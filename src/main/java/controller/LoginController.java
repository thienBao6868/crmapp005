package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import config.MySQLConfig;
import emtity.User;


@WebServlet(name="loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// chuẩn bị câu truy vấn kiểm tra xem email và password có tồn tại trong CSDL
		// hay không

		String query = "SELECT *\n" + "FROM users u \n" + "WHERE  u.email = '" + email + "' AND u.password ='"
				+ password + "'";

		// Mở kết nối tới CSDL
		Connection connection = MySQLConfig.getConnection();

		try {
			// Truyền câu querry đã chuẩn bị cho CSDL đã kết nối
			PreparedStatement statement = connection.prepareStatement(query);

			// executeQuery : chỉ sử dụng khi truy vấn là câu select
			// executeUpdate : chỉ sử dụng khi câu truy vấn là: CREATE, UPDATE , DELETE...

			// Resultset: đại diện cho dữ liệu truy vấn được
			ResultSet resultSet = statement.executeQuery();

			// Tạo list User
			List<User> listUser = new ArrayList<User>();

			// khi nào còn next được thì tạo dữ liệu và lưu vào listUser

			while (resultSet.next()) {
				User user = new User();
				// Lấy giá trị của cột id và gán vào thuộc tính id của đối tượng User
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setFullname(resultSet.getString("fullname"));
				user.setPhone(resultSet.getString("phone"));
				user.setIdRole(resultSet.getInt("id_role"));

				listUser.add(user);

			}

			if (listUser.size() > 0) {
				resp.sendRedirect(req.getContextPath() + "/dashboard");
			} else {
				
				req.setAttribute("ms", "Đăng nhập thất bại, vui lòng thử lại");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}

		} catch (Exception e) {
			System.out.println("Lỗi rồi bé ơi");
		}
	}
}
