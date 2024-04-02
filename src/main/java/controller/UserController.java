package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import service.UserService;

@WebServlet (name = "userController", urlPatterns = {"/add-user"})
public class UserController extends HttpServlet {
	
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.setAttribute("listRole", userService.getAllRole());
		
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fullName = req.getParameter("fullname");
		String email = req.getParameter("email");
		String passWord = req.getParameter("password");
		String phone = req.getParameter("phone");
		int idRole = Integer.parseInt(req.getParameter("idRole"));
		
		
		userService.callCreateUser(fullName, email, passWord, phone, idRole);
	
		resp.sendRedirect(req.getContextPath() + "/add-user");
	}
	
}
