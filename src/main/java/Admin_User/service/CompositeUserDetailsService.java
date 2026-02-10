package Admin_User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Admin_User.entity.Admin;
import Admin_User.entity.Ceo;
import Admin_User.repository.AdminRepository;
import Admin_User.repository.CeoRepository;

@Service
public class CompositeUserDetailsService implements UserDetailsService {

	@Autowired
	CeoRepository ceoRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		 Ceo ceo = ceoRepository.findByName(username);
	        if (ceo != null) {
	            return org.springframework.security.core.userdetails.User
	                .withUsername(ceo.getName())
	                .password(ceo.getPassword())
	                .roles(ceo.getRole().name())
	                .build();
	        }

	        Admin admin = adminRepository.findByName(username);
	        if (admin != null) {
	            return org.springframework.security.core.userdetails.User
	                .withUsername(admin.getName())
	                .password(admin.getPassword())
	                .roles(admin.getRole().name())
	                .build();
	        }

	      

	        throw new UsernameNotFoundException("User not found with username: " + username);
		
	}

}

