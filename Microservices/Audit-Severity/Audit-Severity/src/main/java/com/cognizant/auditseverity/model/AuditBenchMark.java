package com.cognizant.auditseverity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditBenchMark {
    private int benchmarkId;
    private String auditType;
    private int benchmarkNoAnswers;
}
