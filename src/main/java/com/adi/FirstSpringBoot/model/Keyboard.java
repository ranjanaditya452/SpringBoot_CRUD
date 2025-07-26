package com.adi.FirstSpringBoot.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
