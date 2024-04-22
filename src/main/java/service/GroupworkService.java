package service;



import java.util.List;

import emtity.PercentOfTask;
import emtity.Project;
import emtity.Task;
import emtity.User;
import repopsitory.ProjectRepository;
import repopsitory.TaskRepository;
import repopsitory.UserRepository;
import utilities.Utility;

public class GroupworkService {
	
	
	private ProjectRepository projectRepository = new ProjectRepository();
	private UserRepository userRepository = new UserRepository();
	private TaskRepository taskRepository = new TaskRepository();
	
	private Utility utility = new Utility();

	public int CallCreateProject(String tenDuAn, String ngayBatDau, String ngayKetThuc, int id_user) {
		
		
		ngayBatDau  = utility.ConvertDateTimeToTimestamp(ngayBatDau);
		ngayKetThuc = utility.ConvertDateTimeToTimestamp(ngayKetThuc);
		
		return projectRepository.CreateProject( tenDuAn, ngayBatDau, ngayKetThuc, id_user);
	}
	
	public List<Project> CallGetAllProject (int id_user, int id_role) {
		
		List<Project> listProject = projectRepository.GetAllProject(id_user, id_role);
		
		for (int i =0; i < listProject.size(); i++) {
				listProject.get(i).setStart_date(utility.convertTimestampToDateTime(listProject.get(i).getStart_date()));
				listProject.get(i).setEnd_date(utility.convertTimestampToDateTime(listProject.get(i).getEnd_date()));
		}
		
		return listProject;
	}
	
	public Project callGetProjectById(int id_project) {
		
		
		Project project = projectRepository.getProjectById(id_project);
		
		project.setStart_date(utility.convertTimestampToDateTime(project.getStart_date()));
		project.setEnd_date(utility.convertTimestampToDateTime(project.getEnd_date()));
		
		return project;
		
	}
	
	public boolean callUpdateProjectById (int id_project, String nameProject, String startDate, String endDate, int id_leader) {
		
		startDate = utility.ConvertDateTimeToTimestamp(startDate);
		endDate = utility.ConvertDateTimeToTimestamp(endDate);
		
		return projectRepository.updateProjectById(id_project, nameProject, startDate, endDate, id_leader) > 0;
	}
	
	public boolean callDeleteProjectById (int id_project) {
		return projectRepository.deleteProjectById(id_project) > 0;
	} 
	
	public List<User> callGetAllUserIsLeader(int id_user, int id_role) {
		return userRepository.getAllUserIsLeader(id_user, id_role);
	}
	
	public PercentOfTask getPercentOfTask(int id_project) {
		PercentOfTask percent = new PercentOfTask();
		int totalTaskChuaThucHien = 0;
		int totalTaskDangThucHien = 0;
		int totalTaskHoanThanh = 0;
		
		
		
		float percentOfTaskDangThucHien;
		float percentOfTaskHoanThanh;
		float percentOfTaskChuaThucHien;
		
		List<Task> listTask = taskRepository.getAllTaskOfProject(id_project);

		int totalTask = listTask.size(); 
	

		if (totalTask == 0) {
			percent.setDangThucHien("0");
			percent.setChuaThucHien("0");
			percent.setHoanThanh("0");
		} else {

			for (int i = 0; i < listTask.size(); i++) {

				if (listTask.get(i).getStatus().getId() == 1) {
					totalTaskChuaThucHien ++;
				} else if (listTask.get(i).getStatus().getId() == 2) {
					totalTaskDangThucHien++;
				} else if (listTask.get(i).getStatus().getId() == 3) {
					totalTaskHoanThanh++;
				} else {
					System.out.print("Không tìm thấy Id");
				}

			}
			
			
			
			// Để ý khi sử dụng kiểu dữ liệu tính toán 
			percentOfTaskChuaThucHien =  ((float)totalTaskChuaThucHien / (float)totalTask) * 100;
			 String percentChuaThucHien = String.format("%.2f%%",percentOfTaskChuaThucHien);
			
			percentOfTaskDangThucHien = ( (float) totalTaskDangThucHien / (float) totalTask) * 100;
			 String percentDangThucHien = String.format("%.2f%%", percentOfTaskDangThucHien);
			 
			percentOfTaskHoanThanh = ((float) totalTaskHoanThanh / (float)totalTask) * 100;
			 String percentHoanThanh = String.format("%.2f%%", percentOfTaskHoanThanh);
			

			percent.setChuaThucHien(percentChuaThucHien);
			percent.setDangThucHien(percentDangThucHien);
			percent.setHoanThanh(percentHoanThanh);

		}
		return percent;

	}
	
	public List<User> callGetAllUserOfProject(int id_project) {
		return userRepository.getAllUserOfProject(id_project);
	}
	
	public List<Task> callGetAllTaskOfProject(int id_project){
		List<Task> listTask = taskRepository.getAllTaskOfProject(id_project);
		for (int i =0; i < listTask.size(); i++) {
			listTask.get(i).setStart_date(utility.convertTimestampToDateTime(listTask.get(i).getStart_date()));
			listTask.get(i).setEnd_date(utility.convertTimestampToDateTime(listTask.get(i).getEnd_date()));
	}
		
		
		return listTask;
	}
	
}
