package Functions;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import in.sp.backend.DAO.CustomerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/removeCustomer")
public class RemoveCustomer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDao = new CustomerDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String sCID = request.getParameter("customerID");
        int CID = 0;
        try {
            CID = Integer.parseInt(sCID);
        } catch(NumberFormatException e) {
            request.setAttribute("errorMessage", "Error!");
            request.getRequestDispatcher("/removeCustomer.jsp").forward(request, response);
            return;
        }
        
        boolean success = false;
		try {
			success = customerDao.removeCustomer(CID);
		} catch (ClassNotFoundException ec) {
			ec.printStackTrace();
		} 
        if(success) {
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to remove customer. Please try again.");
            request.getRequestDispatcher("/removeCustomer.jsp").forward(request, response);
        }
    }
}
