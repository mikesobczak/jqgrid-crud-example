package com.val.jqgrid.demo.payload.request;

import com.val.jqgrid.demo.view.Item;

public class AddItemRequest {
	
	private Integer mediaPlanId;
	private Item item;
	
	public Integer getMediaPlanId() {
		return mediaPlanId;
	}
	public void setMediaPlanId(Integer mediaPlanId) {
		this.mediaPlanId = mediaPlanId;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}

}
