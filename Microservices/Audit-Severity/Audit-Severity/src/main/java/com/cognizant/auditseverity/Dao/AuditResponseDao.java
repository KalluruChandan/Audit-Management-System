package com.cognizant.auditseverity.Dao;

import com.cognizant.auditseverity.model.AuditResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditResponseDao extends JpaRepository<AuditResponse,Integer> {
}
