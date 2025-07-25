package com.adi.FirstSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adi.FirstSpringBoot.model.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
   User findByUsername(String username);
}
