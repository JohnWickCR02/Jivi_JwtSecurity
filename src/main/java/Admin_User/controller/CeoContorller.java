package Admin_User.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Admin_User.entity.Admin;
import Admin_User.entity.Role;
import Admin_User.repository.AdminRepository;


@RestController
@RequestMapping("/ceo")
public class CeoContorller {

    private final PasswordEncoder passwordEncoder;

	@Autowired
	AdminRepository adminRepository;


    CeoContorller(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	
	@GetMapping("/status")
	public String getStatus() {
		return "CEO IS UP" ;
	}
	
	@PostMapping("/save-admin")
	public Admin saveUser(@RequestBody Admin admin) {
		
		if(admin.getRole()==null) {
			admin.setRole(Role.ADMIN);
		}
		
		//admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		
		return adminRepository.save(admin);
	}
	
	@GetMapping("/getAllAdmin")
	public List<Admin> getTeamMembers(){
		
		List<Admin> admins = adminRepository.findAll();
		
		return admins;
	}
	
	
}
