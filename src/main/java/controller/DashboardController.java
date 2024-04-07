package controller;

import java.io.IOException;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="dashboardController",urlPatterns = {"/dashboard", "/"})
public class DashboardController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String servletPath = req.getServletPath();
		if(servletPath.equals("/")) {
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}else if(servletPath.equals("/dasboard")) {
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}else {
			System.out.println("không tìm thấy bé ơi");
		}
		
	}

}
