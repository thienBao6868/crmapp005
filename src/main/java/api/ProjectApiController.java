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
import service.GroupworkService;

@WebServlet(name="projectApiController", urlPatterns = {"/api/groupwork-edit"})
public class ProjectApiController extends HttpServlet{
	
	
	private GroupworkService groupworkService = new GroupworkService();
	private Gson gson = new Gson();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id_project = Integer.parseInt(req.getParameter("id_project")) ;
		String nameProject = req.getParameter("name");
		String startDate = req.getParameter("start_date");
		String endDate = req.getParameter("end_date");
		
		boolean isSuccess = groupworkService.callUpdateProjectById(id_project, nameProject, startDate, endDate);
		
		BaseResponse baseResponse = new BaseResponse();

		baseResponse.setStatusCode(200);
		baseResponse.setMessage(isSuccess ? "Update Project Thành Công" : " update Project Thất bại");
		baseResponse.setData(isSuccess);

		String json = gson.toJson(baseResponse);

		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("content-Type", "application/json");

		PrintWriter printWrite = resp.getWriter();
		printWrite.write(json);
		printWrite.close();
		
		
	}

}
