package in.sp.backend;

import java.io.IOException;

import Classes.User;
import in.sp.backend.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet{
	private UserDAO userDao = new UserDAO();
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletOutputStream out = resp.getOutputStream();
		String uname= req.getParameter("username");
		String password= req.getParameter("password");
		
		User user;
		try {
			user = userDao.checkLogin(uname, password);
			if (user !=null) {
				HttpSession session = req.getSession();
	            session.setAttribute("user", user);
	            resp.sendRedirect("dashboard.jsp");
	            out.println("Login successfull");
			} else {
				req.setAttribute("errorMessage", "Invalid username or password.");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
