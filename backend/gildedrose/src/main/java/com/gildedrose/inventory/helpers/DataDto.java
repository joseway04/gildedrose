package com.gildedrose.inventory.helpers;

import java.util.ArrayList;
import java.util.List;

import com.gildedrose.inventory.models.Item;

public class DataDto {

	private List<Item> items = new ArrayList<Item>();

	public DataDto(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
