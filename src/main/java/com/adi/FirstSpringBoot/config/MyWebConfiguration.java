package com.adi.FirstSpringBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.adi.FirstSpringBoot.config.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class MyWebConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	auth.authenticationProvider(myAuthenticationProvider());
		
		
		
		
		
//		auth.inMemoryAuthentication().withUser("Adi").password("Tya").authorities("ADMIN")
//		.and().withUser("Niks").password("tera").authorities("USER");
	}
	
	private AuthenticationProvider myAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(myUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(mySetPasswordEncoder());
		return daoAuthenticationProvider;
		
	}

	private PasswordEncoder mySetPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private UserDetailsService myUserDetailsService() {
		
		return new MyUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/","/addProductFE","/books/showFormForAdd","/403").hasAnyAuthority("USER","ADMIN")
        .antMatchers("/updateProductFE/**", "/updateProductFormFE/**", "/deleteProductFE/**").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin().loginProcessingUrl("/login").successForwardUrl("/").permitAll()
        .and()
        .logout().logoutSuccessUrl("/login").permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/403")
        .and()
        .cors().and().csrf().disable();
	}
	
	
	
	
	
	
	
//	@Bean
// 	public PasswordEncoder getPasswordEncoder()
// 	{
// 		return NoOpPasswordEncoder.getInstance();
// 	}
	
	
}
