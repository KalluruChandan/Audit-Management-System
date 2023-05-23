package com.cognizant.auditseverity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * @author  somesh
 * AuditResponse is an entity class, it has some attributes that represents columns in database table.
 */

@Entity
@Table(name="audit_response")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditResponse {

    @Id
    @GeneratedValue
    private int auditId;
    private String managerName;
    private String projectName;
    private String projectExecutionStatus;
    private String remedialActionDuration;
}
