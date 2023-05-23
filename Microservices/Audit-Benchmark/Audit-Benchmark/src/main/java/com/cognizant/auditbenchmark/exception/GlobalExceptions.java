package com.cognizant.auditbenchmark.exception;

/*
 * @Description 
 * 		this class is used for handling the exceptions
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions extends RuntimeException{
    @ExceptionHandler(BenchmarkNotFound.class)
    public ResponseEntity<String> notFound(Exception e){
        ResponseEntity<String> entity =new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        return entity;
    }
}
