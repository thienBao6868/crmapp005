package service;

import repopsitory.RoleRepository;

public class RoleService {

	private RoleRepository roleRepository = new RoleRepository();
	
	public int callCreateRole (String name, String description) {
		
		return roleRepository.createRole(name, description);
		
	}
}
