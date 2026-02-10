package Admin_User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Admin_User.entity.Admin;
import Admin_User.entity.User;
import Admin_User.repository.AdminRepository;
import Admin_User.repository.UserRepository;


@Service
public class AdminDetail implements UserDetailsService {

	@Autowired
	AdminRepository adminRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Admin admin = adminRepository.findByName(username);
		
		
		
		return org.springframework.security.core.userdetails.User
				.withUsername(admin.getName())
				.password(admin.getPassword())
				.roles(admin.getRole().name())
				.build();
	}

}

