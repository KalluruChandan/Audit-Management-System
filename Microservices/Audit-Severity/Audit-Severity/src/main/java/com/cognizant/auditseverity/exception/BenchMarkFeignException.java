package com.cognizant.auditseverity.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/*
 * @author  somesh
 * BenchMarkFeignException is a custom Exception.
 * It activates when fiegn call doesn't respond(benchmark microservice is inactive).
 */


@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Benchmarkservice is not active")
public class BenchMarkFeignException extends RuntimeException {
    public BenchMarkFeignException(String s){
        super(s);
    }
}
