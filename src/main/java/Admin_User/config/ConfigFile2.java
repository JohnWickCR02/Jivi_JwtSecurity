package Admin_User.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class ConfigFile2 {

	
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("").permitAll()
		.anyRequest().authenticated().and()
		.httpBasic();
		
	
		return http.build();
		
		
	}
	
	
}
