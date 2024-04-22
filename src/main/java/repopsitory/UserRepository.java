package repopsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import emtity.Role;
import emtity.User;

public class UserRepository {

	public int createUser(String firstName, String lastName, String fullName, String email, String passWord,
			String phone, int idRole) {

		int result = 0;

		String query = "INSERT INTO users (email, password,fullname,phone,id_role,first_name,last_name) VALUES ('"
				+ email + "','" + passWord + "','" + fullName + "','" + phone + "','" + idRole + "','" + firstName
				+ "','" + lastName + "')";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			result = statement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi connect database" + e.getLocalizedMessage());
		}

		return result;

	}

	// Custom để sử dụng được hàm getAllUser cho nhiều trường hợp (sử dụng ở /users
	// , /task-add)

	public List<User> GetAllUser() {

		List<User> listUser = new ArrayList<User>();
		String query = "SELECT u.id , u.fullname ,u.first_name,u.last_name ,u.email , r.name as role_name, r.id as id_role\n"
				+ "FROM users u \n" + "LEFT JOIN roles r ON r.id = u.id_role ;";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				// Lấy giá trị của cột id và gán vào thuộc tính id của đối tượng User
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setFullname(resultSet.getString("fullname"));

				Role role = new Role();
				role.setName(resultSet.getString("role_name"));
				role.setId(resultSet.getInt("id_role"));
				user.setRole(role);

				listUser.add(user);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error : " + e.getLocalizedMessage());
		}

		return listUser;
	}

	public User getUserById(int id_user) {
		User user = new User();
		String query = "SELECT *\n" + "FROM users u \n" + "WHERE u.id = '" + id_user + "';";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Role role = new Role();
				role.setId(resultSet.getInt("id_role"));

				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFullname(resultSet.getString("fullname"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setPhone(resultSet.getString("phone"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(role);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi kết nối Database lấy user by Id" + e.getLocalizedMessage());
		}

		return user;
	}

	public int updateUserById(int id_user, String firstName, String lastName, String fullName, String email,
			String passWord, String phone, int idRole) {
		int result = 0;

		String query = "UPDATE users s\n" + "SET  s.email = '" + email + "' , s.password = '" + passWord
				+ "' , s.fullname = '" + fullName + "' , s.phone = '" + phone + "' ,s.first_name = '" + firstName
				+ "', s.last_name = '" + lastName + "' , s.id_role = '" + idRole + "' \n" + "WHERE s.id  = '" + id_user
				+ "' ;";

		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			result = statement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lỗi update User" + e.getLocalizedMessage());
		}

		return result;
	}

	public int deleteUserById(int id_user) {
		int result = 0;

		String deleteIdUserOfAssignTask = "DELETE FROM assigntask WHERE assigntask.id_user = '" + id_user + "'";

		String deleteUserById = "DELETE FROM users WHERE users.id  ='" + id_user + "' ";

		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement deleteAssignTaskStatement = connection.prepareStatement(deleteIdUserOfAssignTask);
			deleteAssignTaskStatement.executeUpdate();

			PreparedStatement deleteUserByIdStatement = connection.prepareStatement(deleteUserById);
			result = deleteUserByIdStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lỗi xoá user : " + e.getLocalizedMessage());
		}

		return result;
	}

	public User getUserDoTask(int id_task) {
		User user = new User();
		String query = "SELECT u.id, u.fullname \n" + "FROM task t\n" + "JOIN assigntask a ON a.id_task = t.id \n"
				+ "JOIN users u ON u.id = a.id_user \n" + "WHERE t.id = '" + id_task + "';";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				user.setId(resultSet.getInt("id"));
				user.setFullname(resultSet.getString("fullname"));

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi kết nối Database lấy user by Id" + e.getLocalizedMessage());
		}

		return user;
	}

	public List<User> getAllUserIsLeader(int id_user, int id_role) {

		List<User> listUser = new ArrayList<User>();
		String query = "";

		switch (id_role) {
		case 1:
			query="SELECT u.id,u.fullname \n"
					+ "FROM users u \n"
					+ "WHERE u.id_role =2;";
			break;
		case 2:
			query = "SELECT u.id,u.fullname \n"
					+ "FROM users u \n"
					+ "WHERE u.id = '"+id_user+"';";
			break;
		default:
			break;
		}

		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				// Lấy giá trị của cột id và gán vào thuộc tính id của đối tượng User
				user.setId(resultSet.getInt("id"));
				user.setFullname(resultSet.getString("fullname"));

				listUser.add(user);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error : " + e.getLocalizedMessage());
		}

		return listUser;
	}
	
	public List<User> getAllUserOfProject(int id_project) {

		List<User> listUser = new ArrayList<User>();
		String query = "SELECT u.id ,u.fullname \n"
				+ "FROM task t \n"
				+ "JOIN assigntask a ON a.id_task = t.id \n"
				+ "JOIN users u ON u.id = a.id_user \n"
				+ "WHERE t.id_project = '"+id_project+"'\n"
				+ "GROUP BY u.id ;";


		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				// Lấy giá trị của cột id và gán vào thuộc tính id của đối tượng User
				user.setId(resultSet.getInt("id"));
				user.setFullname(resultSet.getString("fullname"));

				listUser.add(user);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error  : " + e.getLocalizedMessage());
		}

		return listUser;
	}

}
