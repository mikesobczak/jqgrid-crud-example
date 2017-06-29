package com.val.jqgrid.demo.test;

import org.junit.Test;

import com.val.jqgrid.demo.view.Item;

public class ItemTest {
	
	public static final String productName = "VDP Basic";

	@Test
	public void testGetProductName() {
		Item item = new Item();
		item.setProductName(productName);
		assert(item.getProductName().equals(productName));
		
	}

	@Test
	public void testSetProductName() {
		Item item = new Item();
		item.setProductName(productName);
		assert(item.getProductName() != null);
	}

}
