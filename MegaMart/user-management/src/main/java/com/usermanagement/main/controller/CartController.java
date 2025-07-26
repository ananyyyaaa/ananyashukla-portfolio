package com.usermanagement.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.main.entity.CartEntity;
import com.usermanagement.main.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/{userId}")
	public List<CartEntity> getCart(@PathVariable Long userId) {
		return cartService.getUserCart(userId);
	}

	@PostMapping("/{userId}")
	public ResponseEntity<CartEntity> addToCart(@PathVariable Long userId, @RequestBody CartEntity cart) {
		CartEntity savedCart = cartService.addToCart(userId, cart);
		return ResponseEntity.ok(savedCart);
	}

	@DeleteMapping("/{cartId}")
	public ResponseEntity<String> removeFromCart(@PathVariable Long cartId) {
		cartService.removeFromCart(cartId);
		return ResponseEntity.ok("Item removed from cart");
	}
}
