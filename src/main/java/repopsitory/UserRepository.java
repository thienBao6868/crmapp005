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

	public int createUser(String fullName, String email, String passWord, String phone, int idRole ) {
		
		int result = 0 ;
		
		String query = "INSERT INTO users (email, password,fullname,phone,id_role) VALUES ('"+email+"','"+passWord+"','"+fullName+"','"+phone+"',"+idRole+")";
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

	// Custom để sử dụng được hàm getAllUser cho nhiều trường hợp (sử dụng ở /users , /task-add)
	
	public List<User> GetAllUser(){
		
		List<User> listUser = new ArrayList<User>();
		String query = "SELECT u.id , u.fullname ,u.first_name ,u.last_name ,u.email , r.name as role_name\n"
				+ "FROM users u \n"
				+ "JOIN roles r ON r.id = u.id_role ;";
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
				user.setLastName( resultSet.getString("last_name"));
				user.setFullname(resultSet.getString("fullname"));
				
				Role role = new Role();
				role.setName(resultSet.getString("role_name"));
				user.setRole(role);

				listUser.add(user);
				
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error : " + e.getLocalizedMessage());
		}
		
		return listUser ;
	}
}
