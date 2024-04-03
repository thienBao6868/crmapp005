package repopsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
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

	public List<User> GetAllUser(){
		
		List<User> listUser = new ArrayList<User>();
		String query = "SELECT * FROM  users";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setFullname(result.getString("fullname"));
				
				listUser.add(user);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error : " + e.getLocalizedMessage());
		}
		
		return listUser ;
	}
}
