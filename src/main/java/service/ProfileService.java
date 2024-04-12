package service;

import java.util.List;

import emtity.PercentOfTask;
import emtity.Status;
import emtity.Task;
import emtity.User;
import repopsitory.StatusRepository;
import repopsitory.TaskRepository;
import repopsitory.UserRepository;
import utilities.Utility;

public class ProfileService {

	private TaskRepository taskRepository = new TaskRepository();
	private UserRepository userRepository = new UserRepository();
	private StatusRepository statusRepository = new StatusRepository();
	private Utility utility = new Utility();

	public List<Task> callGetAllTaskByUser(int id_user) {

		List<Task> listTask = taskRepository.getAllTaskByUser(id_user);
		for (int i = 0; i < listTask.size(); i++) {
			listTask.get(i).setStart_date(utility.convertTimestampToDateTime(listTask.get(i).getStart_date()));
			listTask.get(i).setEnd_date(utility.convertTimestampToDateTime(listTask.get(i).getEnd_date()));
		}
		return listTask;

	}

	public PercentOfTask getPercentOfTask(int id_user) {
		PercentOfTask percent = new PercentOfTask();
		int totalTaskHoanThanh = 0;
		int totalTaskChuaThucHien = 0;
		int totalTaskDangThucHien = 0;
		float percentOfTaskDangThucHien;
		float percentOfTaskHoanThanh;
		float percentOfTaskChuaThucHien;
		List<Task> listTask = taskRepository.getAllTaskByUser(id_user);

		int totalTask = listTask.size();

		if (totalTask == 0) {
			percent.setDangThucHien("0");
			percent.setChuaThucHien("0");
			percent.setHoanThanh("0");
		} else {

			for (int i = 0; i < listTask.size(); i++) {

				if (listTask.get(i).getStatus().getId() == 1) {
					totalTaskDangThucHien++ ;
				} else if (listTask.get(i).getStatus().getId() == 2) {
					totalTaskHoanThanh++;
				} else if (listTask.get(i).getStatus().getId() == 3) {
					totalTaskChuaThucHien++;
				} else {
					System.out.print("Không tìm thấy Id");
				}

			}
			
			// Để ý khi sử dụng kiểu dữ liệu tính toán 
			percentOfTaskDangThucHien = ( (float) totalTaskDangThucHien / (float) totalTask) * 100;
			 String percentString = String.format("%.2f%%", percentOfTaskDangThucHien);
			percentOfTaskHoanThanh = ((float) totalTaskHoanThanh / (float)totalTask) * 100;
			 String percentString1 = String.format("%.2f%%", percentOfTaskHoanThanh);
			percentOfTaskChuaThucHien =  ((float)totalTaskChuaThucHien / (float)totalTask) * 100;
			 String percentString2 = String.format("%.2f%%",percentOfTaskChuaThucHien);

			percent.setDangThucHien(percentString);
			percent.setChuaThucHien(percentString1);
			percent.setHoanThanh(percentString2);

		}
		return percent;

	}

	public User callGetUserById(int id_user) {
		return userRepository.getUserById(id_user);
	}
	
	public List<Status> callGetAllStatus () {
		return statusRepository.getAllStatus();
	}
	
	public Task callGetTaskById(int id_task) {
		
		
		Task task = taskRepository.getTaskById(id_task);
		
		task.setStart_date(utility.convertTimestampToDateTime(task.getStart_date()));
		task.setEnd_date(utility.convertTimestampToDateTime(task.getEnd_date()));
		return task ;
	}
}
