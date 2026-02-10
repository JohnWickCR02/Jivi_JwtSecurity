package Admin_User.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Admin_User.entity.Admin;
import Admin_User.entity.Role;
import Admin_User.entity.User;
import Admin_User.repository.AdminRepository;
import Admin_User.repository.UserRepository;

@RestController
@RequestMapping("/admin")
public class AdminContorller {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@GetMapping("/status")
	public String getStatus() {
		return "UP" ;
	}
	
	@PostMapping("/save-user")
	public User saveUser(@RequestBody User user) {
		
		if(user.getRole()==null) {
			user.setRole(Role.USER);
		}
		
		return userRepository.save(user);
	}
	
	@GetMapping("/get-lowerteam")
	public List<User> getUsers(){
		
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	@GetMapping("/get-team")
	public List<Admin> getAdmins(){
		
		List<Admin> admins = adminRepository.findAll();
		
		return admins;
	}
	
}
