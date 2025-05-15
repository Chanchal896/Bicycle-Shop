package Functions;

import java.io.IOException;

import in.sp.backend.DAO.SalesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/recordSale")
public class RecordSale extends HttpServlet {
	
    private SalesDAO salesDao = new SalesDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String scid = request.getParameter("customerID");
        String Spid = request.getParameter("productID");
        String Snet = request.getParameter("netAmount");
        
        int customerID = 0;
        int productID = 0;
        double netAmount = 0.0;
        
        try {
            customerID = Integer.parseInt(scid);
            productID = Integer.parseInt(Spid);
            netAmount = Double.parseDouble(Snet);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input for customer ID, product ID, or net amount.");
            request.getRequestDispatcher("/recordSale.jsp").forward(request, response);
            return;
        }
        
        double totalAmount = netAmount;
        
        boolean success = false;
        boolean check = false;
		try {
			check=salesDao.check(customerID);
			if(check) {
				success = salesDao.recordSale(customerID, totalAmount, productID, netAmount);
			}
			else {
				request.setAttribute("errorMessage", "Customer ID does not exist.");
                request.getRequestDispatcher("/RecordSale.jsp").forward(request, response);
                return;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        if (success) {
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to record sale. Please try again.");
            request.getRequestDispatcher("/recordSale.jsp").forward(request, response);
        }
    }
}
