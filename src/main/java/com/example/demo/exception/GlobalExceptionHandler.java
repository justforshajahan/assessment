package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ApiResponse> handleInvalidInput(InvalidInputException ex) {
		ApiResponse errorOutput = new ApiResponse(false, ex.getMessage(), null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorOutput);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handleGeneralException(Exception ex) {
		ApiResponse errorOutput = new ApiResponse(false, "Internal Server Error", null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorOutput);
	}
}
