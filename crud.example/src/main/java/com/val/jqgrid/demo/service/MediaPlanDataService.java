package com.val.jqgrid.demo.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.val.jqgrid.demo.manager.MediaPlanDataManager;
import com.val.jqgrid.demo.view.Item;
import com.val.jqgrid.demo.view.MediaPlan;

public class MediaPlanDataService {
	
	private static Logger LOG = Logger.getLogger(MediaPlanDataService.class);
	
	@Autowired
	private MediaPlanDataManager mediaPlanDataManager;
	
	public MediaPlanDataService() {
		LOG.debug("inside MediaPlanDataService ctor");
	}
	
	public MediaPlan getMediaPlan (Integer id) throws Exception {
		return mediaPlanDataManager.getMediaPlan(id);
	}
	
	public MediaPlan addMediaPlan (Integer id) throws Exception {
		return mediaPlanDataManager.addMediaPlan(id);
	}
	
	public Integer addItemToMediaPlan(Integer mediaPlanId, Item item) throws Exception {
		
		validateItemAdd(item);
		
		return mediaPlanDataManager.addItemToMediaPlan(mediaPlanId, item);
	}
	
	public void updateMediaPlanItem(Integer mediaPlanId, Item item) throws Exception {
		
		validateItemUpdate(item);
		
		mediaPlanDataManager.updateMediaPlanItem(mediaPlanId, item);
	}
	
	public void deleteMediaPlanItem(Integer mediaPlanId, Integer itemId) throws Exception {
		mediaPlanDataManager.deleteMediaPlanItem(mediaPlanId, itemId);
	}

	public MediaPlanDataManager getMediaPlanDataManager() {
		return mediaPlanDataManager;
	}

	public void setMediaPlanDataManager(MediaPlanDataManager mediaPlanDataManager) {
		this.mediaPlanDataManager = mediaPlanDataManager;
	}

	private void validateItemAdd(Item item) throws Exception {
		validateItemBase(item);
	}
	
	private void validateItemUpdate(Item item) throws Exception { 
		if(item.getId() == null || item.getId().equals("")) {
			throw new Exception ("Item id cannot be null");
		}
		
		validateItemBase(item);
	}
	
	private void validateItemBase(Item item) throws Exception {
		
		if(item.getProductName() == null || item.getProductName().equals("")) {
			throw new Exception ("product name cannot be null");
		}
		
		if(item.getUom() == null || item.getUom().equals("")) {
			throw new Exception ("UOM cannot be null");
		}
		
		if(item.getStartDate() == null) {
			throw new Exception ("Start date cannot be null");
		}
		
		if(item.getQty() == 0) {
			throw new Exception ("Qty cannot be zero");
		}
		
		if(item.getRate() == 0) {
			throw new Exception ("Rate cannot be zero");
		}
		
		if(item.getInvestment() == 0) {
			throw new Exception ("Investment cannot be zero");
		}
		
	}
	
}
