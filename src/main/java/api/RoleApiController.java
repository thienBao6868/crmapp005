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
import service.RoleService;

@WebServlet(name="roleApiController", urlPatterns = {"/api/role-edit"})
public class RoleApiController extends HttpServlet {
	
	private RoleService roleService = new RoleService();
	private Gson gson = new Gson();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id_role = Integer.parseInt(req.getParameter("id_role")) ;
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		
		boolean isSuccess = roleService.callUpdateRoleById(id_role, name, description);
		
		BaseResponse baseResponse = new BaseResponse();

		baseResponse.setStatusCode(200);
		baseResponse.setMessage(isSuccess ? "Update role Thành Công" : " update roleThất bại");
		baseResponse.setData(isSuccess);

		String json = gson.toJson(baseResponse);

		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("content-Type", "application/json");

		PrintWriter printWrite = resp.getWriter();
		printWrite.write(json);
		printWrite.close();
		
		
	}

}
