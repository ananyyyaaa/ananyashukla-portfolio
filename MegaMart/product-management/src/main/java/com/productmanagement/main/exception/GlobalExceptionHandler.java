package com.productmanagement.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {
		return buildErrorResponse(HttpStatus.NOT_FOUND, "Product Not Found", ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {
		return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error", ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Input Type", ex.getMessage());
	}

	private ResponseEntity<?> buildErrorResponse(HttpStatus status, String error, String message) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("status", status.value());
		errorDetails.put("error", error);
		errorDetails.put("message", message);
		return new ResponseEntity<>(errorDetails, status);
	}
}
