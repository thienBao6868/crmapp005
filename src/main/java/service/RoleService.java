package service;

import java.util.List;

import emtity.Role;
import repopsitory.RoleRepository;

public class RoleService {

	private RoleRepository roleRepository = new RoleRepository();

	public int callCreateRole(String name, String description) {

		return roleRepository.createRole(name, description);

	}

	public List<Role> CallGetAllRole() {
		return roleRepository.getAll();
	}
}
