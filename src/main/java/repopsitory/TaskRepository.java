package repopsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import config.MySQLConfig;
import emtity.Project;
import emtity.Status;
import emtity.Task;
import emtity.User;

public class TaskRepository {

	public int createTask(String tenCongViec, int idProject, String ngayBatDau, String ngayKetThuc) {

		int result = 0;
		ResultSet resultSet = null;
		int newTaskId = -1;

		String query = "INSERT INTO task (name, start_date, end_date, id_project, id_status) " + "VALUES ('"
				+ tenCongViec + "', '" + ngayBatDau + "', '" + ngayKetThuc + "', " + idProject + ", 1)";

		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			result = statement.executeUpdate();

			// kiểm tra bảng ghi có thêm thành công hay không ?

			if (result == 1) {
				// Thêm thành công thì lấy giá trị được tạo tự động trong qúa trình thực thi SQL
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					newTaskId = resultSet.getInt(1);

				} else {
					throw new ServletException("Failed to retrieve generated task ID");
				}
			} else {
				throw new ServletException("Failed to insert task into database");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error : " + e.getLocalizedMessage());
		}

		return newTaskId;
	}

	public List<Task> GetAllTask() {

		List<Task> listTask = new ArrayList<Task>();

		String query = "SELECT  t.id ,t.name as task_name , p.name as project_name , u.fullname , t.start_date ,t.end_date , s.name as status_name\n"
				+ "FROM task t \n" + "JOIN status s ON s.id = t.id_status\n" + "JOIN project p ON p.id = t.id_project\n"
				+ "JOIN assigntask a ON a.id_task = t.id \n" + "JOIN users u ON u.id = a.id_user ;";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Task task = new Task();
				Status status = new Status();
				Project project = new Project();
				User user = new User();

				status.setName(resultSet.getString("status_name"));
				project.setName(resultSet.getString("project_name"));
				user.setFullname(resultSet.getString("fullname"));

				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("task_name"));
				task.setStart_date(resultSet.getString("start_date"));
				task.setEnd_date(resultSet.getString("end_date"));
				task.setStatus(status);
				task.setProject(project);
				task.setUser(user);

				listTask.add(task);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Kết nối database : " + e.getLocalizedMessage());
		}

		return listTask;
	}

	public List<Task> getAllTaskByUser(int id_user){
		

		List<Task> listTask = new ArrayList<Task>();
		
		String query ="SELECT  t.id as id_task,t.name as task_name , p.name as project_name , t.start_date ,t.end_date ,s.id as id_status, s.name as status_name\n"
				+ "FROM task t \n"
				+ "JOIN status s ON s.id = t.id_status\n"
				+ "JOIN project p ON p.id = t.id_project\n"
				+ "JOIN assigntask a ON a.id_task = t.id \n"
				+ "JOIN users u ON u.id = a.id_user \n"
				+ "WHERE u.id= '"+id_user+"'";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Task task = new Task();
				Status status = new Status();
				Project project = new Project();
			
				
				status.setName(resultSet.getString("status_name"));
				status.setId(resultSet.getInt("id_status"));
				project.setName(resultSet.getString("project_name"));
			
				task.setId(resultSet.getInt("id_task"));
				task.setName(resultSet.getString("task_name"));
				task.setStart_date(resultSet.getString("start_date"));
				task.setEnd_date(resultSet.getString("end_date"));
				task.setStatus(status);
				task.setProject(project);
	
				listTask.add(task);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Kết nối database : " + e.getLocalizedMessage());
		}
		
		return listTask;
	}
}
