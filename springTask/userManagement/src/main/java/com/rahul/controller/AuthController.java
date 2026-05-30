package com.rahul.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.dto.AuthResponse;
import com.rahul.dto.LoginRequest;
import com.rahul.security.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
	
	 private final AuthenticationManager authenticationManager;
	    private final JwtService jwtService;

	    @PostMapping("/login")
	    public AuthResponse login(@RequestBody LoginRequest request) {

	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        request.getEmail(),
	                        request.getPassword()
	                )
	        );

	        String token = jwtService.generateToken(request.getEmail());

	        return new AuthResponse(token);
	    }

}
