package service;

import java.util.List;

import emtity.PercentOfTask;
import emtity.QuantityTask;
import emtity.Role;
import repopsitory.RoleRepository;
import repopsitory.TaskRepository;

public class DashboardService {

	private TaskRepository taskRpository = new TaskRepository();
	private RoleRepository roleRepository = new RoleRepository();

	public List<QuantityTask> callGetQuantityTask(int id_user, int id_role) {

		return taskRpository.getQuantityTask(id_user, id_role);
	}

	public PercentOfTask getPercentOfTask(int id_user, int id_role) {

		List<QuantityTask> listQuantityTask = taskRpository.getQuantityTask(id_user, id_role);

		PercentOfTask percent = new PercentOfTask();
		int totalTaskChuaThucHien = 0;
		int totalTaskDangThucHien = 0;
		int totalTaskHoanThanh = 0;

		float percentOfTaskDangThucHien;
		float percentOfTaskHoanThanh;
		float percentOfTaskChuaThucHien;
		
		if(listQuantityTask.get(0) != null ) {
			totalTaskChuaThucHien = listQuantityTask.get(0).getQuantity();
		}
		if(listQuantityTask.get(1) != null ) {
			totalTaskDangThucHien = listQuantityTask.get(1).getQuantity();
		}
		if(listQuantityTask.get(2) != null ) {
			totalTaskHoanThanh = listQuantityTask.get(2).getQuantity();
		}

		int totalTask = totalTaskChuaThucHien + totalTaskDangThucHien + totalTaskHoanThanh ;

		if (totalTask == 0) {
			percent.setDangThucHien("0");
			percent.setChuaThucHien("0");
			percent.setHoanThanh("0");
		} 

		// Để ý khi sử dụng kiểu dữ liệu tính toán
		percentOfTaskChuaThucHien = ((float) totalTaskChuaThucHien / (float) totalTask) * 100;
		String percentChuaThucHien = String.format("%.2f%%", percentOfTaskChuaThucHien);

		percentOfTaskDangThucHien = ((float) totalTaskDangThucHien / (float) totalTask) * 100;
		String percentDangThucHien = String.format("%.2f%%", percentOfTaskDangThucHien);

		percentOfTaskHoanThanh = ((float) totalTaskHoanThanh / (float) totalTask) * 100;
		String percentHoanThanh = String.format("%.2f%%", percentOfTaskHoanThanh);

		percent.setChuaThucHien(percentChuaThucHien);
		percent.setDangThucHien(percentDangThucHien);
		percent.setHoanThanh(percentHoanThanh);

		return percent;
	}
	
	public Role callGetRoleById(int id_role) {
		return roleRepository.getRoleById(id_role);
	}
}
