package com.adi.FirstSpringBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class MyWebConfiguration{

		
		
		
		
//		auth.inMemoryAuthentication().withUser("Adi").password("Tya").authorities("ADMIN")
//		.and().withUser("Niks").password("tera").authorities("USER");
	
	
	@Bean
	public AuthenticationProvider myAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(myUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(mySetPasswordEncoder());
		return daoAuthenticationProvider;
		
	}

	@Bean
	public PasswordEncoder mySetPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService myUserDetailsService() {
		
		return new MyUsserDetailsService();
	}

	
		@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
	        .requestMatchers("/","/addProductFE","/books/showFormForAdd","/403").hasAnyAuthority("USER","ADMIN")
	        .requestMatchers("/updateProductFE/**", "/updateProductFormFE/**", "/deleteProductFE/**").hasAuthority("ADMIN")
	        .anyRequest().authenticated()
	        .and()
	        .formLogin().loginProcessingUrl("/login").successForwardUrl("/").permitAll()
	        .and() 
	        .logout().logoutSuccessUrl("/login").permitAll()
	        .and()
	        .exceptionHandling().accessDeniedPage("/403")
	        .and()
	        .cors().and().csrf().disable();

	        http.authenticationProvider(myAuthenticationProvider());
	        return http.build();
	    }
	
	
	
	
	
	
//	@Bean
// 	public PasswordEncoder getPasswordEncoder()
// 	{
// 		return NoOpPasswordEncoder.getInstance();
// 	}
	
	
}
