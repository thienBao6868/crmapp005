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

@WebServlet(name = "userApiController", urlPatterns = { "/api/user", "/api/user-edit" })
public class UserApiController extends HttpServlet {

	private UserService userService = new UserService();
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> listUser = userService.CallGetAllUser();

		String data = gson.toJson(listUser);

		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-Type", "application/json");

		PrintWriter printWrite = resp.getWriter();
		printWrite.write(data);
		printWrite.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id_user = Integer.parseInt(req.getParameter("id_user"));
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String fullName = req.getParameter("fullname");
		String email = req.getParameter("email");
		String passWord = req.getParameter("password");
		String phone = req.getParameter("phone");
		int idRole = Integer.parseInt(req.getParameter("idRole"));

		boolean isSuccess = userService.callUpdateUserById(id_user, firstName, lastName, fullName, email, passWord,
				phone, idRole);

		BaseResponse baseResponse = new BaseResponse();

		baseResponse.setStatusCode(200);
		baseResponse.setMessage(isSuccess ? "Update status of Task Thành Công" : " update status of task Thất bại");
		baseResponse.setData(isSuccess);

		String json = gson.toJson(baseResponse);

		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("content-Type", "application/json");

		PrintWriter printWrite = resp.getWriter();
		printWrite.write(json);
		printWrite.close();

	}

}
