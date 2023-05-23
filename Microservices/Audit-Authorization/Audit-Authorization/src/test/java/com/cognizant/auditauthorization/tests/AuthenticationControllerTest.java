package com.cognizant.auditauthorization.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.cognizant.auditauthorization.controller.AuthenticationController;
import com.cognizant.auditauthorization.model.AuthenticationRequest;
import com.cognizant.auditauthorization.model.AuthenticationResponse;
import com.cognizant.auditauthorization.model.ProjectManager;
import com.cognizant.auditauthorization.model.ProjectManagerDetails;
import com.cognizant.auditauthorization.service.JwtTokenService;
import com.cognizant.auditauthorization.service.ProjectManagerService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@SpringBootTest
public class AuthenticationControllerTest {

    @Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private ProjectManagerService projectManagerService;

	@Mock
	private JwtTokenService jwtTokenService;

	@InjectMocks
	private AuthenticationController authController; // from where we will assert

	@Test // for '/authenticate'
	public void generateJwtTest() {
		// instances req.
		ResponseEntity<String> response = null;
		AuthenticationRequest request = null;
        ProjectManager projectManager = null;
		ProjectManagerDetails projectManagerDetails = null;
		// authenticating the User-Credentials - Correct
		request = new AuthenticationRequest();
		request.setUserName("user1");
		request.setPassword("abcd1234");
		// making projectManager
		projectManager = new ProjectManager(1,"user1", "abcd1234", "Project1");
		// making ProjectManagerDetails
		projectManagerDetails = new ProjectManagerDetails(projectManager);
		// making fake token
		final String jwtToken = "jj.ww.tt";
		// making response
		response = new ResponseEntity<String>(jwtToken, HttpStatus.OK);

		// the correct flow
		when(authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())))
						.thenReturn(null);
		when(projectManagerService.loadUserByUsername(request.getUserName())).thenReturn(projectManagerDetails);
		when(jwtTokenService.generateToken(projectManagerDetails)).thenReturn(jwtToken);
		assertEquals(response, authController.generateJwt(request));

		// authenticating the User-Credentials - Wrong
		request = new AuthenticationRequest();
		request.setUserName("invalidUser1");
		request.setPassword("abcd1234");
		// making ProjectManagerDetails
		projectManagerDetails = null;
		// no token generated
		// making response
		response = new ResponseEntity<String>("Not Authorized", HttpStatus.FORBIDDEN);

		// the wrong flow
		when(authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())))
						.thenReturn(null);
		when(projectManagerService.loadUserByUsername(request.getUserName())).thenThrow(RuntimeException.class);
		assertEquals(response, authController.generateJwt(request));
	}

	@Test // for '/validate'
	public void validateJwt() {
		// instances req.
		String jwtTokenHeader = "Bearer jj.ww.tt";
		AuthenticationResponse authenticationResponse = null;
		ResponseEntity<AuthenticationResponse> response = null;
		ProjectManagerDetails projectManagerDetails = null;
        ProjectManager projectManager = null;
		// making projectManager
		projectManager = new ProjectManager(1, "user1", "abcd1234", "Project1");
		// making ProjectManagerDetails
		projectManagerDetails = new ProjectManagerDetails(projectManager);
		// making authentication-response
		authenticationResponse = new AuthenticationResponse("user1", "Project1", true);
		// first remove Bearer from Header
		String jwtToken = jwtTokenHeader.substring(7);
		String username = "user1";
		response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);

		// check the jwt is proper or not - success
		when(jwtTokenService.getUsernameFromToken(jwtToken)).thenReturn(username);
		when(projectManagerService.loadUserByUsername(username)).thenReturn(projectManagerDetails);
		when(jwtTokenService.validateToken(jwtToken, projectManagerDetails)).thenReturn(true); // correct
		assertEquals(response, authController.validateJwt(jwtTokenHeader));

		// now for wrong
		jwtTokenHeader = "Bearer jj.wrong.tt";
		// making ProjectManagerDetails
		projectManagerDetails = null;
		// making authentication-response
		authenticationResponse = new AuthenticationResponse("Invalid", "Invalid", false);
		username = "";
		// first remove Bearer from Header
		jwtToken = jwtTokenHeader.substring(7);
		response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);

		// check the jwt is proper or not - success
		when(jwtTokenService.getUsernameFromToken(jwtToken)).thenReturn(username);
		when(projectManagerService.loadUserByUsername(username)).thenReturn(projectManagerDetails);
		when(jwtTokenService.validateToken(jwtToken, projectManagerDetails)).thenReturn(false); // wrong
		assertEquals(response, authController.validateJwt(jwtTokenHeader));
	}
    
}
