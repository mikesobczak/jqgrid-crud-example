package com.val.jqgrid.demo.view;

import java.util.Map;

public class MediaPlan {
	
	private Integer id;
	private Map<Integer, Item> itemList;
	
	public MediaPlan(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<Integer, Item> getItemList() {
		return itemList;
	}

	public void setItemList(Map<Integer, Item> itemList) {
		this.itemList = itemList;
	}

}
