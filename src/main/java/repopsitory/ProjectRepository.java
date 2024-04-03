package repopsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;

import config.MySQLConfig;

public class ProjectRepository {

	public int CreateProject(String tenDuAn, String ngayBatDau, String ngayKetThuc ) {
		
		int result = 0;
		
		String query ="INSERT INTO project (name,start_date,end_date) VALUES ('"+tenDuAn+"','"+ngayBatDau+"','"+ngayKetThuc+"')";
		
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			result = statement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi kết nối database" + e.getLocalizedMessage());
		}
		
	
		
		return result;
	}

}
