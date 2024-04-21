package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import service.DashboardService;


@WebServlet(name="dashboardController",urlPatterns = {"/dashboard",""})
public class DashboardController extends HttpServlet{
	
	private DashboardService dashboardService = new DashboardService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String servletPath = req.getServletPath();
		
			if(servletPath.equals("/dashboard") || servletPath.equals("") ) {
				
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

								if (name.equals("role")) {
									id_role=Integer.parseInt(value) ;
								}
								if (name.equals("id_user")) {
									id_user=Integer.parseInt(value) ;
								}

							}
						} else {
							System.out.println("No cookies found.");
							
						}
			req.setAttribute("role", dashboardService.callGetRoleById(id_role));
			req.setAttribute("percentOfTask",dashboardService.getPercentOfTask(id_user, id_role)); 
			req.setAttribute("listQuantityTask", dashboardService.callGetQuantityTask(id_user, id_role));
			
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("404.jsp").forward(req, resp);
		}
		
	}

}
