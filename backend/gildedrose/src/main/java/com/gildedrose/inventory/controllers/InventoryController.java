package com.gildedrose.inventory.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gildedrose.inventory.helpers.ItemUpdater;
import com.gildedrose.inventory.models.Item;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	private List<Item> inventory;

	@PostMapping
	public ResponseEntity<List<Item>> addInventory(@RequestParam("file") MultipartFile multipart){
		
        inventory = new ArrayList<Item>();
		try {
			if (multipart.isEmpty()) {
	            throw new RuntimeException("File is empty");
	        }

			String pathName = System.getProperty("user.dir") + "/" + multipart.getOriginalFilename();
			File uploadedFile = new File(pathName);
			multipart.transferTo(uploadedFile);
	        
	        InputStream is = new FileInputStream(new File(pathName));
	        TypeReference<List<Item>> ref = new TypeReference<List<Item>>(){};
	        ObjectMapper mapper = new ObjectMapper();
	        List<Item> items = mapper.readValue(is, ref);
	        			
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
		} catch (Exception e2) {
			e2.printStackTrace();
			throw new RuntimeException("Something went wrong while reading the file");
		}
		return ResponseEntity.ok(inventory);
	}
	@GetMapping
	public ResponseEntity<List<Item>> findAll(){
		return ResponseEntity.ok(inventory);
	}
}
