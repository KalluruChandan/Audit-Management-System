package com.cognizant.auditchecklist.service;

import java.util.List;

import com.cognizant.auditchecklist.dao.AuditChecklistDao;
import com.cognizant.auditchecklist.model.AuditQuestions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**

* 			This class is used to load the questions from database and
*          checking whether the question resides in database or not based on the
*          given audit type.
*
*/
@Service
public class AuditChecklistServiceImpl implements AuditChecklistService {

    @Autowired
    private AuditChecklistDao auditChecklistDao;

    @Override
    public List<AuditQuestions> getAuditQuestions(String auditType) {
        // TODO Auto-generated method stub
        return auditChecklistDao.getAuditQuestions(auditType);
    }
    
}
