package service;

import java.util.List;

import javax.servlet.ServletException;

import emtity.Project;
import emtity.User;
import repopsitory.AssignTaskRepository;
import repopsitory.ProjectRepository;
import repopsitory.TaskRepository;
import repopsitory.UserRepository;
import utilities.Utility;
import javax.servlet.ServletException;

public class TaskService {

	private ProjectRepository projectRepository = new ProjectRepository();
	private UserRepository userRepository = new UserRepository();
	private TaskRepository taskRepository = new TaskRepository();
	private AssignTaskRepository  assignTaskRepository =  new AssignTaskRepository ();
	private Utility utility = new Utility();

	public List<Project> CallGetAllProject() {

		return projectRepository.GetAllProject();
	}

	public List<User> CallGetAllUser() {
		return userRepository.GetAllUser();
	}

	public int CallCreateTask(String tenCongViec, int idProject, String ngayBatDau, String ngayKetThuc, int idUser) throws ServletException {
		
		int result = -1 ;
		ngayBatDau = utility.ConvertStringToTimestamp(ngayBatDau);
		ngayKetThuc = utility.ConvertStringToTimestamp(ngayKetThuc);

		int newTaskId = taskRepository.createTask(tenCongViec, idProject, ngayBatDau, ngayKetThuc);

		if (newTaskId != -1) {
			result = assignTaskRepository.CreateAssignTask(newTaskId, idUser);
		} else {
			throw new ServletException("Failed to insert assignTask into database");
		}
		return result;
		
	}
}
