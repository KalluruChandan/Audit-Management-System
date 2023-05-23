package com.cognizant.auditbenchmark.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.auditbenchmark.dao.AuditBenchmarkDao;
import com.cognizant.auditbenchmark.model.AuditBenchmark;
// import com.cognizant.auditbenchmark.service.AuditBenchmarkService;
// import com.cognizant.auditbenchmark.service.AuditBenchmarkServiceImpl;
import com.cognizant.auditbenchmark.service.AuditBenchmarkServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
class AuditBenchmarkServiceTest {

    @InjectMocks
    private AuditBenchmarkServiceImpl auditBenchmarkService;

    @Mock
    private AuditBenchmarkDao auditBenchmarkDao;

    @Test
    public void testGetAllBenchmarks() throws Exception{

        List<AuditBenchmark> list = getData();
        when(auditBenchmarkDao.findAll()).thenReturn(list);

        assertEquals(list, auditBenchmarkService.getAllBenchmarks());

    }
    
    private List<AuditBenchmark> getData(){

        List<AuditBenchmark> list = new ArrayList<>();
        AuditBenchmark auditBenchmark1 = new AuditBenchmark(1,"Internal",3);
        AuditBenchmark auditBenchmark2 = new AuditBenchmark(2,"SOX",1);

        list.add(auditBenchmark1);
        list.add(auditBenchmark2);

        return list;        
    }
    
}
