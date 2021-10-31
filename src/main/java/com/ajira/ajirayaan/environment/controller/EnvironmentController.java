package com.ajira.ajirayaan.environment.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajira.ajirayaan.environment.dao.EnvironmentDAO;

@RestController
@CrossOrigin("*")
public class EnvironmentController {

	@PostMapping("/api/environment/configure")
	public ResponseEntity<?> configureEnvironment(@RequestBody @Valid EnvironmentDAO environment) {
		System.out.println(environment);
		return null;
	}

	@PatchMapping("/api/environment")
	public ResponseEntity<?> modifyEnvironment() {
		return null;
	}
}
