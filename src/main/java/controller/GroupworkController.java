package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.PathName;
import service.GroupworkService;
import service.ProfileService;

@WebServlet(name = "groupworkController", urlPatterns = { "/groupwork-add", "/groupwork", "/groupwork-details","/groupwork-edit"})
public class GroupworkController extends HttpServlet {

	private GroupworkService groupworkService = new GroupworkService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String servletPath = req.getServletPath();

		if (servletPath.equals(PathName.GROUPWORK.getName())) {
			
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
			
			req.setAttribute("currentRole", id_role);
			req.setAttribute("listProject", groupworkService.CallGetAllProject(id_user, id_role));
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		} else if (servletPath.equals(PathName.ADDGROUPWORK.getName())) {
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
			
			req.setAttribute("listUserIsLeader", groupworkService.callGetAllUserIsLeader(id_user, id_role));
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		} else if (servletPath.equals(PathName.DETAILSGROUPWORK.getName())) {
			
			int id_project = Integer.parseInt(req.getParameter("id_project"));
			
			
			
		
			req.setAttribute("listUserOfProject", groupworkService.callGetAllUserOfProject(id_project));
			req.setAttribute("listAllTaskOfProject",groupworkService.callGetAllTaskOfProject(id_project));
			req.setAttribute("percentOfTask",groupworkService.getPercentOfTask(id_project)); 
			req.getRequestDispatcher("groupwork-details.jsp").forward(req, resp);
		} else if (servletPath.equals(PathName.EDITGROUPWORK.getName())) {
			
			int id_project = Integer.parseInt(req.getParameter("id-project"));
			
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
			
			req.setAttribute("listUserIsLeader", groupworkService.callGetAllUserIsLeader(id_user, id_role));
			
			req.setAttribute("project", groupworkService.callGetProjectById(id_project));
			
			req.getRequestDispatcher("groupwork-edit.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		
		if (servletPath.equals(PathName.ADDGROUPWORK.getName())) {
			
			String tenDuAn = req.getParameter("tenDuAn");
			String ngayBatDau = req.getParameter("ngayBatDau");
			String ngayKetThuc = req.getParameter("ngayKetThuc");
			int id_leader = Integer.parseInt(req.getParameter("leader"));
			
			
			boolean isCreateProjectSuccess = groupworkService.CallCreateProject(tenDuAn, ngayBatDau, ngayKetThuc, id_leader);

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
			
			req.setAttribute("isCreateProjectSuccess", isCreateProjectSuccess);
			req.setAttribute("listUserIsLeader", groupworkService.callGetAllUserIsLeader(id_user, id_role));
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
			
		}
		
	}

}
