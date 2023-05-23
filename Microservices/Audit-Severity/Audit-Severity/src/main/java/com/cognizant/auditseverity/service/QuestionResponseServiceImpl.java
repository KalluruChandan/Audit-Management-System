package com.cognizant.auditseverity.service;

import com.cognizant.auditseverity.exception.BenchMarkFeignException;
import com.cognizant.auditseverity.model.*;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author  somesh
 * The QuestionResponseServiceImpl class is implementation of QuestionResponseService interface.
 * This layer calls the BenchMark Microservice using openfeign client
 * In this layer all the Audit Request data will be sent to Audit Response by using AuditResponseService layer
 */

@Service
@Slf4j
public class QuestionResponseServiceImpl implements QuestionResponseService {


    @Autowired
    private AuditResponseService auditResponseService;

    @Autowired
    private BenchMarkFeignService benchMarkFeignService;

    @Override
    public AuditResponse questionresponse(AuditRequest auditRequest,String jwt) {

        log.info("inside question response service");

        List<AuditBenchMark> benchmarkList=null;
        try {
            log.info("calling benchmark feign service");
            benchmarkList = benchMarkFeignService.getAllBenchmarks(jwt);
        }
        catch (FeignException e){
            throw new BenchMarkFeignException("BenchMarkService is Down");
        }
        AuditResponse auditResponse = new AuditResponse();

        AuditType auditType = new AuditType();

        auditType.setAuditType(auditRequest.getAuditDetail().getAuditType()); // setting auditType

        // getting responses back from RequestBody
        List<AuditQuestion> questionResponses = auditRequest.getAuditDetail().getAuditQuestions();

        // create Audit-response
        auditResponse = auditResponseService.getAuditResponse(benchmarkList, auditType.getAuditType(),
                questionResponses);

        // saving response in DB
        auditResponse = auditResponseService.saveAuditResponse(auditResponse, auditRequest);
        return auditResponse;
    }
}
