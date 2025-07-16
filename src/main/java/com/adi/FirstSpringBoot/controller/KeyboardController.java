package com.adi.FirstSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adi.FirstSpringBoot.model.Keyboard;
import com.adi.FirstSpringBoot.service.KeyboardService;


@RestController
public class KeyboardController {

	@Autowired
	KeyboardService keyboardService;
	
	@GetMapping("/addKeyboard")
	public String addKeyboard() {
		
		Keyboard keyboard = new Keyboard(1,"Logitech",3000);
		keyboardService.addKeyboard(keyboard);
		return "Keyboard Added";
	}
	
	@GetMapping("/getKeyboard")
	public Keyboard getKeyboard() {
		return keyboardService.getKeyboard(1);
	}
}
