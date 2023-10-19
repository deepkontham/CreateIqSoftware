package com.example.demo.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler{

	 @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> HandleResourcenotFound(ResourceNotFoundException exception,WebRequest request){
		
		ErrorDetails errordetails=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<>(errordetails,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(BlogApiException.class)
	public ResponseEntity<ErrorDetails> HandleBlogApiException(BlogApiException exception,WebRequest request){
		
		ErrorDetails errordetails=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<>(errordetails,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> HandleGlobalException(Exception exception,WebRequest request){
		
		ErrorDetails errordetails=new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<>(errordetails,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map< String, String> valids=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)-> {
			String fieldname=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			valids.put(fieldname, message);
			
		});
		return new ResponseEntity<Object>(valids,HttpStatus.BAD_REQUEST);
		}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException ex,WebRequest request){
		
		ErrorDetails errorDetails=new ErrorDetails(new Date(), ex.getMessage(),request.getDescription(false));
	  return new ResponseEntity<>(errorDetails,HttpStatus.UNAUTHORIZED);
	}
	
	} 
	
	


