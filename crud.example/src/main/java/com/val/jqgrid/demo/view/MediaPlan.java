package com.val.jqgrid.demo.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MediaPlan {
	
	private Integer id;
	private Map<Integer, Item> itemMap;
	private Date ihd;
	
	public MediaPlan(Integer id) {
		this.id = id;
		
		// Default the IHD to one month from today.
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		this.ihd = cal.getTime();
		
		itemMap = new LinkedHashMap<Integer, Item>();
	}
	
	public MediaPlan(Integer id, Date ihd) {
		this.id = id;
		this.ihd = ihd;
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

	public Date getIhd() {
		return ihd;
	}

	public void setIhd(Date ihd) {
		this.ihd = ihd;
	}
	
}
