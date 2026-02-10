package Admin_User.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Admin_User.entity.Role;
import Admin_User.entity.User;
import Admin_User.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserContorller {

	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/status")
	public String getStatus() {
		return "UP" ;
	}
	
//	@PostMapping("/save-user")
//	public User saveUser(@RequestBody User user) {
//		
//		if(user.getRole()==null) {
//			user.setRole(Role.USER);
//		}
//		
//		return userRepository.save(user);
//	}
	
	@GetMapping("/get-team")
	public List<User> getTeamMembers(){
		
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	
}
