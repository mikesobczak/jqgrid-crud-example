package com.val.jqgrid.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.val.jqgrid.demo.payload.request.AddItemRequest;
import com.val.jqgrid.demo.payload.response.AddItemResponse;
import com.val.jqgrid.demo.payload.response.GetMediaPlanResponse;
import com.val.jqgrid.demo.payload.response.GetTestResponse;
import com.val.jqgrid.demo.payload.response.Payload;
import com.val.jqgrid.demo.service.MediaPlanDataService;
import com.val.jqgrid.demo.view.Item;
import com.val.jqgrid.demo.view.MediaPlan;

@RestController
public class WebServicesController {
	
	private static Logger LOG = Logger.getLogger(WebServicesController.class);
	
	@Autowired
	private MediaPlanDataService mediaPlanDataService;

	@RequestMapping(value="/services/mediaplan/{id}", method = RequestMethod.GET)
	public GetMediaPlanResponse getMediaPlan(@PathVariable(value = "id") String id) {
		
		LOG.debug("inside getMediaPlan");
		LOG.debug("id = " + id);
		
		GetMediaPlanResponse result = new GetMediaPlanResponse();
		
		Payload payload = new Payload();
		
		try {
			MediaPlan mediaPlan = mediaPlanDataService.getMediaPlan(new Integer(id));
			
			List<Item> items = mediaPlan.getItems();
			
			LOG.debug("mediaPlan: " + mediaPlan);
			
			payload.setRows(items);
			payload.setPage(1);
			payload.setRecords(items.size());
			payload.setTotal(items.size());
			
			result.setPayload(payload);
			result.setReturnCode(200);
			result.setException("");
		}
		catch (Exception e) {
			LOG.error(e.getMessage());
			
			result.setReturnCode(500);
			result.setException(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value="/services/mediaplan", method = RequestMethod.GET)
	public GetMediaPlanResponse getMediaPlan(@RequestParam("id") String id, @RequestParam("page") String page, @RequestParam("rows") String rows, HttpSession session) {
		
		LOG.debug("inside getMediaPlan");
		LOG.debug("id = " + id);
		
		GetMediaPlanResponse result = new GetMediaPlanResponse();
		
		Payload payload = new Payload();
		
		try {
			MediaPlan mediaPlan = mediaPlanDataService.getMediaPlan(new Integer(id));
			
			List<Item> items = mediaPlan.getItems();
			
			LOG.debug("mediaPlan: " + mediaPlan);
			
			payload.setRows(items);
			payload.setPage(1);
			payload.setRecords(items.size());
			payload.setTotal(items.size());
			
			result.setPayload(payload);
			result.setReturnCode(200);
			result.setException("");
		}
		catch (Exception e) {
			LOG.error(e.getMessage());
			
			result.setReturnCode(500);
			result.setException(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value="/services/mediaplan/additem", method = RequestMethod.POST)
	//@RequestMapping(value="/services/mediaplan/additem")
	public AddItemResponse addItemToMediaPlan(@RequestBody AddItemRequest request) {
		
		LOG.debug("inside addItemToMediaPlan");
		
		Integer mediaPlanId = request.getMediaPlanId();
		
		LOG.debug("media plan id = " + request.getMediaPlanId());
		
		AddItemResponse result = new AddItemResponse();
		
		try {
			Integer itemId = mediaPlanDataService.addItemToMediaPlan(mediaPlanId, request.getItem());
			
			result.setPayload("Item added successfully");
			result.setReturnCode(200);
			result.setException("");
			
		}
		catch (Exception e) {
			LOG.error(e.getMessage());
			
			result.setPayload("Item not added");
			result.setReturnCode(500);
			result.setException(e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/services/test", method = RequestMethod.GET)
	public GetTestResponse getTest() {
		
		LOG.debug("inside getTest");
		
		GetTestResponse result = new GetTestResponse();
		
		result.setMsg("Hello, World!");
		
		return result;
	}
	
}
