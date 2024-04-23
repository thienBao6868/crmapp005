package service;

import java.util.List;

import emtity.Role;
import repopsitory.RoleRepository;

public class RoleService {

	private RoleRepository roleRepository = new RoleRepository();

	public boolean callCreateRole(String name, String description) {

		return roleRepository.createRole(name, description) > 0;

	}

	public List<Role> CallGetAllRole() {
		return roleRepository.getAll();
	}

	public Role callGetRoleById(int id_role) {

		return roleRepository.getRoleById(id_role);
	}

	public boolean callUpdateRoleById(int id_role, String name, String description) {

		return roleRepository.updateRoleById(id_role, name, description) > 0;
	}
	
	public boolean callDeleteRoleById(int id_role) {
		return roleRepository.deleteRoleById(id_role) >0 ;
	}
	
	public boolean callCheckUserExists (String name) {
		return roleRepository.checkUserExists(name);
	}
}
