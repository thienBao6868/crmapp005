package service;

import java.util.List;

import javax.servlet.ServletException;

import emtity.Project;
import emtity.Task;
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
	private AssignTaskRepository assignTaskRepository = new AssignTaskRepository();
	private Utility utility = new Utility();

	public List<Project> CallGetAllProject() {

		return projectRepository.GetAllProject();
	}

	public List<User> CallGetAllUser() {
		return userRepository.GetAllUser();
	}

	public int CallCreateTask(String tenCongViec, int idProject, String ngayBatDau, String ngayKetThuc, int idUser)
			throws ServletException {

		int result = -1;
		ngayBatDau = utility.ConvertDateTimeToTimestamp(ngayBatDau);
		ngayKetThuc = utility.ConvertDateTimeToTimestamp(ngayKetThuc);

		int newTaskId = taskRepository.createTask(tenCongViec, idProject, ngayBatDau, ngayKetThuc);

		if (newTaskId != -1) {
			result = assignTaskRepository.CreateAssignTask(newTaskId, idUser);
		} else {
			throw new ServletException("Failed to insert assignTask into database");
		}
		return result;

	}

	public List<Task> CallGetAllTask() {

		List<Task> listTask = taskRepository.GetAllTask();
		for (int i = 0; i < listTask.size(); i++) {
			listTask.get(i).setStart_date(utility.convertTimestampToDateTime(listTask.get(i).getStart_date()));
			listTask.get(i).setEnd_date(utility.convertTimestampToDateTime(listTask.get(i).getEnd_date()));
		}
		return listTask;
	}
}
