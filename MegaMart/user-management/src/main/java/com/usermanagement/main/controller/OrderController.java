package com.usermanagement.main.controller;

import com.usermanagement.main.entity.OrderEntity;
import com.usermanagement.main.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/products")
	public List<OrderEntity> getAllProducts(@PathVariable Long userId) {
		return orderService.getAllProducts(userId);
	}

	@GetMapping("/{userId}")
	public List<OrderEntity> getOrderHistory(@PathVariable Long userId) {
		return orderService.getOrderHistory(userId);
	}

	@PostMapping("/{userId}")
	public ResponseEntity<OrderEntity> placeOrder(@PathVariable Long userId, @RequestBody OrderEntity order) {
		return ResponseEntity.ok(orderService.addOrder(userId, order));
	}
}
