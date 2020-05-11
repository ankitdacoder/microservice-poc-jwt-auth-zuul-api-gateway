package com.webcoder.app.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest req) {

		String errorMessageDescription = ex.getLocalizedMessage();
		if (errorMessageDescription == null)
			errorMessageDescription = ex.toString();
		CustomErrorMessageModel errorMessage = new CustomErrorMessageModel(new Date(), errorMessageDescription);

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value= {NullPointerException.class,UserServiceException.class})
	public ResponseEntity<Object> handleSpecificExceptions(Exception ex, WebRequest req) {

		String errorMessageDescription = ex.getLocalizedMessage();
		if (errorMessageDescription == null)
			errorMessageDescription = ex.toString();
		CustomErrorMessageModel errorMessage = new CustomErrorMessageModel(new Date(), errorMessageDescription);

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	 * @ExceptionHandler(UserServiceException.class) public ResponseEntity<Object>
	 * handleUserServiceException(Exception ex, WebRequest req) {
	 * 
	 * String errorMessageDescription = ex.getLocalizedMessage(); if
	 * (errorMessageDescription == null) errorMessageDescription = ex.toString();
	 * CustomErrorMessageModel errorMessage = new CustomErrorMessageModel(new
	 * Date(), errorMessageDescription);
	 * 
	 * return new ResponseEntity<>(errorMessage, new HttpHeaders(),
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 */
}
