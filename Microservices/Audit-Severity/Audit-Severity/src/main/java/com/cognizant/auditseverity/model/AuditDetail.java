package com.cognizant.auditseverity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuditDetail {

    private String auditType;
    private Date auditDate;
    private List<AuditQuestion> auditQuestions;
}
