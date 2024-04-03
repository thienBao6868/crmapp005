package repopsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import config.MySQLConfig;

public class AssignTaskRepository {

	public int CreateAssignTask(int idTask, int idUser) {
		int result = 0;
		String query = "INSERT INTO assigntask (id_user,id_task,id_status) VALUES (" + idUser + "," + idTask + ",1) ";

		Connection connection = MySQLConfig.getConnection();

		try {

			PreparedStatement statement = connection.prepareStatement(query);

			result = statement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error : " + e.getLocalizedMessage());
		}

		return result;
	}

}
