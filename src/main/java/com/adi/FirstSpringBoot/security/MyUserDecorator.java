package com.adi.FirstSpringBoot.security;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.adi.FirstSpringBoot.model.Role;
import com.adi.FirstSpringBoot.model.User;

public class MyUserDecorator implements UserDetails {

	private User user;
	
	public MyUserDecorator(User user)
	{
		this.user= user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
         
        return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword(); 
	}

	@Override
	public String getUsername() {
		return user.getUsername(); 
	}

	@Override
	public boolean isAccountNonExpired() {
		LocalDate accExpireDate=user.getAccountExpiryDate();
		LocalDate todaysDate = LocalDate.now();
		if(todaysDate.isAfter(accExpireDate))
		{
			return false;
		}
		else 
			return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		if(user.getAccountLockStatus()==1)
		{
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		LocalDate credExpireDate=user.getCredentialsExpiryDate();
		LocalDate todaysDate = LocalDate.now();
		if(todaysDate.isAfter(credExpireDate))
		{
			return false;
		}
		else 
			return true;
	}

	@Override
	public boolean isEnabled() {
		if(user.getAccEnabledStatus()==1)
		{
			return true;
		}
		else {
			return false;
		}
			
	}

}
