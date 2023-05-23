package com.cognizant.auditchecklist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * This class handles all the exceptions. Whenever an exception occurs
 * */
@ControllerAdvice
public class GlobalExceptions extends RuntimeException{
	
	@ExceptionHandler(QuestionsNotFound.class)
    public ResponseEntity<String> notFound(Exception e){
        ResponseEntity<String> entity =new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        return entity;
    }
}
