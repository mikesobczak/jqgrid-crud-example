package com.val.jqgrid.demo.dao.mock;

import org.apache.log4j.Logger;

import com.val.jqgrid.demo.view.Item;
import com.val.jqgrid.demo.view.MediaPlan;
import com.val.jqgrid.demo.view.MediaPlans;

public class DataStore {
	
	private static Logger LOG = Logger.getLogger(MediaPlans.class);
	
	private MediaPlans mediaPlans;
	
	public DataStore() {
		LOG.debug("inside DataStore ctor");
		mediaPlans = new MediaPlans();
	}
	
	public MediaPlan addMediaPlan (Integer id) {
		
		MediaPlan mediaPlan = null;
		
		if(mediaPlans.getList().containsKey(id)) {
			return mediaPlans.getList().get(id);
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
