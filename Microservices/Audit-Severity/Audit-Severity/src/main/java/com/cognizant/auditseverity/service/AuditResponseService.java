package com.cognizant.auditseverity.service;

import com.cognizant.auditseverity.model.AuditBenchMark;
import com.cognizant.auditseverity.model.AuditQuestion;
import com.cognizant.auditseverity.model.AuditRequest;
import com.cognizant.auditseverity.model.AuditResponse;

import java.util.List;

public interface AuditResponseService {

    public AuditResponse getAuditResponse(List<AuditBenchMark> benchmarkList, String auditType,
                                          List<AuditQuestion> questionResponses);

    public AuditResponse saveAuditResponse(AuditResponse auditResponse, AuditRequest auditRequest);
}
