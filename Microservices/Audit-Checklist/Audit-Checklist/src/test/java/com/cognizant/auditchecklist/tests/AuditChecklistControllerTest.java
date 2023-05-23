package com.cognizant.auditchecklist.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.auditchecklist.controller.AuditChecklistController;
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
public class AuditChecklistControllerTest {
        
    @Mock
    private AuditChecklistServiceImpl auditChecklistServiceImpl;

    @InjectMocks
    private AuditChecklistController auditChecklistController;
    /**
     * 
     * to test the testGetAuditQuestions method
     */
    @Test
    public void testGetAuditQuestions() throws Exception{

        List<AuditQuestions> list = new ArrayList<>();
        list.add(new AuditQuestions(1,"question","Internal","yes"));

        String auditType = "Internal";        
        when(auditChecklistServiceImpl.getAuditQuestions(auditType)).thenReturn(list);

        assertEquals(list, auditChecklistController.getAuditQuestions("jwt", auditType).getBody());

    }

}
