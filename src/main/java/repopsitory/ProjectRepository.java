package repopsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import emtity.Project;

public class ProjectRepository {

	public int CreateProject(String tenDuAn, String ngayBatDau, String ngayKetThuc) {

		int result = 0;

		String query = "INSERT INTO project (name,start_date,end_date) VALUES ('" + tenDuAn + "','" + ngayBatDau + "','"
				+ ngayKetThuc + "')";

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

	public List<Project> GetAllProject() {

		List<Project> listProject = new ArrayList<Project>();

		String query = "SELECT  * FROM  project ";
		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
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

		return listProject;

	}

	public Project getProjectById(int id_project) {
		Project project = new Project();

		String query = "SELECT *\n" + "FROM project p \n" + "WHERE p.id = '" + id_project + "' ; ";
		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				project.setId(result.getInt("id"));
				project.setName(result.getString("name"));
				project.setStart_date(result.getString("start_date"));
				project.setEnd_date(result.getString("end_date"));

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error" + e.getLocalizedMessage());
		}

		return project;
	}
	
	public int updateProjectById (int id_project, String nameProject, String startDate, String endDate) {
		int result = 0;

		String query = "UPDATE project t\n"
				+ "SET t.name ='"+nameProject+"' , t.start_date = '"+startDate+"' , t.end_date = '"+endDate+"' \n"
				+ "WHERE t.id  = '"+id_project+"' ;";

		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			result = statement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi update Project: " + e.getLocalizedMessage());
		}

		return result;
	}
	
}
