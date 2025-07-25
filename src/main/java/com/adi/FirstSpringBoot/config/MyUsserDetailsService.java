package com.adi.FirstSpringBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adi.FirstSpringBoot.model.User;
import com.adi.FirstSpringBoot.repository.UserRepository;
import com.adi.FirstSpringBoot.security.MyUserDecorator;

@Service
public class MyUsserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
	User user=	userRepository.findByUsername(username);
	if(user==null)
	{
		throw new UsernameNotFoundException("User doesn't exit");
	}
	
	 	return new MyUserDecorator(user);
	}

}
