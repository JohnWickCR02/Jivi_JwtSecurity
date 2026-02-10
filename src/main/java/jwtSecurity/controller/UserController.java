package jwtSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwtSecurity.entity.Roles;
import jwtSecurity.entity.User;
import jwtSecurity.repository.UserRepo;
import jwtSecurity.service.JwtService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder ;
	
	@Autowired
    JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@GetMapping("/status")
	public String getStatus () {
		return "UP";
	}
	
	@PostMapping("/save")
	public User saveUser ( @RequestBody User user1) {
		
		User user2 = user1 ;
		
		user2.setRole(Roles.USER);
		user2.setPassword(passwordEncoder.encode(user1.getPassword()));
		
		return userRepo.save(user2);
	}
	
	
	
	@GetMapping("/validate")
	public String getValid (Authentication authentication) {
		return "VALID USER " + authentication.getName() ;
	}
	
	
//	@PostMapping("/token")
//	public String loginToken (@RequestBody User user) {
//		User userFromDb = userRepo.findByEmail(user.getEmail());
//		
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword())) ;
//		
//		if (authentication.isAuthenticated()) {
//            // If valid, generate and return the token
//            return jwtService.generateToken(userFromDb.getEmail(),userFromDb.getRole().name());
//        } else {
//            return "Authentication Failed!";
//        }
//		
//	}
	
	
//	@PostMapping("/token")
//	public String loginToken(Authentication authentication) {
//
//	    String email = authentication.getName();
//
//	    User userFromDb = userRepo.findByEmail(email);
//	    
//	    return jwtService.generateToken(email, userFromDb.getRole().name());
//	}
	
	
	@PostMapping("/token")
	public String loginToken(Authentication authentication) {
	    if (authentication == null || !authentication.isAuthenticated()) {
	        return "Authentication required!";
	    }

	    String email = authentication.getName();
	    User userFromDb = userRepo.findByEmail(email);

	    // Safety check: if the database search fails for some reason
	    if (userFromDb == null) {
	        return "User not found in database for email: " + email;
	    }

	    // Generate token using the email and the Role from DB
	    return jwtService.generateToken(email, userFromDb.getRole().name());
	}
	
	
}
