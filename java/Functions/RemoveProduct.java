package Functions;

import java.io.IOException;

import in.sp.backend.DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/removeProduct")
public class RemoveProduct extends HttpServlet {
    private ProductDAO productDao = new ProductDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String STR = request.getParameter("productID");
        int pID = 0;
        try {
            pID = Integer.parseInt(STR);
        } catch(NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid Product ID.");
            request.getRequestDispatcher("/removeProduct.jsp").forward(request, response);
            return;
        }
        
        boolean success = false;
		try {
			success = productDao.removeProduct(pID);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(success) {
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to remove product!");
            request.getRequestDispatcher("/removeProduct.jsp").forward(request, response);
        }
    }
}
