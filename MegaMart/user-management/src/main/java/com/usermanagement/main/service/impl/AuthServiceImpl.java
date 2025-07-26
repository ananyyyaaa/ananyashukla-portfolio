package com.usermanagement.main.service.impl;

import com.usermanagement.main.dto.AuthRequest;
import com.usermanagement.main.dto.AuthResponse;
import com.usermanagement.main.entity.UserEntity;
import com.usermanagement.main.repository.UserRepo;
import com.usermanagement.main.security.JwtUtil;
import com.usermanagement.main.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public AuthResponse login(AuthRequest request) {
		UserEntity user = userRepo.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("Invalid username or password"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		}

		String token = jwtUtil.generateToken(user.getUsername());

		return new AuthResponse(token);
	}
}
