package com.cognizant.auditbenchmark.dao;

/*
 * @Description
 * 		This class connects to the database and 
 * 		allows us doing the crud operations
 */

import com.cognizant.auditbenchmark.model.AuditBenchmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditBenchmarkDao extends JpaRepository<AuditBenchmark,Integer> {
    
}
