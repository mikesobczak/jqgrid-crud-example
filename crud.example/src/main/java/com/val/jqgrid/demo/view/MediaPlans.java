package com.val.jqgrid.demo.view;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class MediaPlans {
	
	private static Logger LOG = Logger.getLogger(MediaPlans.class);
	
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
	
}
