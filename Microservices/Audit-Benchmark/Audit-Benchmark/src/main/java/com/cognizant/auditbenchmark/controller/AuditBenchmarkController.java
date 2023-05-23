package com.cognizant.auditbenchmark.controller;
/*
 * @Description
 * 	this is the controller layer
 * 		Controller for audit benchmark
 * 		This controller got one endpoint
 * 		requests will be routed here
 * 
 */
import java.util.List;

import com.cognizant.auditbenchmark.exception.BenchmarkNotFound;
import com.cognizant.auditbenchmark.model.AuditBenchmark;
import com.cognizant.auditbenchmark.service.AuditBenchmarkService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/benchmark")
@Slf4j
public class AuditBenchmarkController{

    @Autowired
    private AuditBenchmarkService auditBenchmarkService;

    @GetMapping("/AuditBenchmark")
    @ApiOperation(value = "gets auditbenchmark from database",
    notes="This Microservice should provide the acceptable benchmark value for every audit type. It should return a Dictionary of values with AuditType and Acceptable benchmark value of number of questions whose answers can be NO",
    response=AuditBenchmarkService.class)
    public ResponseEntity<List<AuditBenchmark>> getAllBenchmarks(@RequestHeader("Authorization") String jwt) {
    	//this method gets all the benchmarks set for the different Audit types
    		log.debug("inside the get request");    	
    		log.info(jwt);
            List<AuditBenchmark> benchmarks = auditBenchmarkService.getAllBenchmarks();
            if(!benchmarks.isEmpty()){
                ResponseEntity<List<AuditBenchmark>> entity = ResponseEntity.status(HttpStatus.OK).body(benchmarks);
                return entity;
            }
            else{
                throw new BenchmarkNotFound("No benchMark Found");//throw exception if no benchmarks found
            }
            
    }
    
}
