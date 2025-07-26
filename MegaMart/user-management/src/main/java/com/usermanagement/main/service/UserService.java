package com.usermanagement.main.service;

import com.usermanagement.main.dto.ProductDto;
import com.usermanagement.main.dto.RegisterRequest;
import com.usermanagement.main.entity.UserEntity;

import java.util.List;

public interface UserService {

	void registerUser(RegisterRequest request);

	UserEntity getUserById(Long id);

	UserEntity updateUser(Long id, UserEntity updatedUser);

	void deleteUser(Long id);

	List<String> getUserCartItems(Long id);

	List<String> getUserOrderHistory(Long id);

	UserEntity getUserById(Long id, String token, String username);

	ProductDto getProductDetails(Long productId);
}
