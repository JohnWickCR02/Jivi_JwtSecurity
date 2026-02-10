package Admin_User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import Admin_User.entity.Ceo;
import Admin_User.repository.CeoRepository;



@Service
public class CeoDetail implements UserDetailsService {

	@Autowired
	CeoRepository ceoRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Ceo ceo = ceoRepository.findByName(username);
		
		
		
		return org.springframework.security.core.userdetails.User
				.withUsername(ceo.getName())
				.password(ceo.getPassword())
				.roles(ceo.getRole().name())
				.build();
	}

}

