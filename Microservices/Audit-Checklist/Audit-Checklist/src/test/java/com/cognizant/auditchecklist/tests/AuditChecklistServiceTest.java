package com.cognizant.auditchecklist.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.auditchecklist.dao.AuditChecklistDao;
import com.cognizant.auditchecklist.model.AuditQuestions;
import com.cognizant.auditchecklist.service.AuditChecklistServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
public class AuditChecklistServiceTest {

    @Mock
    private AuditChecklistDao auditChecklistDao;

    @InjectMocks
    private AuditChecklistServiceImpl auditChecklistServiceImpl;


    @Test
    public void testGetAuditQuestions() throws Exception{

        List<AuditQuestions> list = new ArrayList<>();

        list.add(new AuditQuestions(1,"question","Internal","yes"));
        String auditType = "Internal";
        when(auditChecklistDao.getAuditQuestions(auditType)).thenReturn(list);

       assertEquals(list, auditChecklistServiceImpl.getAuditQuestions(auditType));
        
    }
    
}
