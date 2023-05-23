package com.cognizant.auditbenchmark.model;

/*
 * @Description
 * 		This class represents Audit Benchmark Entity
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuditBenchmark {

    @Id
    @GeneratedValue
    private int auditBenchmarkId;
    @NotEmpty(message = "audit type cannot be empty")
    private String auditType;
    @NotNull(message = "benchmarkNoAnswers cannot be empty")
    private int benchmarkNoAnswers;

}
