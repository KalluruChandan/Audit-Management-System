package com.cognizant.auditchecklist.dao;

import java.util.List;

import com.cognizant.auditchecklist.model.AuditQuestions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * 
 * This interface communicates with db to fetch the questions.
 *
 */
@Repository
public interface AuditChecklistDao extends JpaRepository<AuditQuestions,Integer> {
    @Query(value = "select * from audit_questions where audit_type = ?1",nativeQuery = true)
    public List<AuditQuestions> getAuditQuestions(String auditType);
}
