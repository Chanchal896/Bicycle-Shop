package Functions;

import java.io.IOException;

import in.sp.backend.DAO.ReturnDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/recordReturn")
public class RecordReturn extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReturnDAO returnDao = new ReturnDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Ssales = request.getParameter("salesID");
        String Sref = request.getParameter("refAmount");
        
        int salesID = 0;
        double refAmount = 0.0;
        
        try {
            salesID = Integer.parseInt(Ssales);
            refAmount = Double.parseDouble(Sref);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid Sales ID or Refund Amount.");
            request.getRequestDispatcher("/RecordReturn.jsp").forward(request, response);
            return;
        }
        
        boolean success = false;
        boolean check = false;
		try {
			salesID = Integer.parseInt(Ssales);
			check=returnDao.check(salesID);
			if(check) {
				success = returnDao.recordReturn(salesID, refAmount);
			}
			else {
				request.setAttribute("errorMessage", "Sales ID does not exist.");
                request.getRequestDispatcher("/RecordReturn.jsp").forward(request, response);
                return;
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
        if (success) {
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to record return. Please try again.");
            request.getRequestDispatcher("/RecordReturn.jsp").forward(request, response);
        }
    }
}
