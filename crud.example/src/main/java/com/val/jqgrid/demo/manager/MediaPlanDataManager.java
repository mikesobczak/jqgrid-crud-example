package com.val.jqgrid.demo.manager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.val.jqgrid.demo.dao.mock.DataStore;
import com.val.jqgrid.demo.view.Item;
import com.val.jqgrid.demo.view.MediaPlan;

public class MediaPlanDataManager {
	
	private static Logger LOG = Logger.getLogger(MediaPlanDataManager.class);
	
	@Autowired
	private DataStore dataStore;
	
	public MediaPlan getMediaPlan (Integer id) throws Exception {
		return dataStore.getMediaPlan(id);
	}
	
	public MediaPlan addMediaPlan (Integer id) throws Exception {
		return dataStore.addMediaPlan(id);
	}
	
	public Integer addItemToMediaPlan(Integer mediaPlanId, Item item) throws Exception {
		return dataStore.addItemToMediaPlan(mediaPlanId, item);
	}
	
	public void updateMediaPlanItem(Integer mediaPlanId, Item item) throws Exception {
		dataStore.updateMediaPlanItem(mediaPlanId, item);
	}
	
	public void deleteMediaPlanItem(Integer mediaPlanId, Integer itemId) throws Exception {
		dataStore.deleteMediaPlanItem(mediaPlanId, itemId);
	}
	
	public DataStore getDataStore() {
		return dataStore;
	}

	public void setDataStore(DataStore dataStore) {
		this.dataStore = dataStore;
	}

}
