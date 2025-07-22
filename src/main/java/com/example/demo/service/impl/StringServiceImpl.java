package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ApiResponse;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.service.StringService;

@Service
public class StringServiceImpl implements StringService {

	@Override
	public ApiResponse removeFirstAndLast(String input) {
		if (input == null || input.length() < 2) {
			throw new InvalidInputException("Input string must be at least 2 characters long");
		}

		if (input == null || input.length() < 2) {
			throw new InvalidInputException("Input must be at least 2 characters long");
		}

		String result = "";

		if (input.length() > 2) {
			result = input.substring(1, input.length() - 1);
		}

		return new ApiResponse(true, "Success", result);
	}

}
