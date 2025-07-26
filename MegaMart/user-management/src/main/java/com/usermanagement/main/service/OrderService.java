package com.usermanagement.main.service;

import com.usermanagement.main.entity.OrderEntity;
import com.usermanagement.main.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
	@Autowired
	private OrderRepo orderRepository;

	public List<OrderEntity> getOrderHistory(Long userId) {
		return orderRepository.findByUserId(userId);
	}

	public OrderEntity addOrder(Long userId, OrderEntity order) {
		order.setUserId(userId);
		order.setOrderDate(new Date());
		return orderRepository.save(order);
	}

	public List<OrderEntity> getAllProducts(Long userId) {
		return orderRepository.findByUserId(userId);
	}
}
