package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.ApiResponse;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.service.StringService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {

	@Autowired
	private StringService stringService;

	// ✅ 1. Basic functionality with common words of varying lengths
	@Test
	void removeFirstAndLast_basicWord() {
		ApiResponse response = stringService.removeFirstAndLast("country");
		assertEquals("ountr", response.getData());
		assertTrue(response.isSuccess());
		assertEquals("Success", response.getMessage());
	}

	@Test
	void removeFirstAndLast_shortWord() {
		ApiResponse response = stringService.removeFirstAndLast("cat");
		assertEquals("a", response.getData());
	}

	@Test
	void removeFirstAndLast_mediumWord() {
		ApiResponse response = stringService.removeFirstAndLast("hello");
		assertEquals("ell", response.getData());
	}

	// ✅ 2. Edge cases: Strings with 0 to 3 characters
	@Test
	void removeFirstAndLast_zeroLength_shouldThrow() {
		InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
			stringService.removeFirstAndLast("");
		});
		assertEquals("Input string must be at least 2 characters long", exception.getMessage());
	}

	@Test
	void removeFirstAndLast_oneChar_shouldThrow() {
		InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
			stringService.removeFirstAndLast("a");
		});
		assertEquals("Input string must be at least 2 characters long", exception.getMessage());
	}

	@Test
	void removeFirstAndLast_twoChars_shouldReturnEmpty() {
		ApiResponse response = stringService.removeFirstAndLast("ab");
		assertEquals("", response.getData());
		assertTrue(response.isSuccess());
	}

	@Test
	void removeFirstAndLast_threeChars() {
		ApiResponse response = stringService.removeFirstAndLast("abc");
		assertEquals("b", response.getData());
	}

	// ✅ 3. Strings with numbers and special characters
	@Test
	void removeFirstAndLast_alphanumeric() {
		ApiResponse response = stringService.removeFirstAndLast("9hello9");
		assertEquals("hello", response.getData());
	}

	@Test
	void removeFirstAndLast_withSymbols() {
		ApiResponse response = stringService.removeFirstAndLast("#java$");
		assertEquals("java", response.getData());
	}

	@Test
	void removeFirstAndLast_withPercentChar() {
		ApiResponse response = stringService.removeFirstAndLast("%abc%");
		assertEquals("abc", response.getData());
	}

	@Test
	void removeFirstAndLast_specialOnly() {
		ApiResponse response = stringService.removeFirstAndLast("@$&*#");
		assertEquals("$&*", response.getData());
	}

	@Test
	void removeFirstAndLast_numbersOnly() {
		ApiResponse response = stringService.removeFirstAndLast("12345");
		assertEquals("234", response.getData());
	}
}
