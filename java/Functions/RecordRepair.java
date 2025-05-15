package Functions;

import in.sp.backend.DAO.RepairDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/recordRepair")
public class RecordRepair extends HttpServlet {
	private RepairDAO repairDAO = new RepairDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Scid = request.getParameter("customerID");
		String SrD = request.getParameter("repairDetails");
		String Stc = request.getParameter("totalCost");

		int customerID;
		double totalCost;

		try {
			customerID = Integer.parseInt(Scid);
			totalCost = Double.parseDouble(Stc);
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Invalid input values.");
			request.getRequestDispatcher("RecordRepair.jsp").forward(request, response);
			return;
		}

		boolean customerExists = repairDAO.customerExists(customerID);
		if (!customerExists) {
			request.setAttribute("errorMessage", "Customer ID does not exist.");
			request.getRequestDispatcher("RecordRepair.jsp").forward(request, response);
			return;
		} else {
			boolean success = repairDAO.recordRepair(customerID, SrD, totalCost,
					new Date(System.currentTimeMillis()));
			if (success) {
				response.sendRedirect("dashboard.jsp");
			} else {
				request.setAttribute("errorMessage", "Failed to record repair.");
				request.getRequestDispatcher("RecordRepair.jsp").forward(request, response);
			}
		}

	}
}
