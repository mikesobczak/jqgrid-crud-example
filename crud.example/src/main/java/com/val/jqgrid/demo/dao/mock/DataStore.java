package com.val.jqgrid.demo.dao.mock;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.val.jqgrid.demo.service.MediaPlanDataService;
import com.val.jqgrid.demo.view.Item;
import com.val.jqgrid.demo.view.MediaPlan;

public class DataStore {
	
	private static Logger LOG = Logger.getLogger(MediaPlanDataService.class);
	
	private Map<Integer, MediaPlan> list;
	
	public DataStore() {
		LOG.debug("inside DataStore ctor");
		list = new HashMap<Integer, MediaPlan>();
		
		// Load an initial media plan
		MediaPlan mediaPlan = new MediaPlan(12345);
		
		Map<Integer, Item> itemMap = new LinkedHashMap<Integer, Item>();
		
		Calendar date = Calendar.getInstance();
		Date startDate = date.getTime();
		
		Item item = new Item();
		item.setId(1);
		item.setInvestment(3125);
		item.setProductName("VDP + Email");
		item.setQty(125000);
		item.setRate(25);
		item.setUom("CPM");
		item.setStartDate(startDate);
		itemMap.put(item.getId(), item);
		
		item = new Item();
		item.setId(2);
		item.setInvestment(4940);
		item.setProductName("VDP + Email");
		item.setQty(260000);
		item.setRate(19);
		item.setUom("CPM");
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
	
	/**
	 * 
	 * @param mediaPlanId
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public Integer addItemToMediaPlan(Integer mediaPlanId, Item item) throws Exception {
		Integer nextItemId;
		
		LOG.debug("inside addItemToMediaPlan");
		LOG.debug("mediaPlanId: " + mediaPlanId);
		
		//LOG.debug("mediaPlanMap: " + list);
		
		if(list.containsKey(mediaPlanId) == false) {
			throw new Exception ("Media Plan not found");
		}
		
		// Get the media plan object from the internal Map.
		MediaPlan mediaPlan = list.get(mediaPlanId);
		
		Map<Integer, Item> itemMap = mediaPlan.getItemMap();
		
		nextItemId = itemMap.size() + 1;
		
		item.setId(nextItemId);
		
		itemMap.put(nextItemId, item);
		
		// Update entry in internal Map of media plans.
		list.put(mediaPlanId, mediaPlan);
		
		return nextItemId;
	}
	
	public void updateMediaPlanItem(Integer mediaPlanId, Item item) throws Exception {
		
		LOG.debug("inside updateMediaPlanItem");
		
		LOG.debug("current list: " + list);
		
		if(list.containsKey(mediaPlanId) == false) {
			throw new Exception ("Media Plan not found");
		}
		
		MediaPlan mediaPlan = list.get(mediaPlanId);
		
		Map<Integer, Item> itemMap = mediaPlan.getItemMap();
		
		itemMap.put(item.getId(), item);
		
		list.put(mediaPlanId, mediaPlan);
		
		LOG.debug("updated list: " + list);
		
	}
	
	public void deleteMediaPlanItem(Integer mediaPlanId, Integer itemId) throws Exception {
		
		LOG.debug("inside deleteMediaPlanItem");
		
		if(list.containsKey(mediaPlanId) == false) {
			throw new Exception ("Media Plan not found");
		}
		
		MediaPlan mediaPlan = list.get(mediaPlanId);
		
		Map<Integer, Item> itemMap = mediaPlan.getItemMap();
		
		itemMap.remove(itemId);
		
		list.put(mediaPlanId, mediaPlan);
		
	}

}
