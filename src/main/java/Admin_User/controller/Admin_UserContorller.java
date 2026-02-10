package Admin_User.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Admin_User.entity.Ceo;
import Admin_User.entity.Role;
import Admin_User.entity.User;
import Admin_User.repository.AdminRepository;
import Admin_User.repository.CeoRepository;
import Admin_User.repository.UserRepository;

@RestController
@RequestMapping("/admin-user")
public class Admin_UserContorller {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    CeoRepository ceoRepository;

    Admin_UserContorller(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

	
	@GetMapping("/status")
	public String getStatus() {
		return "ADMIN-USER SETUP IS RUNNING ..." ;
	}
	
//	@PostMapping("/ceo-config")
//	public Ceo ceoConfig(@RequestBody Ceo ceo) {
//		
//		ceo.setPassword(passwordEncoder.encode(ceo.getPassword()));
//		if(ceo.getRole()==null) {
//			ceo.setRole(Role.CEO);
//		}
//		
//		return ceoRepository.save(ceo);
//		
//		
//	}
	
	
}
