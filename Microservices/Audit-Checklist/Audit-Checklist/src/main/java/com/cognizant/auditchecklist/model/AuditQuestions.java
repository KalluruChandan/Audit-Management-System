package com.cognizant.auditchecklist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
This class is an entity which we will be storing in the database.
  
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuditQuestions {
	
	   
    @Id
    @GeneratedValue
    private int questionId;
    @NotBlank(message = "Question cannot be empty")
    private String question;
    @NotBlank(message = "audit type cannot be empty")
    private String auditType;
    private String response;
}
