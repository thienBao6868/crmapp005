package repopsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import emtity.Project;
import emtity.User;

public class ProjectRepository {

	public int CreateProject(String tenDuAn, String ngayBatDau, String ngayKetThuc, int id_user) {

		int result = 0;

		String query = "INSERT INTO project (name,start_date,end_date,id_user) VALUES ('" + tenDuAn + "','" + ngayBatDau
				+ "','" + ngayKetThuc + "','" + id_user + "')";

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

	public List<Project> GetAllProject(int id_user, int id_role) {

		List<Project> listProject = new ArrayList<Project>();
		String query = "";
		switch (id_role) {
		case 1:
			query = "SELECT p.id, p.name ,p.start_date ,p.end_date ,p.id_user, u.fullname\n" + "FROM project p \n"
					+ "JOIN users u ON u.id = p.id_user ";
			break;
		case 2:
			query = "SELECT p.id, p.name ,p.start_date ,p.end_date ,p.id_user, u.fullname\n" + "FROM project p \n"
					+ "JOIN users u ON u.id = p.id_user \n" + "WHERE p.id_user = '" + id_user + "';";
			break;

		case 3:
			query = "SELECT p.id, p.name ,p.start_date ,p.end_date ,p.id_user , u.fullname\n" + "FROM project p \n"
					+ "JOIN task t ON t.id_project = p.id \n" + "JOIN assigntask a ON a.id_task = t.id \n"
					+ "JOIN users u ON u.id = a.id_user \n" + "WHERE a.id_user = '" + id_user + "'\n"
					+ "GROUP BY p.id\n" + "ORDER BY p.id ASC ";
			break;

		default:
			break;
		}

		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("id_user"));
				user.setFullname(result.getString("fullname"));

				Project project = new Project();
				project.setId(result.getInt("id"));
				project.setName(result.getString("name"));
				project.setStart_date(result.getString("start_date"));
				project.setEnd_date(result.getString("end_date"));
				project.setUser(user);

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

		String query = "SELECT p.id,p.name ,p.start_date ,p.end_date , u.id as id_user , u.fullname \n"
				+ "FROM project p \n" + "JOIN users u ON u.id = p.id_user \n" + "WHERE p.id = '" + id_project + "'; ";
		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				project.setId(result.getInt("id"));
				project.setName(result.getString("name"));
				project.setStart_date(result.getString("start_date"));
				project.setEnd_date(result.getString("end_date"));

				User leader = new User();
				leader.setId(result.getInt("id_user"));
				leader.setFullname(result.getString("fullname"));
				project.setUser(leader);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error" + e.getLocalizedMessage());
		}

		return project;
	}

	public int updateProjectById(int id_project, String nameProject, String startDate, String endDate, int id_leader) {
		int result = 0;

		String query = "UPDATE project t\n"
				+ "SET t.name = '"+nameProject+"', t.start_date = '"+startDate+"' , t.end_date = '"+endDate+"' , t.id_user='"+id_leader+"' \n"
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

	public int deleteProjectById(int id_project) {
		int result = 0;

		String updateIdProjectOfTask = "UPDATE task t\n" + "SET t.id_project = NULL \n" + "WHERE t.id_project ='"
				+ id_project + "';";

		String deleteProjectById = "DELETE FROM project WHERE project.id = '" + id_project + "';";

		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement updateIdProjectOfTaskStatement = connection.prepareStatement(updateIdProjectOfTask);
			updateIdProjectOfTaskStatement.executeUpdate();
			PreparedStatement deleteProjectByIdStatement = connection.prepareStatement(deleteProjectById);
			result = deleteProjectByIdStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi xoá project" + e.getLocalizedMessage());
		}

		return result;
	}

}
