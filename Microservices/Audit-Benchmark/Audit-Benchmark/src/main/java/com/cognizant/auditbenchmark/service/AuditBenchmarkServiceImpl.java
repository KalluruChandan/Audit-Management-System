package com.cognizant.auditbenchmark.service;
/*
 * @Description
 * 	Service class of the audit benchmark service
 * 	This class contains the business logic for the audit benchmark service
 * 
 * 
 */
import java.util.List;

import com.cognizant.auditbenchmark.dao.AuditBenchmarkDao;
import com.cognizant.auditbenchmark.model.AuditBenchmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditBenchmarkServiceImpl implements AuditBenchmarkService{

    @Autowired
    private AuditBenchmarkDao auditBenchmarkDao;//Autowiring with AuditBenchmarkDao interface

    @Override
    public List<AuditBenchmark> getAllBenchmarks() {
        return auditBenchmarkDao.findAll();//This should call findAll method in AuditBenchmarkDao interface
    }
    
}
