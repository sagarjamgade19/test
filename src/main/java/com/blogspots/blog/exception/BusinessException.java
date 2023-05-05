package com.blogspots.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class BusinessException {
	@ResponseStatus()
	@ExceptionHandler(ResourceNotFoundException.class)
	public Map<String, String>resourceNotFound(ResourceNotFoundException exception) {
		
		HashMap<String, String>hm=new HashMap<>();
		hm.put("Error Message", exception.getMessage());
		return hm;
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		
		HashMap<String, String>hm=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			hm.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String, String>>(hm, HttpStatus.BAD_REQUEST);
	}
	
	/*@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		
		HashMap<String, String>hm=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			hm.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String, String>>(hm, HttpStatus.BAD_REQUEST);*/
}
