package com.ajira.ajirayaan.rover.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

/**
 * useItem(name) checkAvailability() remove 1 qty
 * 
 * showCurrentInventory()
 **/
@Service
public class RoverInventory {
	// store item, its priority and units occupied
	// {item_name:[priority, unitsOccupied]}
	private final Map<String, List<Integer>> itemPriorityAndSize = new HashMap<String, List<Integer>>();
	// capacity in units(not quantity)
	private final int inventoryCapacity = 17;
	// current size in units(not quantity)
	private int currentInventorySize = 0;
	// lowest priority out of all items.
	// the item with this priority will be removed first.
	private final int lowestPriority = 3;
	// actual inventory. based on priorities.
	// {item_priority:{itemName: name, itemQuantity: qty}}
	private Map<Integer, Map<String, String>> inventory = new HashMap<>();

	@PostConstruct
	void onInit() {
		this.itemPriorityAndSize.put("storm-shield", Arrays.asList(1, 2));
		this.itemPriorityAndSize.put("water-sample", Arrays.asList(2, 2));
		this.itemPriorityAndSize.put("rock-sample", Arrays.asList(3, 3));
	}

	public void addItemToInventory(String item, int quantity) {
		if (quantity <= 0) {
			return;
		}
		int unitsRequiredToHoldItem = this.itemPriorityAndSize.get(item).get(1) * quantity;
		int priorityOfCurrentItem = this.itemPriorityAndSize.get(item).get(0);
		Map<Integer, Integer> itemsAndUnitsToBeRemoved = new HashMap<>();
		if (unitsRequiredToHoldItem > (this.inventoryCapacity - this.currentInventorySize)) {
			int freeUnitsRequired = unitsRequiredToHoldItem - (this.inventoryCapacity - this.currentInventorySize);
			boolean canBeStored = this.isSpaceAvailable(priorityOfCurrentItem, itemsAndUnitsToBeRemoved,
					freeUnitsRequired);
			if (!canBeStored) {
				return;
			}
		}

		this.freeInventory(itemsAndUnitsToBeRemoved);

		if (this.currentInventorySize == this.inventoryCapacity) {
			return;
		}
		this.addTargetItemToInventory(item, quantity, priorityOfCurrentItem);
		this.currentInventorySize += unitsRequiredToHoldItem;
	}

	private void addTargetItemToInventory(String item, int quantity, int priorityOfCurrentItem) {
		if (this.inventory.keySet().contains(priorityOfCurrentItem)) {
			int currentQtyOfItem = Integer.parseInt(this.inventory.get(priorityOfCurrentItem).get("itemQuantity"));
			this.inventory.get(priorityOfCurrentItem).put("itemQuantity",
					Integer.valueOf(currentQtyOfItem + quantity).toString());
		} else {
			Map<String, String> itemDetails = new HashMap<String, String>();
			itemDetails.put("itemName", item);
			itemDetails.put("itemQuantity", Integer.valueOf(quantity).toString());
			this.inventory.put(priorityOfCurrentItem, itemDetails);
		}
	}

	private void freeInventory(Map<Integer, Integer> itemsAndUnitsToBeRemoved) {
		for (Integer prioritiyOfItemToBeDropped : itemsAndUnitsToBeRemoved.keySet()) {
			int currentQtyOfItem = Integer.parseInt(this.inventory.get(prioritiyOfItemToBeDropped).get("itemQuantity"));
			String nameOfItem = this.inventory.get(prioritiyOfItemToBeDropped).get("itemName");
			if (itemsAndUnitsToBeRemoved.get(prioritiyOfItemToBeDropped) == currentQtyOfItem) {
				this.inventory.remove(prioritiyOfItemToBeDropped);
				this.currentInventorySize -= currentQtyOfItem * this.itemPriorityAndSize.get(nameOfItem).get(1);
			} else {
				int finalQtyOfItem = currentQtyOfItem - itemsAndUnitsToBeRemoved.get(prioritiyOfItemToBeDropped);
				this.inventory.get(prioritiyOfItemToBeDropped).put("itemQuantity",
						Integer.valueOf(finalQtyOfItem).toString());
				this.currentInventorySize -= itemsAndUnitsToBeRemoved.get(prioritiyOfItemToBeDropped)
						* this.itemPriorityAndSize.get(nameOfItem).get(1);
			}
		}
	}

	private boolean isSpaceAvailable(int priorityOfCurrentItem, Map<Integer, Integer> itemsAndUnitsToBeRemoved,
			int freeUnitsRequired) {
		boolean canBeStored = false;
		int currentPriority = this.lowestPriority;
		while (currentPriority > 0 && freeUnitsRequired > 0) {
			Map<String, String> inventoryItem = this.inventory.get(currentPriority);
			if (inventoryItem != null && priorityOfCurrentItem < currentPriority) {
				int inventoryItemSize = this.itemPriorityAndSize.get(inventoryItem.get("itemName")).get(1);
				int inventoryItemQty = Integer.parseInt(inventoryItem.get("itemQuantity"));
				int unitsHeldByItem = inventoryItemSize * inventoryItemQty;
				if (unitsHeldByItem > freeUnitsRequired) {
					canBeStored = true;
					int qtyOfInventoryItemToBeRemoved = (int) Math.ceil(Integer.valueOf(freeUnitsRequired).doubleValue()
							/ Integer.valueOf(inventoryItemSize).doubleValue());
					itemsAndUnitsToBeRemoved.put(currentPriority, qtyOfInventoryItemToBeRemoved);
				} else {
					freeUnitsRequired -= unitsHeldByItem;
					itemsAndUnitsToBeRemoved.put(currentPriority, inventoryItemQty);
				}
				if (canBeStored || freeUnitsRequired == 0) {
					return true;
				}
			}
			if (itemsAndUnitsToBeRemoved.size() == 0) {
				break;
			}
			currentPriority--;
		}
		return canBeStored;
	}

	public void useItem(String itemName) {
		int priorityOfCurrentItem = this.itemPriorityAndSize.get(itemName).get(0);
		if (this.inventory.keySet().contains(priorityOfCurrentItem)) {
			int currentQtyOfItem = Integer.parseInt(this.inventory.get(priorityOfCurrentItem).get("itemQuantity"));
			if (currentQtyOfItem - 1 == 0) {
				this.inventory.remove(priorityOfCurrentItem);
			} else {
				this.inventory.get(priorityOfCurrentItem).put("itemQuantity",
						Integer.valueOf(currentQtyOfItem - 1).toString());
			}
			this.currentInventorySize -= this.itemPriorityAndSize.get(itemName).get(1);
		}
	}

	public List<Map<String, Object>> showCurrentInventory() {
		List<Map<String, Object>> currentInventory = new ArrayList<Map<String, Object>>();
		for (Integer priority : this.inventory.keySet()) {
			Map<String, Object> itemDetails = new HashMap<String, Object>();
			itemDetails.put("type", this.inventory.get(priority).get("itemName"));
			itemDetails.put("quantity", Integer.parseInt(this.inventory.get(priority).get("itemQuantity")));
			itemDetails.put("priority", priority);
			currentInventory.add(itemDetails);
		}
		return currentInventory;
	}
}
