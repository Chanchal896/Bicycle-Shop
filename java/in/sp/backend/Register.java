package in.sp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import in.sp.backend.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reg")
public class Register extends HttpServlet{
	
	private UserDAO userDao = new UserDAO();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uname= req.getParameter("name");
		String password= req.getParameter("pass");
		String confpass= req.getParameter("confirmpass");
		String role= req.getParameter("role");

		try {
			if(password.equals(confpass)) {					
					boolean check=userDao.registerUser(uname, password, role);
					if (check) {
						System.out.println("User registerd successfully");
						resp.sendRedirect("login.jsp");
					}
					else {
						System.out.println("This registeruser did not worked and left out");
					}
			}
			else {
				req.setAttribute("errorMessage", "Passwords do not match.");
				req.getRequestDispatcher("/Register.jsp").forward(req, resp);
	            return;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
