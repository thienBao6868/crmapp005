package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.PathName;
import service.UserService;

@WebServlet (name = "userController", urlPatterns = {"/add-user","/users","/user-details","/user-edit"})
public class UserController extends HttpServlet {
	
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String servletPath = req.getServletPath();
		if(servletPath.equals(PathName.ADDUSER.getName())) {
			req.setAttribute("listRole", userService.getAllRole());
			
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		}else if (servletPath.equals(PathName.USERS.getName())){
			
			int id_role = 0;
			// Retrieve the cookies associated with the request
			Cookie[] cookies = req.getCookies();

			// Check if cookies exist
			if (cookies != null) {
				// Iterate over the cookies array
				for (Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if (name.equals("role")) {
						id_role=Integer.parseInt(value) ;
					}
				}
			}
			
			req.setAttribute("idRole",id_role);
			req.setAttribute("listUser", userService.CallGetAllUser());
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			
		}else if (servletPath.equals(PathName.DETAILUSER.getName())) {
			int id_user = Integer.parseInt(req.getParameter("id_user")) ;
			
			int id_role = Integer.parseInt(req.getParameter("id_role"));
			
			req.setAttribute("percentOfTask",userService.getPercentOfTask(id_user, id_role)); 
			req.setAttribute("user", userService.callGetUserById(id_user));
			req.setAttribute("listStatus",userService.callGetAllStatus());
			
			req.setAttribute("listTask", userService.callGetAllTaskByUser(id_user, id_role));
			
			req.getRequestDispatcher("user-detail.jsp").forward(req, resp);
		}else if (servletPath.equals(PathName.EDITUSER.getName())) {
			
			int id_user = Integer.parseInt(req.getParameter("id_user")) ;
			req.setAttribute("listRole", userService.getAllRole());
			req.setAttribute("user", userService.callGetUserById(id_user));
			req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
		}
		
		
		
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String fullName = req.getParameter("fullname");
		String email = req.getParameter("email");
		String passWord = req.getParameter("password");
		String phone = req.getParameter("phone");
		int idRole = Integer.parseInt(req.getParameter("idRole"));
		
		
		userService.callCreateUser(firstName, lastName,fullName, email, passWord, phone, idRole);
	
		resp.sendRedirect(req.getContextPath() + "/add-user");
	}
	
}
