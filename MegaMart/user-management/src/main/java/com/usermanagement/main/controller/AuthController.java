package com.usermanagement.main.controller;

import com.usermanagement.main.dto.AuthRequest;
import com.usermanagement.main.dto.AuthResponse;
import com.usermanagement.main.dto.RegisterRequest;
import com.usermanagement.main.security.JwtUtil;
import com.usermanagement.main.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public AuthResponse login(@RequestBody AuthRequest request) {
		System.out.println("login done");
	
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credentials");
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String token = jwtUtil.generateToken(userDetails.getUsername());
         
		return new AuthResponse(token);
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
		System.out.println("registered api call");
		userService.registerUser(registerRequest);
		return new ResponseEntity<>("YOU ARE REGISTERED.", HttpStatus.CREATED);
	}
}
