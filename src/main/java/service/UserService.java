package service;

import java.util.List;

import emtity.PercentOfTask;
import emtity.Role;
import emtity.Status;
import emtity.Task;
import emtity.User;
import repopsitory.RoleRepository;
import repopsitory.StatusRepository;
import repopsitory.TaskRepository;
import repopsitory.UserRepository;
import utilities.Utility;

public class UserService {
	
	private RoleRepository roleRepository = new RoleRepository();
	
	private UserRepository userRepository = new UserRepository();
	
	private StatusRepository statusRepository = new StatusRepository();
	
	private TaskRepository taskRepository = new TaskRepository();
	
	private Utility utility = new Utility();
	
	public List<Role> getAllRole(){
		return roleRepository.getAll();
	}
	
	public int callCreateUser(String fullName, String email, String passWord, String phone, int idRole) {
		return userRepository.createUser(fullName, email, passWord, phone, idRole);
	}
	public List<User> CallGetAllUser () {
		return userRepository.GetAllUser();
	}
	
	public User callGetUserById (int id_user) {
		return userRepository.getUserById(id_user);
	}
	
	public List<Status> callGetAllStatus() {
		return statusRepository.getAllStatus();
	}
	
	public List<Task> callGetAllTaskByUser(int id_user){
		
		List<Task> listTask = taskRepository.getAllTaskByUser(id_user);
		for (int i = 0; i < listTask.size(); i++) {
			listTask.get(i).setStart_date(utility.convertTimestampToDateTime(listTask.get(i).getStart_date()));
			listTask.get(i).setEnd_date(utility.convertTimestampToDateTime(listTask.get(i).getEnd_date()));
		}
		return listTask;
	}
	public PercentOfTask getPercentOfTask(int id_user) {
		PercentOfTask percent = new PercentOfTask();
		int totalTaskChuaThucHien = 0;
		int totalTaskDangThucHien = 0;
		int totalTaskHoanThanh = 0;
		
		
		
		float percentOfTaskDangThucHien;
		float percentOfTaskHoanThanh;
		float percentOfTaskChuaThucHien;
		
		List<Task> listTask = taskRepository.getAllTaskByUser(id_user);

		int totalTask = listTask.size(); // 4 task 

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
	
}
