package com.adi.FirstSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adi.FirstSpringBoot.model.Keyboard;

@Repository
public interface KeyboardRepository extends JpaRepository<Keyboard, Integer> {

}
