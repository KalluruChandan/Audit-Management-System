package com.cognizant.auditchecklist.service;

import java.util.List;

import com.cognizant.auditchecklist.model.AuditQuestions;

public interface AuditChecklistService {
    public List<AuditQuestions> getAuditQuestions(String auditType);
}
