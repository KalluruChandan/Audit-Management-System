package com.cognizant.auditseverity.service;

import com.cognizant.auditseverity.model.AuditRequest;
import com.cognizant.auditseverity.model.AuditResponse;

public interface QuestionResponseService {
    public AuditResponse questionresponse(AuditRequest auditRequest,String jwt);
}
