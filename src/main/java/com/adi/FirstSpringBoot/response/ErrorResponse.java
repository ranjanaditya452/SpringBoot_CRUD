package com.adi.FirstSpringBoot.response;

import lombok.Data;

@Data
public class ErrorResponse {
	String errmsg;
	int httpStatusCode;
	boolean success;
	public ErrorResponse(String errmsg, int httpStatusCode, boolean success) {
		
		this.errmsg = errmsg;
		this.httpStatusCode = httpStatusCode;
		this.success = success;
	}
	
}
	