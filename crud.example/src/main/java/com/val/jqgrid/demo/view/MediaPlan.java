package com.val.jqgrid.demo.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MediaPlan {
	
	private Integer id;
	private Map<Integer, Item> itemMap;
	
	public MediaPlan(Integer id) {
		this.id = id;
		itemMap = new LinkedHashMap<Integer, Item>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<Integer, Item> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<Integer, Item> itemMap) {
		this.itemMap = itemMap;
	}
	
	public List<Item> getItems() {
		return new ArrayList<Item>(itemMap.values());
	}

}
