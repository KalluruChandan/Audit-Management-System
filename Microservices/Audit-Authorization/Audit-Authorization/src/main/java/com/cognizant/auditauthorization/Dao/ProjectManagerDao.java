package com.cognizant.auditauthorization.Dao;

import com.cognizant.auditauthorization.model.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//interacts with the database
@Repository
public interface ProjectManagerDao extends JpaRepository<ProjectManager,Integer> {
	//Data derived query method-Finds username by string returns null if username is not present

    Optional<ProjectManager> findByusername(String userName);
}
