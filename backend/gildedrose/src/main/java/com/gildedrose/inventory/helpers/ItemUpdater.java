package com.gildedrose.inventory.helpers;

import com.gildedrose.inventory.models.Item;

public class ItemUpdater {
	private Item item;
	
	public ItemUpdater(Item item) {
		this.item = item;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void updateItem() {
		switch (item.getName()) {
		case "Aged Brie": {
			updateAgedBrie(item);
			break;
		}
		case "Backstage passes": {	
			updateBackstagePasses(item);
			break;
		}
		case "Sulfuras": {	
			updateSulfuras(item);
			break;
		}
		case "Normal Item": {
			updateNormalItem(item);
			break;
		}
		case "Conjured": {	
			updateConjured(item);
			break;
		}
		default:
			throw new RuntimeException("NO SUCH ITEM");
		}
	}
	private void updateConjured(Item item) {
		item.setQuality(item.getQuality() - 2);

		if (item.getSellIn() <= 0) {
			item.setQuality(item.getQuality() - 2);
		}

		item.setQuality(Math.max(item.getQuality(), 0));
		decreaseSellIn(item);
		
	}
	private void updateNormalItem(Item item) {
		//“Normal Item” decreases in Quality by 1
		item.setQuality(item.getQuality() - 1);

	    if (item.getSellIn() <= 0) {
	      item.setQuality(item.getQuality() - 1);
	    }
	    item.setQuality(Math.min(item.getQuality(), 50));
	    decreaseSellIn(item);		
	}

	//Decrease item quality by 1
	private void decreaseSellIn(Item item) {
		item.setSellIn(item.getSellIn() - 1);
	}
	private void updateSulfuras(Item item) {
		//Legendary. Need no update
		
	}
	private void updateBackstagePasses(Item item) {
		if (item.getSellIn() > 10) {
			item.setQuality(item.getQuality() + 1);
		} else if (item.getSellIn() <= 10 && item.getSellIn() > 5) {
			item.setQuality(item.getQuality() + 2);
		} else if (item.getSellIn() <= 5 && item.getSellIn() > 0) {
			item.setQuality(item.getQuality() + 3);
		} else {
			item.setQuality(0);
		}
		decreaseSellIn(item);
		
	}
	private void updateAgedBrie(Item item){
		item.setQuality(item.getQuality() + 1);

	    if (item.getSellIn() <= 0) {
	      item.setQuality(item.getQuality() + 1);
	    }

	    item.setQuality(Math.min(item.getQuality(), 50));
	    decreaseSellIn(item); 
	    
	}	
}
