package service;

import java.util.List;

import emtity.Project;
import emtity.User;
import repopsitory.ProjectRepository;
import repopsitory.TaskRepository;
import repopsitory.UserRepository;
import utilities.Utility;

public class TaskService {

	private ProjectRepository projectRepository = new ProjectRepository();
	private UserRepository userRepository = new UserRepository();
	private TaskRepository taskRepository = new TaskRepository();
	private Utility utility = new Utility();
	
	public List<Project> CallGetAllProject (){
		
		return projectRepository.GetAllProject();
	}
	
	public List<User> CallGetAllUser () {
		return userRepository.GetAllUser();
	}
	
	public int CallCreateTask (String tenCongViec, int idProject,String ngayBatDau, String ngayKetThuc,int idUser) {
		
		ngayBatDau = utility.ConvertStringToTimestamp(ngayBatDau);
		ngayKetThuc = utility.ConvertStringToTimestamp(ngayKetThuc);
		
		return taskRepository.createTask(tenCongViec, idProject,ngayBatDau , ngayKetThuc, idUser);
	}
}
