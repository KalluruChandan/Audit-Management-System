package com.cognizant.auditauthorization.service;

import com.cognizant.auditauthorization.Dao.ProjectManagerDao;
import com.cognizant.auditauthorization.model.ProjectManager;
import com.cognizant.auditauthorization.model.ProjectManagerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectManagerService implements UserDetailsService {

    @Autowired
    private ProjectManagerDao projectManagerDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	//If the username is found return the details else raises an exception

        Optional<ProjectManager> name = projectManagerDao.findByusername(username);
        if(name.isPresent()){
            ProjectManager projectManager=name.get();
            return new ProjectManagerDetails(projectManager);
        }
        else{
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
       }
}
