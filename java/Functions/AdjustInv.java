package Functions;

import java.io.IOException;

import in.sp.backend.DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adjustInventory")
public class AdjustInv extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDao = new ProductDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String SpID = request.getParameter("productID");
        String Snq = request.getParameter("newQuantity");
        int pID = 0;
        int NQ = 0;
        try {
            pID = Integer.parseInt(SpID);
            NQ = Integer.parseInt(Snq);
        } catch(NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input. Please enter numeric values.");
            request.getRequestDispatcher("/adjustInventory.jsp").forward(request, response);
            return;
        }
        
        boolean success = false;
		try {
			success = productDao.adjustInventory(pID, NQ);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(success) {
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to adjust inventory. Please try again.");
            request.getRequestDispatcher("/adjustInventory.jsp").forward(request, response);
        }
    }
}
