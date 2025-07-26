package com.adi.FirstSpringBoot.model;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 int productId;
	
	@NotNull(message = "Product title cannot be null") 
	 @Size(min =3, message = "Product title must contain at least 3 characters") 
	 String productTitle;
	
	 @NotNull(message = "Desc cannot be null") 
	 String productDescription;
	
	 @NotNull(message = "Category cannot be null") 
	 String productCategory;
	 
	 @NotNull(message = "Price cannot be null") 
	 @Min(value = 100, message = "Price must be at least 100") 
	 @Max(value = 50000, message = "Price must be less than or equal to 50,000") 
	 @Positive
	 double productPrice;
	 
	 private LocalDateTime createdAt;
	 
	 private LocalDateTime updatedAt;
	 
	 
	 @PrePersist
	 protected void atCreation() {
		 LocalDateTime now = LocalDateTime.now();
		 this.createdAt=now;
		 this.updatedAt=now;
	 }
	 
	 @PreUpdate
	 protected void atUpdation() {
		 LocalDateTime now= LocalDateTime.now();
		 this.updatedAt=now;
	 }
}
