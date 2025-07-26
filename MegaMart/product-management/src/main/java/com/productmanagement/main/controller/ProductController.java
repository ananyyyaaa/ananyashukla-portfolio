package com.productmanagement.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.productmanagement.main.dto.ProductRequest;
import com.productmanagement.main.dto.ProductResponse;
import com.productmanagement.main.model.Product;
import com.productmanagement.main.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		ProductResponse response = mapToResponse(product);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
		ProductResponse response = productService.createProduct(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}

	@GetMapping("/filter/name")
	public List<Product> filterByName(@RequestParam String name) {
		return productService.filterByName(name);
	}

	@GetMapping("/filter/max-price")
	public List<Product> filterByMaxPrice(@RequestParam double price) {
		return productService.filterByMaxPrice(price);
	}

	@GetMapping("/filter/price-range")
	public List<Product> filterByPriceRange(@RequestParam double min, @RequestParam double max) {
		return productService.filterByPriceRange(min, max);
	}

	@GetMapping("/paginated")
	public Page<Product> getPaginatedProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return productService.getProductsPaginated(page, size);
	}

	@GetMapping("/filter/name/paginated")
	public Page<Product> getPaginatedFilteredByName(@RequestParam String name,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
		return productService.filterByNamePaginated(name, page, size);
	}

	private ProductResponse mapToResponse(Product product) {
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
}
