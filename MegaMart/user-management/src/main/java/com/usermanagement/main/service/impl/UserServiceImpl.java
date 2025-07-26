package com.usermanagement.main.service.impl;

import com.usermanagement.main.dto.ProductDto;
import com.usermanagement.main.dto.RegisterRequest;
import com.usermanagement.main.entity.UserEntity;
import com.usermanagement.main.feign.ProductFeign;
import com.usermanagement.main.repository.UserRepo;
import com.usermanagement.main.security.JwtUtil;
import com.usermanagement.main.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ProductFeign productFeign;

	public ProductDto fetchProduct(Long id) {
	    return productFeign.getProductById(id);
	}

	@Override
	public void registerUser(RegisterRequest request) {
		if (userRepo.findByUsername(request.getUsername()).isPresent()) { 
			throw new RuntimeException("Username already taken.");
		}
        System.out.println("going to register user");
		UserEntity user = new UserEntity();
		user.setUsername(request.getUsername());
		user.setFullName(request.getFullName()); 
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEmail(request.getEmail());
		user.setPhone(request.getPhone());
		user.setAddress(request.getAddress());
		user.setGender(request.getGender());
		user.setDateOfBirth(request.getDateOfBirth());

		userRepo.save(user);
	}
	@Override
	public UserEntity getUserById(Long id) {
	    return userRepo.findById(id)
	        .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
	}

	@Override
	public UserEntity getUserById(Long id , String token , String username) {
		JwtUtil utility = new JwtUtil();
		utility.validateToken(token , username);
;		return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
	}

	@Override
	public UserEntity updateUser(Long id, UserEntity updatedUser) {
		UserEntity user = getUserById(id);
		user.setEmail(updatedUser.getEmail());
		user.setPhone(updatedUser.getPhone());
		user.setAddress(updatedUser.getAddress());
		user.setGender(updatedUser.getGender());
		user.setDateOfBirth(updatedUser.getDateOfBirth());
		userRepo.save(user);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		if (!userRepo.existsById(id)) {
			throw new RuntimeException("User not found.");
		}
		userRepo.deleteById(id);
	}

	@Override
	public List<String> getUserCartItems(Long id) {
		return Arrays.asList("iPhone 15", "MacBook Pro", "AirPods Pro");
	}

	@Override
	public List<String> getUserOrderHistory(Long id) {
		return Arrays.asList("Order #101 - Delivered", "Order #102 - Shipped", "Order #103 - Cancelled");
	}

	@Override
	public ProductDto getProductDetails(Long productId) {
		return null;
	}
}
