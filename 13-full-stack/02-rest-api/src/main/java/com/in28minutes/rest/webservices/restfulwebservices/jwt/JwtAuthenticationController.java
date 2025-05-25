package com.in28minutes.rest.webservices.restfulwebservices.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class JwtAuthenticationController {
    
    private final JwtTokenService tokenService;

    public JwtAuthenticationController(JwtTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/authenticate")
    public String generateToken(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

    @GetMapping("/")
    public String getHome(Principal principal) {
        return "Hello, " + principal.getName();
    }

    // private final AuthenticationManager authenticationManager;

//    public JwtAuthenticationController(JwtTokenService tokenService,
//            AuthenticationManager authenticationManager) {
//        this.tokenService = tokenService;
//        this.authenticationManager = authenticationManager;
//    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<JwtTokenResponse> generateToken(
//            @RequestBody JwtTokenRequest jwtTokenRequest) {
//
//        var authenticationToken =
//                new UsernamePasswordAuthenticationToken(
//                        jwtTokenRequest.username(),
//                        jwtTokenRequest.password());
//
//        var authentication =
//                authenticationManager.authenticate(authenticationToken);
//
//        var token = tokenService.generateToken(authentication);
//
//        return ResponseEntity.ok(new JwtTokenResponse(token));
//    }
}


