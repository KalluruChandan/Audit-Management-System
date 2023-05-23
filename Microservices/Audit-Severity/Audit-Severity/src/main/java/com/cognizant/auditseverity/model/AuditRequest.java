package com.cognizant.auditseverity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


/*
 * @author  somesh
 * AuditRequest is an entity class, it has some attributes that represents columns in database table.
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditRequest {

    @NotBlank(message="please enter projectname")
    private String projectName;

    @NotBlank(message="please enter managername")
    private String managerName;

    private AuditDetail auditDetail;
}
