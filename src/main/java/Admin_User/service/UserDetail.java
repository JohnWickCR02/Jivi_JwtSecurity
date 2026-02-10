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
public class UserDetail implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByName(username);
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getName())
				.roles(user.getRole().name())
				.build();
	}

}

