package com.cognizant.auditbenchmark.service;

/*
 * @Description
 * 		this is an interface for implementation of service layer
 */

import java.util.List;

import com.cognizant.auditbenchmark.model.AuditBenchmark;

public interface AuditBenchmarkService {
	
	//this method gets all the benchmark information from the database
    public List<AuditBenchmark> getAllBenchmarks();
    
}
