package Functions;

import java.io.IOException;

import in.sp.backend.DAO.CustomerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addCustomer")
public class AddCustomer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDao = new CustomerDAO();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        boolean add = false;
		try {
			add = customerDao.addCustomer(name, email);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        if (add) {
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to add customer. Please try again.");
            request.getRequestDispatcher("/addCustomer.jsp").forward(request, response);
        }
    }
}
