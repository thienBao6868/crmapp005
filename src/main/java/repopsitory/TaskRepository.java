package repopsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;

import config.MySQLConfig;

public class TaskRepository {
	

	public int createTask (String tenCongViec, int idProject,String ngayBatDau, String ngayKetThuc) {
		
		int result = 0 ;
		ResultSet resultSet = null;
		int  newTaskId = -1;
		
		String query = "INSERT INTO task (name, start_date, end_date, id_project, id_status) " +
	               "VALUES ('" + tenCongViec + "', '" + ngayBatDau + "', '" + ngayKetThuc + "', " + idProject + ", 1)";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			result = statement.executeUpdate();
			
			// kiểm tra bảng ghi có thêm thành công hay không ?
			
			if(result == 1) {
				// Thêm thành công thì lấy giá trị được tạo tự động trong qúa trình thực thi SQL 
				 resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
	                newTaskId  = resultSet.getInt(1);
	                
	            } else {
	                throw new ServletException("Failed to retrieve generated task ID");
	            }
			}else {
				throw new ServletException("Failed to insert task into database");
			}
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error : " + e.getLocalizedMessage());
		}
		
		
		return newTaskId;
	}

}
