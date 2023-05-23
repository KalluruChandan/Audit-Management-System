package com.cognizant.auditauthorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="manager_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectManager {
	//Creating a table and passing values to id,username,password and projectName

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private String projectName;
}
