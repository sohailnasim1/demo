package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.domain.BookAlreadyExistsException;
import com.example.demo.domain.BookNotFoundException;

@RestControllerAdvice
public class BookControllerAdvice {
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String bookNotFoundHandler(BookNotFoundException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(BookAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	String bookAlreadyExistsHandler(BookAlreadyExistsException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	Map<String,String> validationExceptionHandler(MethodArgumentNotValidException e) {
		Map<String, String> errorMap = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(err->{
		String fieldName = ((FieldError)err).getField();
		String errorMessage = err.getDefaultMessage();
		errorMap.put(fieldName, errorMessage);
		});
		return errorMap;
	}
}
