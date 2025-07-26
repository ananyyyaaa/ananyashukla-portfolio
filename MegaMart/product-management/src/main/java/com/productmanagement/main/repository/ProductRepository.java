package com.productmanagement.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.productmanagement.main.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByNameContainingIgnoreCase(String name);

	List<Product> findByPriceLessThanEqual(double price);

	List<Product> findByPriceBetween(double minPrice, double maxPrice);

	Page<Product> findAll(Pageable pageable);

	Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
