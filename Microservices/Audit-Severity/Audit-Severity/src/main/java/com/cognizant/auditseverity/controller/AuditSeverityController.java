package com.cognizant.auditseverity.controller;

import com.cognizant.auditseverity.model.AuditRequest;
import com.cognizant.auditseverity.model.AuditResponse;
import com.cognizant.auditseverity.service.AuditResponseServiceImpl;
import com.cognizant.auditseverity.service.QuestionResponseServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @author  somesh
 * The AuditSeverityController class is responsible for control of specific business
 * module process(To Evaluate Project Execution Status).
 * In this layer, the interface of Service layer is called to control the business process.
 */

@RestController
@RequestMapping("/severity")
@CrossOrigin(origins = "*")
@Slf4j
public class AuditSeverityController {


    @Autowired
    private QuestionResponseServiceImpl questionResponseService;

    @Autowired
    private AuditResponseServiceImpl auditResponseService;

    @PostMapping("/projectexecutionstatus")
    @ApiOperation(value = "save auditresponse into database",
                 notes="Gets the audit response and analyzes the project execution status",
                 response=QuestionResponseServiceImpl.class)
    public ResponseEntity<?> auditSeverity(@RequestHeader("Authorization") String jwt,@RequestBody AuditRequest auditRequest) {

        AuditResponse   auditResponse = questionResponseService.questionresponse(auditRequest,jwt);
        log.info("inside audit severity controller");
        ResponseEntity<AuditResponse>    response = new ResponseEntity<>(auditResponse, HttpStatus.OK);

        return response;

    }

    @GetMapping("/responses")
    @ApiOperation(value = "gets the audit responses",
            notes="returns saved auditresponses from database",
            response=AuditResponseServiceImpl.class)
    public ResponseEntity<List<AuditResponse>> getAllAuditResponses(){
        List<AuditResponse> auditResponses= auditResponseService.getAllResponse();
        return new ResponseEntity<List<AuditResponse>>(auditResponses,HttpStatus.OK);
    }
}
