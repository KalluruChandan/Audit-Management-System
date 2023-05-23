package com.cognizant.auditauthorization.controller;

import com.cognizant.auditauthorization.model.AuthenticationRequest;
import com.cognizant.auditauthorization.model.AuthenticationResponse;
import com.cognizant.auditauthorization.model.ProjectManagerDetails;
import com.cognizant.auditauthorization.service.JwtTokenService;
import com.cognizant.auditauthorization.service.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private ProjectManagerService projectManagerService;

    @GetMapping("/health")
    public String isActive(){
        return "Audit-Authorization is active";
    }

    @PostMapping("/authenticate")
    @ApiOperation(value = "returns a JWT Token",
    notes="This request should return a new JWT token" )
    public ResponseEntity<String> generateJwt(@RequestBody AuthenticationRequest request) {
        ResponseEntity<String> response = null;

        // authenticating the User-Credentials
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
            // else when it authenticates successfully
            final UserDetails userDetails
                    = projectManagerService.loadUserByUsername(request.getUserName());

            final String jwt = jwtTokenService.generateToken(userDetails); // returning the token as response


            response = new ResponseEntity<String>(jwt, HttpStatus.OK);

        } catch (Exception e) {
            response = new ResponseEntity<String>("Not Authorized", HttpStatus.FORBIDDEN);
        }

        return response;
    }


    @GetMapping("/validate")
    @ApiOperation(value = "validates a JWT token",
    notes="This request should provide validation for a JWT token")
    public ResponseEntity<AuthenticationResponse> validateJwt(@RequestHeader("Authorization") String jwt) {

        AuthenticationResponse authenticationResponse = new AuthenticationResponse("Invalid", "Invalid", false);
        ResponseEntity<AuthenticationResponse> response = null;

        // first remove Bearer from Header
        jwt = jwt.substring(7);


        // check the jwt is proper or not
        final ProjectManagerDetails userDetails = (ProjectManagerDetails) projectManagerService
                .loadUserByUsername(jwtTokenService.getUsernameFromToken(jwt));

        // now validating the jwt
        try {
            if (jwtTokenService.validateToken(jwt, userDetails)) {
                authenticationResponse.setUserName(userDetails.getUsername());
                authenticationResponse.setProjectName(userDetails.getProjectName());
                authenticationResponse.setValid(true);
                response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
            } else {
                response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
            }
        } catch (Exception e) {
            response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
        }
        return response;
    }
}
