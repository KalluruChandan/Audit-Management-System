package com.cognizant.auditseverity.service;

import com.cognizant.auditseverity.model.AuditBenchMark;
import com.cognizant.auditseverity.model.AuditQuestion;
import com.cognizant.auditseverity.model.AuditResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
public class AuditResponseServiceTest {

    @InjectMocks
    AuditResponseServiceImpl auditResponseService;

    List<AuditBenchMark> benchmarkList = new ArrayList<>();
    List<AuditQuestion> questions = new ArrayList<>();
    Date date = new Date();

    @BeforeEach
    public void setUp(){
        benchmarkList.add(new AuditBenchMark(1, "Internal", 3));
        benchmarkList.add(new AuditBenchMark(2, "SOX", 1));
    }

    @Test
    @DisplayName("test- Audit type – Internal - Audit result – RED")
    public void testAuditResponseInternalRed()  {

        questions.add(
                new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "Internal", "YES"));
        questions.add(new AuditQuestion(2, "Have all Change requests been approved by the application owner?",
                "Internal", "NO"));
        questions.add(new AuditQuestion(3, "Are all artifacts like CR document, Unit test cases available?", "Internal",
                "NO"));
        questions.add(new AuditQuestion(4, "Is the SIT and UAT sign-off available?", "Internal", "NO"));
        questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
                "Internal", "NO"));


        AuditResponse auditResponse = new AuditResponse(0,  null,null,
                "RED", "Action to be taken in 2 weeks");

        assertEquals(auditResponse, auditResponseService.getAuditResponse(benchmarkList, "Internal", questions));

    }


    @Test
    @DisplayName("test- Audit type – Internal - Audit result – GREEN")
    public void testAuditResponseInternalGreen()  {

        questions.add(
                new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "Internal", "YES"));
        questions.add(new AuditQuestion(2, "Have all Change requests been approved by the application owner?",
                "Internal", "NO"));
        questions.add(new AuditQuestion(3, "Are all artifacts like CR document, Unit test cases available?", "Internal",
                "YES"));
        questions.add(new AuditQuestion(4, "Is the SIT and UAT sign-off available?", "Internal", "NO"));
        questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
                "Internal", "YES"));


        AuditResponse auditResponse = new AuditResponse(0,  null,null,
                "GREEN", "No action needed");

        assertEquals(auditResponse, auditResponseService.getAuditResponse(benchmarkList, "Internal", questions));

    }
    @Test
    @DisplayName("test- Audit type – Sox - Audit result – RED")
    public void testAuditResponseSoxRed(){
        questions.add(new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "SOX", "YES"));
        questions.add(
                new AuditQuestion(2, "Have all Change requests been approved by the application owner?", "SOX", "NO"));
        questions.add(new AuditQuestion(3,
                "For a major change, was there a database backup taken before and after PROD move?", "SOX", "NO"));
        questions.add(new AuditQuestion(4,
                "Has the application owner approval obtained while adding a user to the system?", "SOX", "YES"));
        questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
                "SOX", "YES"));

        AuditResponse auditResponse = new AuditResponse(0,null, null, "RED", "Action to be taken in 1 week");

        assertEquals(auditResponse, auditResponseService.getAuditResponse(benchmarkList, "SOX", questions));
    }


    @Test
    @DisplayName("test- Audit type – Sox - Audit result – Green")
    public void testAuditResponseSoxGreen(){
        questions.add(new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "SOX", "YES"));
        questions.add(
                new AuditQuestion(2, "Have all Change requests been approved by the application owner?", "SOX", "NO"));
        questions.add(new AuditQuestion(3,
                "For a major change, was there a database backup taken before and after PROD move?", "SOX", "YES"));
        questions.add(new AuditQuestion(4,
                "Has the application owner approval obtained while adding a user to the system?", "SOX", "YES"));
        questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
                "SOX", "YES"));

        AuditResponse auditResponse = new AuditResponse(0, null, null, "GREEN", "No action needed");

        assertEquals(auditResponse, auditResponseService.getAuditResponse(benchmarkList, "SOX", questions));
    }

}
