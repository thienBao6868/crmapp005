package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import emtity.User;
import response.BaseResponse;
import service.UserService;

@WebServlet(name = "userApiController", urlPatterns = { "/api/delete-user", "/api/user-edit" })
public class UserApiController extends HttpServlet {

	private UserService userService = new UserService();
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String servletPath = req.getServletPath();
		if (servletPath.equals("/api/delete-user")) {
			int id_user = Integer.parseInt(req.getParameter("id_user"));

			boolean isSuccess = userService.callDeleteUserById(id_user);
			BaseResponse baseResponse = new BaseResponse();

			baseResponse.setStatusCode(200);
			baseResponse.setMessage(isSuccess ? "Delete user Thành Công" : " Delete user Thất bại");
			baseResponse.setData(isSuccess);

			String json = gson.toJson(baseResponse);

			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("content-Type", "application/json");

			PrintWriter printWrite = resp.getWriter();
			printWrite.write(json);
			printWrite.close();

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id_user = Integer.parseInt(req.getParameter("id_user"));
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String passWord = req.getParameter("password");
		String phone = req.getParameter("phone");
		int idRole = Integer.parseInt(req.getParameter("idRole"));

		boolean isSuccess = userService.callUpdateUserById(id_user, firstName, lastName, fullName, email, passWord,
				phone, idRole);

		BaseResponse baseResponse = new BaseResponse();

		baseResponse.setStatusCode(200);
		baseResponse.setMessage(isSuccess ? "Update user Thành Công" : " update user Thất bại");
		baseResponse.setData(isSuccess);

		String json = gson.toJson(baseResponse);

		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("content-Type", "application/json");

		PrintWriter printWrite = resp.getWriter();
		printWrite.write(json);
		printWrite.close();

	}

}
