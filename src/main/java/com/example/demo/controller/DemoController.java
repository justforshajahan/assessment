package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.service.StringService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DemoController {

	private final StringService stringService;

	@GetMapping("/remove")
	public ResponseEntity<ApiResponse> remove(@RequestParam(name = "input") String input) {
		return ResponseEntity.ok(stringService.removeFirstAndLast(input));
	}
}
