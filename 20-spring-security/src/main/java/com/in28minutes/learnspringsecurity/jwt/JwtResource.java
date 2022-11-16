package com.in28minutes.learnspringsecurity.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtResource {
	//
	
	private AuthenticationManager manager;
	
	private JwtEncoder jwtEncoder;
	
	public JwtResource(AuthenticationManager manager, JwtEncoder jwtEncoder) {
		this.manager = manager;
		this.jwtEncoder = jwtEncoder;
	}
	
	@PostMapping("/authenticate")
	public Authentication authenticateAndCreateJwtToken(
						@RequestBody JwtRequest request) {
		var authenticationToken = new UsernamePasswordAuthenticationToken
		(request.username(),request.password());
		
		var authentication = manager.authenticate(authenticationToken);
		
		return authentication;
		//return new JwtResponse("JWT Token");
	}

}

record JwtRequest(String username, String password) {}
record JwtResponse(String token) {}