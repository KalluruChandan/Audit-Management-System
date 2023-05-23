package com.cognizant.auditseverity.service;

import com.cognizant.auditseverity.Dao.AuditResponseDao;
import com.cognizant.auditseverity.model.AuditBenchMark;
import com.cognizant.auditseverity.model.AuditQuestion;
import com.cognizant.auditseverity.model.AuditRequest;
import com.cognizant.auditseverity.model.AuditResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author  somesh
 * The AuditResponseServiceImpl class is implementation of AuditResponseService interface.
 * This layer calls the interface of the defined DAO layer.
 * In this layer based on predefined parameters the audit response is evaluated.
 * Audit Response status is Green - No action Needed
 * Audit Response status is Red - Action to be taken based on audit type
 */

@Service
@Slf4j
public class AuditResponseServiceImpl implements AuditResponseService{


    @Autowired
    private AuditResponseDao auditResponseDao;


    public AuditResponse getAuditResponse(List<AuditBenchMark> benchmarkList, String auditType,
                                          List<AuditQuestion> questionResponses) {

        log.info("inside audit response service");

        int No = 0;
        for (AuditBenchMark auditBenchMark : benchmarkList) {
            if (auditBenchMark.getAuditType().equalsIgnoreCase(auditType)) {
                No = auditBenchMark.getBenchmarkNoAnswers();
            }
        }

        AuditResponse auditResponse = createAuditResponse(No, questionResponses);
        return auditResponse;
    }

    public AuditResponse createAuditResponse(int acceptableNo, List<AuditQuestion> questions) {

        String auditType = questions.get(0).getAuditType();
        int actualNo = countNo(questions);
        AuditResponse auditResponse = new AuditResponse();


        if (actualNo <= acceptableNo && auditType.equalsIgnoreCase("Internal")) {
            auditResponse.setProjectExecutionStatus("GREEN");
            auditResponse.setRemedialActionDuration("No action needed");
        } else if (actualNo > acceptableNo && auditType.equalsIgnoreCase("Internal")) {
            auditResponse.setProjectExecutionStatus("RED");
            auditResponse.setRemedialActionDuration("Action to be taken in 2 weeks");
        } else if (actualNo <= acceptableNo && auditType.equalsIgnoreCase("SOX")) {
            auditResponse.setProjectExecutionStatus("GREEN");
            auditResponse.setRemedialActionDuration("No action needed");
        } else {
            auditResponse.setProjectExecutionStatus("RED");
            auditResponse.setRemedialActionDuration("Action to be taken in 1 week");
        }

        return auditResponse;
    }


    public int countNo(List<AuditQuestion> questions) {

        int count = 0;
        for (AuditQuestion auditQuestion : questions) {
            if (auditQuestion.getResponse().equalsIgnoreCase("No")) {
                count++;
            }
        }
        return count;
    }



    public AuditResponse saveAuditResponse(AuditResponse auditResponse, AuditRequest auditRequest) {

        auditResponse.setProjectName(auditRequest.getProjectName());
        auditResponse.setManagerName(auditRequest.getManagerName());
        return auditResponseDao.save(auditResponse);
    }

    public List<AuditResponse> getAllResponse(){
        return auditResponseDao.findAll();
    }
}
