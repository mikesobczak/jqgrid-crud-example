package com.val.jqgrid.demo.dao.mock;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.val.jqgrid.demo.view.Item;
import com.val.jqgrid.demo.view.MediaPlan;

public class MediaPlans {
	
	private static Logger LOG = Logger.getLogger(MediaPlans.class);
	
	/*
	private Map<Integer, MediaPlan> list;
	
	public MediaPlans () {
		LOG.debug("inside MediaPlans ctor");
		list = new HashMap<Integer, MediaPlan>();
	}

	public Map<Integer, MediaPlan> getList() {
		return list;
	}

	public void setList(Map<Integer, MediaPlan> list) {
		this.list = list;
	}
	*/
	
	//private MediaPlans mediaPlans;
	private Map<Integer, MediaPlan> list;
	
	public MediaPlans() {
		LOG.debug("inside MediaPlans ctor");
		list = new HashMap<Integer, MediaPlan>();
	}
	
	public MediaPlan addMediaPlan (Integer id) {
		
		MediaPlan mediaPlan = null;
		
		if(list.containsKey(id)) {
			return list.get(id);
		}
		else {
			mediaPlan = new MediaPlan(id);
		}
		
		return mediaPlan;
	}
	
	public Integer addItemToMediaPlan(Integer mediaPlanId, Item item) {
		Integer nextItemId;
		
		nextItemId = 0;
		
		return nextItemId;
	}
	
	public void updateMediaPlanItem(Integer mediaPlanId, Item item) {
		
	}
	
	public void deleteMediaPlanItem(Integer mediaPlanId, Integer itemId) {
		
	}
	
}
