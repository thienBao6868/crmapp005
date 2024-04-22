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
import emtity.QuantityTask;
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
			System.out.println("Error create Task : " + e.getLocalizedMessage());
		}

		return newTaskId;
	}
	
	public List<Task> getAllTaskByUser(int id_user, int id_role) {
		
		
		List<Task> listTask = new ArrayList<Task>();

		String query = "";

		switch (id_role) {
		case 1:
			query = "SELECT  t.id as id_task,t.name as task_name , p.name as project_name , t.start_date ,t.end_date ,s.id as id_status, s.name as status_name , u.id as id_user, u.fullname \n"
					+ "FROM task t \n"
					+ "LEFT JOIN status s ON s.id = t.id_status\n"
					+ "LEFT JOIN project p ON p.id = t.id_project\n"
					+ "LEFT JOIN assigntask a ON a.id_task = t.id \n"
					+ "LEFT JOIN users u ON u.id = a.id_user ";
			break;
		case 2:
			query = "SELECT t.id as id_task,t.name as task_name , p.name as project_name , t.start_date ,t.end_date ,s.id as id_status, s.name as status_name,u.id as id_user, u.fullname \n"
					+ "FROM project p \n"
					+ "JOIN task t ON t.id_project = p.id \n"
					+ "JOIN assigntask a ON a.id_task = t.id \n"
					+ "JOIN users u ON u.id = a.id_user \n"
					+ "JOIN status s ON s.id = t.id_status\n"
					+ "WHERE p.id_user = '"+id_user+"'";
			break;
		case 3:
			query = "SELECT  t.id as id_task,t.name as task_name , p.name as project_name , t.start_date ,t.end_date ,s.id as id_status, s.name as status_name, u.id as id_user, u.fullname \n"
					+ "FROM task t \n"
					+ "LEFT JOIN status s ON s.id = t.id_status\n"
					+ "LEFT JOIN project p ON p.id = t.id_project\n"
					+ "LEFT JOIN assigntask a ON a.id_task = t.id \n"
					+"JOIN users u ON u.id = a.id_user "
					+ "WHERE a.id_user = '"+id_user+"'";
			break;
		default:
			break;
		}

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
				status.setId(resultSet.getInt("id_status"));
				project.setName(resultSet.getString("project_name"));
				user.setId(resultSet.getInt("id_user"));
				user.setFullname(resultSet.getString("fullname"));

				task.setId(resultSet.getInt("id_task"));
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
			System.out.println("Error Kết nối database lấy all task by User : " + e.getLocalizedMessage());
		}

		return listTask;
	}

	public Task getTaskById(int id_task) {

		Task task = new Task();
		String query = "  SELECT t.id as id_task, t.name, t.start_date, t.end_date, t.id_project, p.name as name_project, s.id as id_status , s.name as name_status, u.id as id_user, u.fullname\n"
				+ " FROM task t \n"
				+ " JOIN project p ON p.id = t.id_project\n"
				+ " JOIN status s ON s.id = t.id_status\n"
				+ " JOIN users u ON u.id = p.id_user\n"
				+ " WHERE t.id = '"+id_task+"';";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Project project = new Project();
				Status status = new Status();
				User user = new User();

				project.setId(resultSet.getInt("id_project"));
				project.setName(resultSet.getString("name_project"));

				status.setId(resultSet.getInt("id_status"));
				status.setName(resultSet.getString("name_status"));
				
				user.setId(resultSet.getInt("id_user"));
				user.setFullname(resultSet.getString("fullname"));

				task.setId(resultSet.getInt("id_task"));
				task.setName(resultSet.getString("name"));
				task.setStart_date(resultSet.getString("start_date"));
				task.setEnd_date(resultSet.getString("end_date"));
				task.setProject(project);
				task.setStatus(status);
				task.setUser(user);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi kết nối database lấy task by id :" + e.getLocalizedMessage());
		}
		return task;
	}

	public int updateStatusOfTaskd(int id_task, int id_status) {
		int result = 0;

		String query = "UPDATE task t\n" + "JOIN assigntask a ON a.id_task = t.id\n" + "SET t.id_status = '" + id_status
				+ "' , a.id_status = '" + id_status + "'\n" + "WHERE t.id = '" + id_task + "';";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			result = statement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi update status of Task : " + e.getLocalizedMessage());
		}
		return result;
	}

	public int updateTaskById(int id_task, int id_project, int id_user, int id_status, String tenCongViec,
			String start_date, String end_date) {
		int result = 0;

		String query = "UPDATE task t\n" + "JOIN assigntask a ON a.id_task = t.id\n" + "SET t.id_status = '" + id_status
				+ "' , a.id_status = '" + id_status + "', t.name ='" + tenCongViec + "' , t.start_date = '" + start_date
				+ "' , t.end_date = '" + end_date + "' , t.id_project = '" + id_project + "', a.id_user='" + id_user
				+ "'\n" + "WHERE t.id = '" + id_task + "';";
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			result = statement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi update Task By Id : " + e.getLocalizedMessage());
		}
		return result;

	}

	public int deleteTaskById(int id_task) {
		int result = 0;

		String deleteIdTaskOfAssignTask = "DELETE FROM assigntask WHERE assigntask.id_task = '" + id_task + "'";

		String deleteTaskById = "DELETE FROM task WHERE task.id  ='" + id_task + "' ";

		Connection connection = MySQLConfig.getConnection();

		try {
			PreparedStatement deleteIdTaskOfAssignTaskStatement = connection.prepareStatement(deleteIdTaskOfAssignTask);
			deleteIdTaskOfAssignTaskStatement.executeUpdate();

			PreparedStatement deleteTaskByIdStatement = connection.prepareStatement(deleteTaskById);
			result = deleteTaskByIdStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("lỗi xoá task by Id : " + e.getLocalizedMessage());
		}

		return result;
	}

	public List<QuantityTask> getQuantityTask(int id_user, int id_role) {

		List<QuantityTask> listQuantityTask = new ArrayList<QuantityTask>();
		String query = "";
		switch (id_role) {
		case 1:
			query = "SELECT IFNULL(result.totalTaskByStatus, 0) AS totalTaskByStatus\n"
					+ "FROM (\n"
					+ "    SELECT COUNT(t.id) AS totalTaskByStatus, t.id_status\n"
					+ "    FROM (\n"
					+ "        SELECT id_status\n"
					+ "        FROM task\n"
					+ "        GROUP BY id_status\n"
					+ "        LIMIT 3\n"
					+ "    ) AS all_statuses\n"
					+ "    LEFT JOIN task t ON t.id_status = all_statuses.id_status\n"
					+ "    GROUP BY t.id_status \n"
					+ "    ORDER BY t.id_status ASC\n"
					+ ") AS result; ";
			break;
		case 2:
			query = "SELECT COALESCE(COUNT(t.id), 0) as totalTaskByStatus\n"
					+ "FROM task t \n"
					+ "JOIN project p ON p.id = t.id_project \n"
					+ "JOIN users u ON u.id = p.id_user \n"
					+ "WHERE u.id = '"+id_user+"'\n"
					+ "GROUP BY t.id_status \n"
					+ "ORDER BY t.id_status ASC;";
			break;
		case 3:
			query = "SELECT IFNULL(result.totalTaskByStatus, 0) AS totalTaskByStatus\n"
					+ "FROM (\n"
					+ "    SELECT COUNT(t.id) AS totalTaskByStatus, t.id_status\n"
					+ "    FROM task t \n"
					+ "    LEFT JOIN assigntask a ON a.id_task = t.id \n"
					+ "    LEFT JOIN users u ON u.id = a.id_user \n"
					+ "    WHERE u.id = '"+id_user+"'\n"
					+ "    GROUP BY t.id_status \n"
					+ "    ORDER BY t.id_status ASC\n"
					+ ") AS result\n"
					+ "RIGHT JOIN (\n"
					+ "    SELECT id_status\n"
					+ "    FROM task\n"
					+ "    GROUP BY id_status\n"
					+ "    LIMIT 3\n"
					+ ") AS all_statuses ON result.id_status = all_statuses.id_status;";
			break;
		default:
			break;
		}

		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				QuantityTask quantityTask = new QuantityTask();
				quantityTask.setQuantity(resultSet.getInt("totalTaskByStatus"));
				listQuantityTask.add(quantityTask);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Kết nối database lấy quantity Task  : " + e.getLocalizedMessage());
		}

		return listQuantityTask;

	}
	
	public List<Task> getAllTaskOfProject (int id_project){
		
		List<Task> listTaskOfProject = new ArrayList<Task>();
		
		String query = "SELECT t.id as id_task ,t.name ,t.start_date ,t.end_date , s.id as id_status, s.name as name_status , u.id as id_user, u.fullname \n"
				+ "FROM task t \n"
				+ "JOIN status s ON s.id = t.id_status \n"
				+ "JOIN assigntask a ON a.id_task = t.id \n"
				+ "JOIN users u ON u.id = a.id_user \n"
				+ "WHERE t.id_project = '"+id_project+"';";
		
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt("id_user"));
				user.setFullname(resultSet.getString("fullname"));
				
				Status status = new Status();
				status.setId(resultSet.getInt("id_status"));
				status.setName(resultSet.getString("name_status"));
				Task task = new Task();
				
				task.setId(resultSet.getInt("id_task"));
				task.setName(resultSet.getString("name"));
				task.setStart_date(resultSet.getString("start_date"));
				task.setEnd_date(resultSet.getString("end_date"));
				
				task.setStatus(status);
				task.setUser(user);
				
				listTaskOfProject.add(task);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Kết nối database lấy all task of project  : " + e.getLocalizedMessage());
		}

		return listTaskOfProject;
		
	}
	
}
