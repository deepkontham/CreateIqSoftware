package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
	
	private HttpStatus httpStatus;
	private String message;
	
	
	public BlogApiException(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}


	public BlogApiException(String message, HttpStatus httpStatus, String message1) {
		super(message);
		this.httpStatus = httpStatus;
		message = message1;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


	public String getMessage() {
		return message;
	}


	
	
	
	
	
	
	
	

}
