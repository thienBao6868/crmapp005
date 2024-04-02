package repopsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import emtity.Role;

// quản lý tất cả các câu truy vấn liên quan tởi bản role
public class RoleRepository {
	public List<Role> getAll() {

		List<Role> listRole = new ArrayList<Role>();

		try {

			String query = "SELECT * FROM roles";

			Connection connection = MySQLConfig.getConnection();

			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				Role role = new Role();

				role.setId(result.getInt("id"));

				role.setName(result.getString("name"));

				role.setDescription(result.getString("description"));

				listRole.add(role);

			}

		} catch (Exception e) {

			// **TODO**: handle exception

			System.out.println("Lỗi truy roles" + e.getLocalizedMessage());

		}
		return listRole;
	}

	public int createRole(String name, String description) {
		int result = 0;
		String query = "INSERT  INTO roles (name, description) VALUES ('"+name+"','"+description+"')";
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			result = statement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lỗi kết nối database" + e.getLocalizedMessage());
		}
		return result;
		
	}
	
}
