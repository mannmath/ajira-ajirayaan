package com.ajira.ajirayaan.rover.service;

import java.util.List;
import java.util.Map;

public interface RoverService {
	public List<Map<String, Object>> addItemToRoverInventoryAndShow(String itemName, int quantity);

	public void useItemFromInventory(String itemName);
}
