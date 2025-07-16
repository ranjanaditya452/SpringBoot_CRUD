package com.adi.FirstSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adi.FirstSpringBoot.model.Keyboard;
import com.adi.FirstSpringBoot.repository.KeyboardRepository;

@Service
public class KeyboardService {

	@Autowired
	KeyboardRepository keyboardRepository;
	
	public void addKeyboard(Keyboard keyboard) {
		keyboardRepository.save(keyboard);
	}
	
	public Keyboard getKeyboard(int id) {
		return keyboardRepository.findById(id).get();
	}
}
