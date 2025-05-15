package Functions;

import java.io.IOException;

import in.sp.backend.DAO.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderProduct")
public class OrderProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDAO orderDao = new OrderDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
    	String Spid = request.getParameter("productId");
        String Sq = request.getParameter("quantity");
        String Spr = request.getParameter("price");
        
        int productId = 0;
        int quantity = 0;
        double price = 0.0;
        
        try {
            productId = Integer.parseInt(Spid);
            quantity = Integer.parseInt(Sq);
            price = Double.parseDouble(Spr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input for product ID, quantity, or price.");
            request.getRequestDispatcher("/orderProduct.jsp").forward(request, response);
            return;
        }
        
        boolean success = false;
		try {
			success = orderDao.placeOrder(productId, quantity, price);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        if (success) {
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to place order. Please try again.");
            request.getRequestDispatcher("/orderProduct.jsp").forward(request, response);
        }
    }
}
