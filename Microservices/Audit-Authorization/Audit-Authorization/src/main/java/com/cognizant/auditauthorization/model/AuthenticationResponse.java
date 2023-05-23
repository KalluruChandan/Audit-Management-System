package com.cognizant.auditauthorization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
//authorize the token and generates the response
    private String userName;
    private String projectName;
    private boolean isValid;
}
