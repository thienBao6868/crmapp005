package repopsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;



import config.MySQLConfig;

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

}
