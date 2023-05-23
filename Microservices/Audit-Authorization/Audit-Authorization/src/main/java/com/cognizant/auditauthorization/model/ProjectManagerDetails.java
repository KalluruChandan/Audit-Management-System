package com.cognizant.auditauthorization.model;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
public class ProjectManagerDetails implements UserDetails {
	//return the details


    private String userName;
    private String password;
    private String projectName;

   public ProjectManagerDetails(ProjectManager projectManager){
          userName=projectManager.getUsername();
          password= projectManager.getPassword();
          projectName=projectManager.getProjectName();
   }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public String getProjectName() {
        return projectName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
