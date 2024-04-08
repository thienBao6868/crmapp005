package service;



import java.util.List;

import emtity.Project;
import repopsitory.ProjectRepository;
import utilities.Utility;

public class GroupworkService {
	
	
	private ProjectRepository projectRepository = new ProjectRepository();
	
	private Utility utility = new Utility();

	public int CallCreateProject(String tenDuAn, String ngayBatDau, String ngayKetThuc) {
		
		
		ngayBatDau  = utility.ConvertDateTimeToTimestamp(ngayBatDau);
		ngayKetThuc = utility.ConvertDateTimeToTimestamp(ngayKetThuc);
		
		return projectRepository.CreateProject( tenDuAn, ngayBatDau, ngayKetThuc);
	}
	
	public List<Project> CallGetAllProject () {
		
		List<Project> listProject = projectRepository.GetAllProject();
		
		for (int i =0; i < listProject.size(); i++) {
				listProject.get(i).setStart_date(utility.convertTimestampToDateTime(listProject.get(i).getStart_date()));
				listProject.get(i).setEnd_date(utility.convertTimestampToDateTime(listProject.get(i).getEnd_date()));
		}
		
		return listProject;
	}

}
