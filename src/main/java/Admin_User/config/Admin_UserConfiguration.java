package Admin_User.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import Admin_User.service.AdminDetail;
import Admin_User.service.CeoDetail;
import Admin_User.service.CompositeUserDetailsService;
import Admin_User.service.UserDetail;

@Configuration
public class Admin_UserConfiguration {

	@Autowired
	private CompositeUserDetailsService compositeUserDetailsService;

	@Autowired
	AdminDetail adminDetail;
	
	@Autowired
	UserDetail userDetail;
	
	@Autowired
	CeoDetail ceoDetail;
	
	
	@Bean
	public SecurityFilterChain securityFilter (HttpSecurity httpRequest) throws Exception {
		
		httpRequest.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/admin-user/**").permitAll()
		.requestMatchers("/ceo/**").hasRole("CEO")
		.requestMatchers("/admin/**").hasAnyRole("CEO","ADMIN")
		.requestMatchers("/user/**").hasAnyRole("CEO","USER","ADMIN")
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and()
		.userDetailsService(compositeUserDetailsService);
		
		return httpRequest.build();
	
	    }
		
	@Bean
    public PasswordEncoder passwordEncoder() {
		
        return  new BCryptPasswordEncoder() ;
	
	}
	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//		
//        return  NoOpPasswordEncoder.getInstance() ;
//	
//	}
	
}
