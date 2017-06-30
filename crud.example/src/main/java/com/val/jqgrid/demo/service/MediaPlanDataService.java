package com.val.jqgrid.demo.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.val.jqgrid.demo.view.Item;
import com.val.jqgrid.demo.view.MediaPlan;

public class MediaPlanDataService {
	
	private static Logger LOG = Logger.getLogger(MediaPlanDataService.class);
	
	private Map<Integer, MediaPlan> list;
	
	public MediaPlanDataService() {
		LOG.debug("inside MediaPlanDataService ctor");
		list = new HashMap<Integer, MediaPlan>();
		
		// Load an initial media plan
		MediaPlan mediaPlan = new MediaPlan(12345);
		
		Map<Integer, Item> itemMap = new LinkedHashMap<Integer, Item>();
		
		Calendar date = Calendar.getInstance();
		Date startDate = date.getTime();
		
		Item item = new Item();
		item.setId(1);
		item.setInvestment(31250);
		item.setProductName("VDP + Email");
		item.setQty(125000);
		item.setRate(25);
		item.setUom("Each");
		item.setStartDate(startDate);
		itemMap.put(item.getId(), item);
		
		mediaPlan.setItemMap(itemMap);
		
		list.put(mediaPlan.getId(), mediaPlan);
	}
	
	public MediaPlan getMediaPlan (Integer id) throws Exception {
		MediaPlan mediaPlan = null;
		
		if(list.containsKey(id)) {
			mediaPlan = list.get(id);
		}
		else {
			throw new Exception ("Media plan not found");
		}
		
		return mediaPlan;
	}
	
	public MediaPlan addMediaPlan (Integer id) throws Exception {
		
		LOG.debug("inside addMediaPlan");
		
		MediaPlan mediaPlan = null;
		
		if(list.containsKey(id)) {
			throw new Exception("Media Plan already exists");
		}
		else {
			mediaPlan = new MediaPlan(id);
			list.put(id, mediaPlan);
		}
		
		return mediaPlan;
	}
	
	public Integer addItemToMediaPlan(Integer mediaPlanId, Item item) throws Exception {
		Integer nextItemId;
		
		LOG.debug("inside addItemToMediaPlan");
		
		if(list.containsKey(mediaPlanId)) {
			throw new Exception ("Media Plan not found");
		}
		
		MediaPlan mediaPlan = list.get(mediaPlanId);
		
		Map<Integer, Item> itemMap = mediaPlan.getItemMap();
		
		nextItemId = itemMap.size() + 1;
		
		item.setId(nextItemId);
		
		itemMap.put(nextItemId, item);
		
		return nextItemId;
	}
	
	public void updateMediaPlanItem(Integer mediaPlanId, Item item) throws Exception {
		
		LOG.debug("inside updateMediaPlanItem");
		
		if(list.containsKey(mediaPlanId)) {
			throw new Exception ("Media Plan not found");
		}
		
		MediaPlan mediaPlan = list.get(mediaPlanId);
		
		Map<Integer, Item> itemMap = mediaPlan.getItemMap();
		
		itemMap.put(item.getId(), item);
		
	}
	
	public void deleteMediaPlanItem(Integer mediaPlanId, Integer itemId) throws Exception {
		
		LOG.debug("inside deleteMediaPlanItem");
		
		if(list.containsKey(mediaPlanId)) {
			throw new Exception ("Media Plan not found");
		}
		
		MediaPlan mediaPlan = list.get(mediaPlanId);
		
		Map<Integer, Item> itemMap = mediaPlan.getItemMap();
		
		itemMap.remove(itemId);
		
	}
	
}
