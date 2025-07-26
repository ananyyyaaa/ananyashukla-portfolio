package com.usermanagement.main.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.usermanagement.main.dto.ProductDto;

@FeignClient(name = "product-service")
public interface ProductFeign {

	public static final String PRODUCT_ID = "productId";

	@GetMapping("/api/products/{productId}")
	ProductDto getProductById(@PathVariable(PRODUCT_ID) Long productId);
}
