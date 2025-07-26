package com.usermanagement.main.controller;

import com.usermanagement.main.dto.ProductDto;
import com.usermanagement.main.entity.UserEntity;
import com.usermanagement.main.security.JwtUtil;
import com.usermanagement.main.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Long id, @RequestHeader("Authorization") String token,
			@RequestParam String username) {
		jwtUtil.validateToken(token, username);
		UserEntity user = userService.getUserById(id, token, username);
		return ResponseEntity.ok(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity updatedUser,
			@RequestHeader("Authorization") String token, @RequestParam String username) {
		jwtUtil.validateToken(token, username);
		UserEntity user = userService.updateUser(id, updatedUser);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id, @RequestHeader("Authorization") String token,
			@RequestParam String username) {
		jwtUtil.validateToken(token, username);
		userService.deleteUser(id);
		return ResponseEntity.ok("User deleted successfully.");
	}

	@GetMapping("/{id}/cart")
	public ResponseEntity<List<String>> getUserCartItems(@PathVariable Long id,
			@RequestHeader("Authorization") String token, @RequestParam String username) {
		jwtUtil.validateToken(token, username);
		List<String> cartItems = userService.getUserCartItems(id);
		return ResponseEntity.ok(cartItems);
	}

	@GetMapping("/{id}/orders")
	public ResponseEntity<List<String>> getUserOrderHistory(@PathVariable Long id,
			@RequestHeader("Authorization") String token, @RequestParam String username) {
		jwtUtil.validateToken(token, username);
		List<String> orderHistory = userService.getUserOrderHistory(id);
		return ResponseEntity.ok(orderHistory);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDto> getProductDetails(@PathVariable Long productId,
			@RequestHeader("Authorization") String token, @RequestParam String username) {
		jwtUtil.validateToken(token, username);
		ProductDto product = userService.getProductDetails(productId);
		return ResponseEntity.ok(product);
	}
}
