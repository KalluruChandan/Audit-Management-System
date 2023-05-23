package com.cognizant.auditbenchmark.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// import com.cognizant.auditbenchmark.config.JwtFilter;
import com.cognizant.auditbenchmark.controller.AuditBenchmarkController;
import com.cognizant.auditbenchmark.model.AuditBenchmark;
import com.cognizant.auditbenchmark.service.AuditBenchmarkService;

// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
// @WebMvcTest(controllers = AuditBenchmarkController.class)
public class AuditBenchmarkControllerTest {

    @Mock
    private AuditBenchmarkService auditBenchmarkService;

    @InjectMocks
    private AuditBenchmarkController auditBenchmarkControllerImpl;

    @Test
    public void testGetAllBenchmarks() throws Exception{

        List<AuditBenchmark> list = getData();
        when(auditBenchmarkService.getAllBenchmarks()).thenReturn(list);

       assertEquals(list, auditBenchmarkControllerImpl.getAllBenchmarks("jwt").getBody());
        
    }

    // @Autowired
    // private MockMvc mockMvc;

    // @Autowired
    // private JwtFilter jwtFilter;

    // @Autowired
	// private WebApplicationContext context;

    // @BeforeEach
    // public void setUp(){
    //     mockMvc= MockMvcBuilders.webAppContextSetup(context).addFilter(jwtFilter).build();
    // }

    // @Test
    // public void testGetAllBenchmarks() throws Exception{

    //     List<AuditBenchmark> list = getData();
    //     when(auditBenchmarkService.getAllBenchmarks()).thenReturn(list);

    //     mockMvc.perform(get("/benchmark/AuditBenchmark").contentType(MediaType.APPLICATION_JSON))

    //             .andExpect(status().isOk());
    
    // }


    
    
    
    private List<AuditBenchmark> getData(){

        List<AuditBenchmark> list = new ArrayList<>();
        AuditBenchmark auditBenchmark1 = new AuditBenchmark(1,"Internal",3);
        AuditBenchmark auditBenchmark2 = new AuditBenchmark(2,"SOX",1);

        list.add(auditBenchmark1);
        list.add(auditBenchmark2);

        return list;        
    }
}
