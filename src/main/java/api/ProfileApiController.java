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
import service.ProfileService;
import service.TaskService;

@WebServlet(name="profileApiController", urlPatterns = {"/api/profile-edit"})
public class ProfileApiController extends HttpServlet {

	private Gson gson = new Gson();
	private ProfileService profileService = new ProfileService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_task = Integer.parseInt(req.getParameter("id_task")) ;
		int id_status = Integer.parseInt(req.getParameter("id_status"));
		
		boolean isSuccess = profileService.callUpdateStatusOfTaskd(id_task, id_status);
		
		BaseResponse baseResponse = new BaseResponse();
		
		baseResponse.setStatusCode(200);
		baseResponse.setMessage(isSuccess? "Update status of Task Thành Công" : " update status of task Thất bại");
		baseResponse.setData(isSuccess);
		
		String json = gson.toJson(baseResponse);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("content-Type","application/json");
		
		PrintWriter printWrite = resp.getWriter();
		printWrite.write(json);
		printWrite.close();
	}

}
