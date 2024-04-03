package service;

import java.util.List;

import emtity.Project;
import repopsitory.ProjectRepository;

public class TaskService {

	private ProjectRepository projectRepository = new ProjectRepository();
	
	public List<Project> CallGetAllProject (){
		
		return projectRepository.GetAllProject();
	}
	
}
