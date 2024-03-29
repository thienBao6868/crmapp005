package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.MySQLConfig;
import emtity.User;



@WebServlet(name = "loginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Retrieve the cookies associated with the request
		Cookie[] cookies = req.getCookies();

		// Check if cookies exist
		if (cookies != null) {
			// Iterate over the cookies array
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();

				if (name.equals("email")) {
					req.setAttribute("email", value);
				}
				if (name.equals("password")) {
					req.setAttribute("password", value);
				}

			}
		} else {
			System.out.println("No cookies found.");
		}

		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String  remember = req.getParameter("remember");

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
				user.setLastName( resultSet.getString("last_name"));
				user.setFullname(resultSet.getString("fullname"));
				user.setPhone(resultSet.getString("phone"));
				user.setIdRole(resultSet.getInt("id_role"));

				listUser.add(user);

			}

			if (listUser.size() > 0) {

				if (remember != null) {
					// Create a new cookie
					Cookie cookie = new Cookie("email", email);
					Cookie cookie1 = new Cookie("password", password);

					// Set the maximum age of the cookie (in seconds), here it's set to 24 hours
					cookie.setMaxAge(24 * 60 * 60);
					cookie1.setMaxAge(24 * 60 * 60); // 1 day
					// Add the cookie to the response
					resp.addCookie(cookie);
					resp.addCookie(cookie1);
				}

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
