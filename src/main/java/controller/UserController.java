package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.PathName;
import service.UserService;

@WebServlet (name = "userController", urlPatterns = {"/add-user","/users","/user-details"})
public class UserController extends HttpServlet {
	
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String servletPath = req.getServletPath();
		if(servletPath.equals(PathName.ADDUSER.getName())) {
			req.setAttribute("listRole", userService.getAllRole());
			
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		}else if (servletPath.equals(PathName.USERS.getName())){
			// Test lấy danh sách User. 
			//System.out.println(userService.CallGetAllUser());
			
			req.setAttribute("listUser", userService.CallGetAllUser());
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			
		}else if (servletPath.equals(PathName.DETAILUSER.getName())) {
			int id_user = Integer.parseInt(req.getParameter("id_user")) ;
			
			req.setAttribute("percentOfTask",userService.getPercentOfTask(id_user)); 
			req.setAttribute("user", userService.callGetUserById(id_user));
			req.setAttribute("listStatus",userService.callGetAllStatus());
			req.setAttribute("listTask", userService.callGetAllTaskByUser(id_user));
			req.getRequestDispatcher("user-detail.jsp").forward(req, resp);
		}
		
		
		
		
		
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
