package Functions;

import java.io.IOException;

import in.sp.backend.DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addProduct")
public class AddProduct extends HttpServlet {
    private ProductDAO productDao = new ProductDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Spname = request.getParameter("productName");
        String Sprice = request.getParameter("price");
        String Sstock = request.getParameter("stockQuantity");
        double price = 0.0;
        int SQ = 0;
        try {
            price = Double.parseDouble(Sprice);
            SQ = Integer.parseInt(Sstock);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input for price or stock quantity.");
            request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
            return;
        }
        
        boolean added = false;
		try {
			added = productDao.addProduct(Spname, price, SQ);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        if (added) {
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to add product. Please try again.");
            request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
        }
    }
}
