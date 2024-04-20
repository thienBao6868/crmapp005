package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.PathName;
import service.ProfileService;

@WebServlet(name = "profileController", urlPatterns = { "/profile", "/profile-edit" })
public class ProfileController extends HttpServlet {

	private ProfileService profileService = new ProfileService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String servletPath = req.getServletPath();
		if (servletPath.equals(PathName.PROFILE.getName())) {
			int id_user = 0;
			int id_role = 0;
			// Retrieve the cookies associated with the request
			Cookie[] cookies = req.getCookies();

			// Check if cookies exist
			if (cookies != null) {
				// Iterate over the cookies array
				for (Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if (name.equals("id_user")) {
						id_user = Integer.parseInt(value);
					}
					if (name.equals("role")) {
						id_role=Integer.parseInt(value) ;
					}
				}
			}
			
			req.setAttribute("role", profileService.callGetRoleById(id_role));
			req.setAttribute("percentOfTask",profileService.getPercentOfTask(id_user, id_role)); 
			req.setAttribute("listTaskByUser", profileService.callGetAllTaskByUser(id_user,id_role));
			req.setAttribute("user", profileService.callGetUserById(id_user));
			req.getRequestDispatcher("profile.jsp").forward(req, resp);

		} else if (servletPath.equals(PathName.EDITPROFILE.getName())) {
			
			int id_task = Integer.parseInt(req.getParameter("id_task")) ;
			
			
			req.setAttribute("taskById", profileService.callGetTaskById(id_task));
			req.setAttribute("listStatus", profileService.callGetAllStatus());
	
			req.getRequestDispatcher("profile-edit.jsp").forward(req, resp);
		}

	}

}
