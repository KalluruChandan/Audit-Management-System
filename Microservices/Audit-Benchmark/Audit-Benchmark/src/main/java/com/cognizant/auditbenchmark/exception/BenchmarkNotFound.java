package com.cognizant.auditbenchmark.exception;

/*
 * @Description
 * 		this is a custom exception extends RuntimeException
 */

public class BenchmarkNotFound extends RuntimeException{

    public BenchmarkNotFound(String message){
        super(message);
    }
    
}
