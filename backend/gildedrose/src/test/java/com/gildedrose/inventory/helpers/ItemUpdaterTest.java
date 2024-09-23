package com.gildedrose.inventory.helpers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gildedrose.inventory.models.Item;

class ItemUpdaterTest {
	@Test
	void testInvalidItem() {
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			Item actual = new Item("INVALID ITEM", 2, 2);
			ItemUpdater updater = new ItemUpdater(actual);
			updater.updateItem();
		});
        String expected = "NO SUCH ITEM";
        
		assertEquals(expected, exception.getMessage());
	}
	@Test
	void testDecreaseQualityBy2AndDecreaseSellInBy1(){
		Item actual = new Item("Conjured", 4, 6);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Conjured", 3, 4);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
		
		updater.updateItem();
		expected = new Item("Conjured", 2, 2);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}
    
	@Test
	void qualityConjuredMustNotBeNegativeNumber() {
		
		Item actual = new Item("Conjured", 8, 2);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Conjured", 7, 0);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
		
		updater.updateItem();
		expected = new Item("Conjured", 6, 0);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
		
		updater.updateItem();
		expected = new Item("Conjured", 5, 0);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}
	@Test
	void testAgedBrieIncreaseSellInByOneDecreaseQualityByOne() {
		Item actual = new Item("Aged Brie", 1, 1);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Aged Brie", 0, 2);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}
	@Test
	void testAgedBrieIncreaseSellInByOneDecreaseQualityByTwo() {
		Item actual = new Item("Aged Brie", 1, 10);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Aged Brie", 0, 11);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
				
		updater.updateItem();
		expected = new Item("Aged Brie", -1, 13);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}
	@Test
	void testConjuredDecreseQualityBy4AndSellInBy1WhenSellInDayPassed(){
	
		Item actual = new Item("Conjured", 1, 10);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Conjured", 0, 8);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
				
		updater.updateItem();
		expected = new Item("Conjured", -1, 4);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}

	@Test
	void test() {
		
		Item actual = new Item("Backstage passes", 1, 4);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Backstage passes", 0, 7);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
		
		updater.updateItem();
		expected = new Item("Backstage passes", -1, 0);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}
	@Test
	void testQualityNotExceed50(){		
		Item actual = new Item("Aged Brie", 8, 49);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Aged Brie", 7, 50);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
		
		updater.updateItem();
		expected = new Item("Aged Brie", 6, 50);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}
	@Test
	void testSulfurasNotChangeBothSellInAndQuality() {
		
		Item actual = new Item("Sulfuras", 5, 8);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Sulfuras", 5, 8);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
		
		updater.updateItem();
		expected = new Item("Sulfuras", 5, 8);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}
	@Test
	void testBackstagePassesWhenSellInGreaterThan10() {
		
		Item actual = new Item("Backstage passes", 15, 4);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Backstage passes", 14, 5);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
		
		updater.updateItem();
		expected = new Item("Backstage passes", 13, 6);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}
	
	@Test
	void testBackstagePassesWhenSellInGreaterThan10WhenSellInBetween10And6() {
				
		Item actual = new Item("Backstage passes", 11, 4);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Backstage passes", 10, 5);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
		
		updater.updateItem();
		expected = new Item("Backstage passes", 9, 7);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
		
	}
	@Test
	void testBackPassesIncreaseQualityBy3DecreaseSellInBy1WhenSellInBetween5And0() {
		
		Item actual = new Item("Backstage passes", 6, 4);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Backstage passes", 5, 6);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
		
		updater.updateItem();
		expected = new Item("Backstage passes", 4, 9);
		
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}
	
	

	@Test
	void testNormalItem() {
		Item actual = new Item("Normal Item", 2, 2);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Normal Item", 1, 1);
				
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}
	@Test
	void testNormalItemQualityHigherThanMax() {
		Item actual = new Item("Normal Item", -1, 55);
		ItemUpdater updater = new ItemUpdater(actual);
		updater.updateItem();
		Item expected = new Item("Normal Item", -2, 50);
				
		assertThat(actual.getName()).isEqualTo(expected.getName());
		assertThat(actual.getQuality()).isEqualTo(expected.getQuality());
		assertThat(actual.getSellIn()).isEqualTo(expected.getSellIn());
	}

}
