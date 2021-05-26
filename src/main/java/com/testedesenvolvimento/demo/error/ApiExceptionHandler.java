package com.testedesenvolvimento.demo.error;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> handleAnyException(Exception e,WebRequest request){
		var errorDetail = new DetalheError(new Date(),e.getLocalizedMessage());
		return new ResponseEntity<>(errorDetail,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = NotFoundError.class)
	public ResponseEntity<Object> handleNotFound(Exception e,WebRequest request){
		var errorDetail = new DetalheError(new Date(),e.getLocalizedMessage());
		return new ResponseEntity<>(errorDetail,new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ValidationError.class)
	public ResponseEntity<Object> handleValidationException(Exception e,WebRequest request){
		var errorDetail = new DetalheError(new Date(),e.getLocalizedMessage());
		return new ResponseEntity<>(errorDetail,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	
}
