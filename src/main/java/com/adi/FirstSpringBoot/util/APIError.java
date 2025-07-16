package com.adi.FirstSpringBoot.util;

import lombok.Data;

@Data
public class APIError {

	  private String message;
	    private String field;
	    private Object rejectedValue;

	    public APIError(String message, String field, Object rejectedValue) {
	        this.message = message;
	        this.field = field;
	        this.rejectedValue = rejectedValue;
	    }
}
