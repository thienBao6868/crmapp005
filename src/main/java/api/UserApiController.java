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
import service.UserService;

@WebServlet(name="userApiController", urlPatterns = {"/api/user"})
public class UserApiController extends HttpServlet {
	
	private UserService userService = new UserService();
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> listUser  = userService.CallGetAllUser();
		
		String data = gson.toJson(listUser);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-Type", "application/json");
		
		PrintWriter printWrite = resp.getWriter();
		printWrite.write(data);
		printWrite.close();
			
			
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

}
