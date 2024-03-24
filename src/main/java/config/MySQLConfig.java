package config;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySQLConfig {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			
			String url = "jdbc:mysql://localhost:3307/crmapp";
            String username = "root";
            String password = "bao123";
			// Khai Báo driver sẽ sử dụng là của MySQL 
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Khai báo thông tin đường dẫn MYSQL sẽ kết nối đến 
			return DriverManager.getConnection(url,username, password);
			
		} catch (Exception e) {
			System.out.println("Lỗi kết nối CSDL" + e.getMessage());
		}
		return connection;
	}

}
