package com.ajira.ajirayaan.environment.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajira.ajirayaan.environment.dao.EnvironmentDAO;
import com.ajira.ajirayaan.environment.service.EnvironmentService;

@RestController
@CrossOrigin("*")
public class EnvironmentController {

	private EnvironmentService environment;

	public EnvironmentController(EnvironmentService environment) {
		this.environment = environment;
	}

	@PostMapping("/api/environment/configure")
	public ResponseEntity<?> configureEnvironment(@RequestBody @Valid EnvironmentDAO environment) {
		this.environment.configureEnvironment(environment);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping("/api/environment")
	public ResponseEntity<?> modifyEnvironment(@RequestBody Map<String, Object> modifier) {
		Map<String, Object> response = this.environment.modifyEnvironment(modifier);
		HttpStatus statusCode = (HttpStatus) response.getOrDefault("statusCode", HttpStatus.OK);
		String errorMsg = response.get("errMsg") == null ? null : response.get("errMsg").toString();
		if (errorMsg != null) {
			Map<String, String> responseMsg = new HashMap<String, String>();
			responseMsg.put("message", errorMsg);
			return new ResponseEntity<>(responseMsg, statusCode);
		}
		return new ResponseEntity<>(statusCode);
	}
}
