package com.usermanagement.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermanagement.main.entity.CartEntity;
import com.usermanagement.main.repository.CartRepo;
import com.usermanagement.main.repository.UserRepo;

@Service
public class CartService {

	@Autowired
	private CartRepo cartRepository;

	@Autowired
	private UserRepo userRepository;

	public List<CartEntity> getUserCart(Long userId) {
		return cartRepository.findByUserId(userId);
	}

	public CartEntity addToCart(Long userId, CartEntity cart) {
	
		if (!userRepository.existsById(userId)) {
			throw new RuntimeException("User not found");
		}
		cart.setUserId(userId); 
		return cartRepository.save(cart);
	}

	public void removeFromCart(Long cartId) {
		cartRepository.deleteById(cartId);
	}
}
