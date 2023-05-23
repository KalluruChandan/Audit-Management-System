package com.cognizant.auditauthorization.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.cognizant.auditauthorization.Dao.ProjectManagerDao;
import com.cognizant.auditauthorization.model.ProjectManager;
import com.cognizant.auditauthorization.model.ProjectManagerDetails;
import com.cognizant.auditauthorization.service.ProjectManagerService;

// import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class ProjectManagerServiceTest {

    @Mock
    private ProjectManagerDao projectManagerDao;

    @InjectMocks
    private ProjectManagerService projectManagerService;

    @Test
    public void testLoadUserByUsername() throws Exception{

        ProjectManager projectManager = new ProjectManager(1,"som","som123","project-1");
        Optional<ProjectManager> optional = Optional.of(projectManager);
        when(projectManagerDao.findByusername("som")).thenReturn(optional);
        
        UserDetails userDetailsExpected= new ProjectManagerDetails(projectManager);
        UserDetails actualUserDetails = projectManagerService.loadUserByUsername("som");

        // assertEquals(expected, actual);

        assertEquals(userDetailsExpected.equals(actualUserDetails),actualUserDetails.equals(userDetailsExpected));

    }

}
