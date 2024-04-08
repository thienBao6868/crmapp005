package repopsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import emtity.Project;

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

	public List<Project> GetAllProject (){
		
		 List<Project> listProject = new ArrayList<Project>();
		
		String query = "SELECT  * FROM  project ";
		Connection connection = MySQLConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result =  statement.executeQuery();
			
			
			
			while(result.next()) {
				Project project = new Project();
				project.setId(result.getInt("id"));
				project.setName(result.getString("name"));
				project.setStart_date(result.getString("start_date"));
				project.setEnd_date(result.getString("end_date"));
				
				listProject.add(project);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error" + e.getLocalizedMessage());
		}
		
		
		return listProject ;
		
	}
}
