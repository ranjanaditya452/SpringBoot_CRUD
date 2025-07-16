package com.adi.FirstSpringBoot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Keyboard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int keybId;
	private String keybName;
	private int price;
}
