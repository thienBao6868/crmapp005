package service;



import repopsitory.ProjectRepository;
import utilities.Utility;

public class GroupworkService {
	
	
	private ProjectRepository projectRepository = new ProjectRepository();
	
	private Utility utility = new Utility();

	public int CallCreateProject(String tenDuAn, String ngayBatDau, String ngayKetThuc) {
		
		
		ngayBatDau  = utility.ConvertStringToTimestamp(ngayBatDau);
		ngayKetThuc = utility.ConvertStringToTimestamp(ngayKetThuc);
		
		return projectRepository.CreateProject( tenDuAn, ngayBatDau, ngayKetThuc);
	}

}
