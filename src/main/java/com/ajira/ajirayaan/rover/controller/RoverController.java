package com.ajira.ajirayaan.rover.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajira.ajirayaan.rover.service.RoverService;

@RestController
@CrossOrigin("*")
public class RoverController {

	RoverService rover;

	public RoverController(RoverService rover) {
		this.rover = rover;
	}

	@GetMapping("/v1/inventory")
	public ResponseEntity<List<Map<String, Object>>> putItemAndShow(@RequestParam String item,
			@RequestParam Integer quantity) {
		List<Map<String, Object>> currentInventory = rover.addItemToRoverInventoryAndShow(item, quantity);
		return new ResponseEntity<>(currentInventory, HttpStatus.OK);
	}

	@GetMapping("/v1/inventory/use")
	public ResponseEntity<?> useItem(@RequestParam String item) {
		rover.useItemFromInventory(item);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
