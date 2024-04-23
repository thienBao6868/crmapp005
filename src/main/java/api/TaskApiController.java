package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import response.BaseResponse;
import service.TaskService;

@WebServlet(name="taskApiController", urlPatterns = {"/api/task-edit","/api/delete-task"})
public class TaskApiController extends HttpServlet{
	
	private Gson gson = new Gson();
	private TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String servletPath = req.getServletPath();
		if(servletPath.equals("/api/delete-task")) {
			int id_task = Integer.parseInt(req.getParameter("id_task"));
			
			boolean isSuccess = taskService.callDeleteTaskById(id_task);
			
			BaseResponse baseResponse = new BaseResponse();
			baseResponse.setStatusCode(200);
			baseResponse.setMessage(isSuccess ? "Delete Task Thành Công" : " Delete Task Thất bại");
			baseResponse.setData(isSuccess);

			String json = gson.toJson(baseResponse);

			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("content-Type", "application/json");

			PrintWriter printWrite = resp.getWriter();
			printWrite.write(json);
			printWrite.close();
		}
		
	};
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id_task = Integer.parseInt(req.getParameter("id_task"));
		int id_project = Integer.parseInt(req.getParameter("id_project"));
		int id_user = Integer.parseInt(req.getParameter("id_user"));
		int id_status = Integer.parseInt(req.getParameter("id_status"));
		String tenCongViec = req.getParameter("tenCongViec");
		String start_date = req.getParameter("start_date");
		String end_date = req.getParameter("end_date");
		
		boolean isSuccess = taskService.callUpdateTaskById(id_task, id_project, id_user, id_status, tenCongViec, start_date, end_date);
		
		BaseResponse baseResponse = new BaseResponse();

		baseResponse.setStatusCode(200);
		baseResponse.setMessage(isSuccess ? "Update Task Thành Công" : " update Task Thất bại");
		baseResponse.setData(isSuccess);

		String json = gson.toJson(baseResponse);

		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("content-Type", "application/json");

		PrintWriter printWrite = resp.getWriter();
		printWrite.write(json);
		printWrite.close();
		
		
	}
	
};
