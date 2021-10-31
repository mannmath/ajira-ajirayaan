package com.ajira.ajirayaan.rover.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ajira.ajirayaan.rover.inventory.RoverInventory;

@Service
public class RoverServiceImpl implements RoverService {

	RoverInventory inventory;

	public RoverServiceImpl(RoverInventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public List<Map<String, Object>> addItemToRoverInventoryAndShow(String itemName, int quantity) {
		inventory.addItemToInventory(itemName, quantity);
		List<Map<String, Object>> currentInventory = inventory.showCurrentInventory();
		return currentInventory;
	}

	@Override
	public void useItemFromInventory(String itemName) {
		inventory.useItem(itemName);
	}

}
