package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.PathName;
import service.TaskService;

@WebServlet(name = "taskController", urlPatterns = { "/task-add", "/task","/task-edit","/task-detail" })
public class TaskController extends HttpServlet {

	private TaskService taskService = new TaskService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String servletPath = req.getServletPath();

		if (servletPath.equals(PathName.ADDTASK.getName())) {
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
			
			
			req.setAttribute("listProject", taskService.CallGetAllProject(id_user, id_role));
			req.setAttribute("listUser", taskService.callGetAllUserIsStaff());
			
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);

		} else if (servletPath.equals(PathName.TASK.getName())) {
			
			
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
			
			req.setAttribute("idRole",id_role);
			req.setAttribute("listTask", taskService.CallGetAllTask(id_user, id_role));
			req.getRequestDispatcher("task.jsp").forward(req, resp);
			
		}else if (servletPath.equals(PathName.EDITTASK.getName())) {
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
			
			int idTask = Integer.parseInt(req.getParameter("id_task"));
			
			req.setAttribute("task",taskService.callGetTaskById(idTask));
			req.setAttribute("listStatus", taskService.callGetAllStatus());
			req.setAttribute("listProject", taskService.CallGetAllProject(id_user, id_role));
			req.setAttribute("listUser", taskService.callGetAllUserIsStaff());
			req.getRequestDispatcher("task-edit.jsp").forward(req, resp);

		}else if (servletPath.equals(PathName.TASKDETAIL.getName())) {
			
			int idTask = Integer.parseInt(req.getParameter("id_task"));
			
			req.setAttribute("userDoTask", taskService.callGetUserDoTask(idTask));
			req.setAttribute("task",taskService.callGetTaskById(idTask));
			
			req.getRequestDispatcher("task-detail.jsp").forward(req, resp);
		}

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int idProject = Integer.parseInt(req.getParameter("tenDuAn"));
		String tenCongViec = req.getParameter("tenCongViec");
		int idUser = Integer.parseInt(req.getParameter("nguoiThucHien"));
		String ngayBatDau = req.getParameter("ngayBatDau");
		String ngayKetThuc = req.getParameter("ngayKetThuc");
		boolean isCreateTaskSuccess = taskService.CallCreateTask(tenCongViec, idProject, ngayBatDau, ngayKetThuc, idUser);
		
		
		

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
		
		req.setAttribute("isCreateTaskSuccess", isCreateTaskSuccess);
		req.setAttribute("listProject", taskService.CallGetAllProject(id_user, id_role));
		req.setAttribute("listUser", taskService.callGetAllUserIsStaff());
		
		req.getRequestDispatcher("task-add.jsp").forward(req, resp);

	}
}
