package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.PathName;

@WebServlet(name = "profileController", urlPatterns = { "/profile", "/profile-edit" })
public class ProfileController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String servletPath = req.getServletPath();
		
		if(servletPath.equals(PathName.PROFILE.getName())) {
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
			
		}else if (servletPath.equals(PathName.EDITPROFILE.getName())) {
			req.getRequestDispatcher("profile-edit.jsp").forward(req, resp);
		}
		
	}

}
