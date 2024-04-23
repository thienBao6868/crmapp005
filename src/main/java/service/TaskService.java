package service;

import java.util.List;

import javax.servlet.ServletException;

import emtity.Project;
import emtity.Status;
import emtity.Task;
import emtity.User;
import repopsitory.AssignTaskRepository;
import repopsitory.ProjectRepository;
import repopsitory.StatusRepository;
import repopsitory.TaskRepository;
import repopsitory.UserRepository;
import utilities.Utility;
import javax.servlet.ServletException;

public class TaskService {

	private ProjectRepository projectRepository = new ProjectRepository();
	private UserRepository userRepository = new UserRepository();
	private TaskRepository taskRepository = new TaskRepository();
	private StatusRepository statusRepository = new StatusRepository();
	private AssignTaskRepository assignTaskRepository = new AssignTaskRepository();
	private Utility utility = new Utility();

	public List<Project> CallGetAllProject(int id_user, int id_role) {

		return projectRepository.GetAllProject(id_user, id_role);
	}

	public List<User> CallGetAllUser() {
		return userRepository.GetAllUser();
	}

	public boolean CallCreateTask(String tenCongViec, int idProject, String ngayBatDau, String ngayKetThuc, int idUser)
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
		return result > 0;

	}

	public List<Task> CallGetAllTask(int id_user, int id_role) {

		List<Task> listTask = taskRepository.getAllTaskByUser(id_user, id_role);
		for (int i = 0; i < listTask.size(); i++) {
			listTask.get(i).setStart_date(utility.convertTimestampToDateTime(listTask.get(i).getStart_date()));
			listTask.get(i).setEnd_date(utility.convertTimestampToDateTime(listTask.get(i).getEnd_date()));
		}
		return listTask;
	}
	
	public List<Status> callGetAllStatus(){
		return statusRepository.getAllStatus();
	}
	
	public Task callGetTaskById(int id_task) {
		
		
		Task task = taskRepository.getTaskById(id_task);
		task.setStart_date(utility.convertTimestampToDateTime(task.getStart_date()));
		task.setEnd_date(utility.convertTimestampToDateTime(task.getEnd_date()));
		return task;
	}
	
	public boolean callUpdateTaskById (int id_task, int id_project, int id_user, int id_status, String tenCongViec,
			String start_date, String end_date) {
		start_date = utility.ConvertDateTimeToTimestamp(start_date);
		end_date = utility.ConvertDateTimeToTimestamp(end_date);
		
		return taskRepository.updateTaskById(id_task, id_project, id_user, id_status, tenCongViec, start_date, end_date) > 0;
	}
	
	public boolean callDeleteTaskById (int id_task) {
		
		return taskRepository.deleteTaskById(id_task) > 0;
	}
	
	public User callGetLeaderOfTask(int id_task) {
		return userRepository.getLeaderOfTask(id_task);
	}
	
	public List<User> callGetAllUserIsStaff () {
		return userRepository.getAllUserIsStaff();
	}
}
