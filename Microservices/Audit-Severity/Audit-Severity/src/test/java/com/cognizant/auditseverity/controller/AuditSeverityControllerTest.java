package com.cognizant.auditseverity.controller;

import com.cognizant.auditseverity.model.*;
import com.cognizant.auditseverity.service.BenchMarkFeignService;
import com.cognizant.auditseverity.service.QuestionResponseServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
public class AuditSeverityControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @MockBean
    private BenchMarkFeignService benchMarkFeignService;

    @Autowired
    private WebApplicationContext webApplicationContext;


    List<AuditBenchMark> benchmarkList = new ArrayList<>();
    List<AuditQuestion> questions = new ArrayList<>();

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @BeforeEach
    void mockFeignClient() throws NoSuchFieldException, IllegalAccessException {
        QuestionResponseServiceImpl questionService = webApplicationContext.getBean(QuestionResponseServiceImpl.class);
        Field fieldUserApiClient = QuestionResponseServiceImpl.class.getDeclaredField("benchMarkFeignService");
        fieldUserApiClient.setAccessible(true);
        fieldUserApiClient.set(questionService, this.benchMarkFeignService);
    }

    @Test
    public void testAuditSeverity() throws Exception {

        questions.add(
                new AuditQuestion(1, "Have all Change requests followed SDLC before PROD move?", "Internal", "YES"));
        questions.add(new AuditQuestion(2, "Have all Change requests been approved by the application owner?",
                "Internal", "NO"));
        questions.add(new AuditQuestion(3, "Are all artifacts like CR document, Unit test cases available?", "Internal",
                "NO"));
        questions.add(new AuditQuestion(4, "Is the SIT and UAT sign-off available?", "Internal", "NO"));
        questions.add(new AuditQuestion(5, "Is data deletion from the system done with application owner approval?",
                "Internal", "NO"));


        AuditRequest auditRequest=new AuditRequest("project-1","som",
                new AuditDetail("Internal",new Date(),questions));

        benchmarkList.add(new AuditBenchMark(1, "Internal", 3));
        benchmarkList.add(new AuditBenchMark(2, "SOX", 1));


        Mockito.when(benchMarkFeignService.getAllBenchmarks(Mockito.any()))
                .thenReturn(benchmarkList);
        ObjectMapper mapper=new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(auditRequest);

        this.mvc.perform(post("/severity/projectexecutionstatus")
                        .header("Authorization","xxx")
                .content(jsonResponse).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".projectExecutionStatus").value("RED"))
                .andExpect(MockMvcResultMatchers.jsonPath(".remedialActionDuration").value("Action to be taken in 2 weeks"))
                .andDo(print());

    }
}
