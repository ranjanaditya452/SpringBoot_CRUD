package com.adi.FirstSpringBoot.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	
	String username;
	
	String password; 
	
	LocalDate accountExpiryDate;
	
	int accountLockStatus;//1-unlock,0-locked
	
	LocalDate credentialsExpiryDate;
	
	int accEnabledStatus; //1--unlock,0-locked
	
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
	           name = "users_roles",
	           joinColumns = @JoinColumn(name = "fkuser_id"),
	           inverseJoinColumns = @JoinColumn(name = "fkrole_id")
	           )
	List<Role> roles;
}
