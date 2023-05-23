package com.cognizant.auditseverity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuditQuestion {

    private int questionId;
    private String question;
    private String auditType;
    private String response;

}

