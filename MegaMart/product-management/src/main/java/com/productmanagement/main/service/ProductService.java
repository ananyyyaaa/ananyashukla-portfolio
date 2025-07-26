package com.productmanagement.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.productmanagement.main.dto.ProductRequest;
import com.productmanagement.main.dto.ProductResponse;
import com.productmanagement.main.exception.ProductNotFoundException;
import com.productmanagement.main.model.Product;
import com.productmanagement.main.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(Long id, Product product) {
		if (productRepository.existsById(id)) {
			product.setId(id);
			return productRepository.save(product);
		}
		return null;
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public List<Product> filterByName(String name) {
		return productRepository.findByNameContainingIgnoreCase(name);
	}

	public List<Product> filterByMaxPrice(double price) {
		return productRepository.findByPriceLessThanEqual(price);
	}

	public List<Product> filterByPriceRange(double min, double max) {
		return productRepository.findByPriceBetween(min, max);
	}

	public Page<Product> getProductsPaginated(int page, int size) {
		return productRepository.findAll(PageRequest.of(page, size));
	}

	public Page<Product> filterByNamePaginated(String name, int page, int size) {
		return productRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size));
	}

	public Product getProductById1(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
	}

	public ProductResponse mapToResponse(Product product) {
		ProductResponse response = new ProductResponse();
		response.setId(product.getId());
		response.setName(product.getName());
		response.setDescription(product.getDescription());
		response.setPrice(product.getPrice());
		response.setCategory(product.getCategory());
		response.setStock(product.getStock());
		response.setCreatedBy(product.getCreatedBy());
		response.setUpdatedBy(product.getUpdatedBy());
		return response;
	}

	public Product mapToEntity(ProductRequest request) {
		Product product = new Product();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setCategory(request.getCategory());
		product.setStock(request.getStock());
		product.setCreatedBy(request.getCreatedBy());
		product.setUpdatedBy(request.getUpdatedBy());
		return product;
	}

	public ProductResponse createProduct(ProductRequest request) {
		Product product = mapToEntity(request);
		Product savedProduct = productRepository.save(product);
		return mapToResponse(savedProduct);
	}

}
