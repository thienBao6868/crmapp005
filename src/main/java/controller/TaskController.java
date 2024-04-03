package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TaskService;

@WebServlet(name = "taskController", urlPatterns = { "/task-add" })
public class TaskController extends HttpServlet {

	private TaskService taskService = new TaskService();

	@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 
	 req.setAttribute("listProject", taskService.CallGetAllProject() );
	 req.setAttribute("listUser", taskService.CallGetAllUser());
	 //System.out.print( taskService.CallGetAllProject());
	 req.getRequestDispatcher("task-add.jsp").forward(req, resp);
}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int idProject = Integer.parseInt(req.getParameter("tenDuAn"));
		String tenCongViec = req.getParameter("tenCongViec");
		int idUser= Integer.parseInt(req.getParameter("nguoiThucHien"));
		String ngayBatDau = req.getParameter("ngayBatDau");
		String ngayKetThuc = req.getParameter("ngayKetThuc");
		
		// System.out.println(idProject + tenCongViec + idUser + ngayBatDau + ngayKetThuc);
		taskService.CallCreateTask(tenCongViec, idProject, ngayBatDau, ngayKetThuc, idUser);
		
		resp.sendRedirect(req.getContextPath() + "/task-add");
		
		
	}
}
