package com.gildedrose.inventory.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gildedrose.inventory.helpers.ItemUpdater;
import com.gildedrose.inventory.models.Item;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	private List<Item> inventory = new ArrayList<Item>();

	@PostMapping
	public ResponseEntity<List<Item>> addInventory(@RequestBody List<Item> items){
		
		items.stream().forEach(item -> {
			ItemUpdater itemUpdater = new ItemUpdater(item);
			try {
				itemUpdater.updateItem();
				inventory.add(item);
				System.out.println(item);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
		
		return ResponseEntity.ok(inventory);
	}
}
