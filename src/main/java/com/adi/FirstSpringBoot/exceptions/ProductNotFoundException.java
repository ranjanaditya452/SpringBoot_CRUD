package com.adi.FirstSpringBoot.exceptions;




public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(String errmsg) {
		super(errmsg);
	}
}
