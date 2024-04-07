package service;

import java.util.List;

import emtity.Role;
import emtity.User;
import repopsitory.RoleRepository;
import repopsitory.UserRepository;

public class UserService {
	
	private RoleRepository roleRepository = new RoleRepository();
	
	private UserRepository userRepository = new UserRepository();
	
	public List<Role> getAllRole(){
		return roleRepository.getAll();
	}
	
	public int callCreateUser(String fullName, String email, String passWord, String phone, int idRole) {
		return userRepository.createUser(fullName, email, passWord, phone, idRole);
	}
	public List<User> CallGetAllUser () {
		return userRepository.GetAllUser();
	}
	
}
