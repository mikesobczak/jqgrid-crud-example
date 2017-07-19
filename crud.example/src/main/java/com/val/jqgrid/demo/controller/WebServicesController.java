package com.val.jqgrid.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	
	DateFormat addFormat = new SimpleDateFormat("MMM/dd/yyyy", Locale.ENGLISH);
	DateFormat editFormat = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
	
	@Autowired
	private MediaPlanDataService mediaPlanDataService;

	@RequestMapping(value="/services/mediaplan/{id}", method = RequestMethod.GET)
	public GetMediaPlanResponse getMediaPlan(@PathVariable(value = "id") String id) {
		
		LOG.info("inside getMediaPlan");
		LOG.info("id = " + id);
		
		GetMediaPlanResponse result = new GetMediaPlanResponse();
		
		Payload payload = new Payload();
		
		try {
			MediaPlan mediaPlan = mediaPlanDataService.getMediaPlan(new Integer(id));
			
			List<Item> items = mediaPlan.getItems();
			
			//LOG.debug("mediaPlan: " + mediaPlan);
			
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
	
	/**
	 * This version is used when we were doing an Ajax call via the datatype function() of the jqgrid.
	 * 
	 * @param id
	 * @param page
	 * @param rows
	 * @param session
	 * @return
	 */
	//@RequestMapping(value="/services/mediaplan", method = RequestMethod.GET)
	public GetMediaPlanResponse getMediaPlan(@RequestParam("id") String id, @RequestParam("page") String page, @RequestParam("rows") String rows, HttpSession session) {
		
		LOG.info("inside getMediaPlan");
		LOG.info("id = " + id);
		
		GetMediaPlanResponse result = new GetMediaPlanResponse();
		
		Payload payload = new Payload();
		
		try {
			MediaPlan mediaPlan = mediaPlanDataService.getMediaPlan(new Integer(id));
			
			List<Item> items = mediaPlan.getItems();
			
			//LOG.debug("mediaPlan: " + mediaPlan);
			
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
	
	/**
	 * This version is used when we use the standard url way to get data for jqgrid.
	 * 
	 * @param id
	 * @param page
	 * @param rows
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/services/mediaplan", method = RequestMethod.GET)
	public GetMediaPlanResponse getMediaPlan(@RequestParam("id") String id, 
			@RequestParam("_search") String search, 
			@RequestParam("nd") String nd,
			@RequestParam("rows") String rows,
			@RequestParam("page") String page, 
			@RequestParam("sidx") String sidx, 
			@RequestParam("sord") String sord,
			HttpSession session) {
		
		LOG.info("inside getMediaPlan");
		LOG.info("id = " + id);
		
		GetMediaPlanResponse result = new GetMediaPlanResponse();
		
		Payload payload = new Payload();
		
		try {
			Integer rowsRequested = new Integer(rows);
			MediaPlan mediaPlan = mediaPlanDataService.getMediaPlan(new Integer(id));
			
			List<Item> items = mediaPlan.getItems();
			
			//LOG.debug("mediaPlan: " + mediaPlan);
			
			payload.setRows(items);  // rows returned
			payload.setPage(1);
			payload.setRecords(items.size());
			
			// set the "total", the total number of pages available
			if(rowsRequested == 0) {
				payload.setTotal(1); // default of 1
			}
			else if(items.size() < rowsRequested) {
				payload.setTotal(1); // default of 1
			}
			else {
				
				// total pages = total items avail / # rows requested
				
				int total = items.size() / rowsRequested;
				
				if(items.size() % rowsRequested > 1) {
					total++;
				}
				
				payload.setTotal(total);  
			}
			
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
	
	/**
	 * This was used for testing via SoapUI.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/services/mediaplan/additem", method = RequestMethod.POST)
	public AddItemResponse addItemToMediaPlan(@RequestBody AddItemRequest request) {
		
		LOG.info("inside addItemToMediaPlan");
		
		Integer mediaPlanId = request.getMediaPlanId();
		
		LOG.info("media plan id = " + request.getMediaPlanId());
		
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
	
	/**
	 * This was the first pass at an endpoint that would be called by jqgrid.
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/services/mediaplan/addupdateitem", method = RequestMethod.POST)
	public AddItemResponse addUpdateItem(
			@RequestParam(required=false) String startDate,
			@RequestParam(required=false) Integer qty,
			@RequestParam("oper") String oper,
			@RequestParam("id") String id,
			@RequestParam("mediaPlanId") String mpId,
			@RequestParam(required=false) String productName,
			@RequestParam(required=false) String uom,
			@RequestParam(required=false) Double rate,
			@RequestParam(required=false) Double investment
			) throws Exception {
		
		LOG.info("inside addUpdateItem2");
		LOG.info("oper = " + oper);
		
		Integer mediaPlanId = new Integer(mpId);
		
		LOG.debug("startDate = " + startDate);
		LOG.debug("qty = " + qty);
		LOG.debug("id = " + id);
		LOG.debug("mediaPlanId = " + mediaPlanId);
		LOG.debug("productName = " + productName);
		LOG.debug("uom = " + uom);
		
		Date date = null;
		
		AddItemResponse result = new AddItemResponse();
		String payload = "";
		
		Item item = null;
		
		try {
			
			if(oper.equalsIgnoreCase("add") || oper.equalsIgnoreCase("edit")) {
				
				item = new Item();
				item.setQty(qty);
				item.setProductName(productName);
				item.setUom(uom);
				item.setRate(rate);
				item.setInvestment(investment);
				
				date = editFormat.parse(startDate);
				//date = addFormat.parse(startDate);
				
				item.setStartDate(date);
				LOG.debug("date = " + date);
			}
			
			Integer itemId = null;
			if(oper.equalsIgnoreCase("edit") || oper.equalsIgnoreCase("del")) {
				itemId = new Integer(id);
			}
			
			if(oper.equalsIgnoreCase("add")) {
				
				itemId = mediaPlanDataService.addItemToMediaPlan(mediaPlanId, item);
			}
			else if(oper.equalsIgnoreCase("edit")) {
				
				item.setId(itemId);
				mediaPlanDataService.updateMediaPlanItem(mediaPlanId, item);
			}
			else if(oper.equalsIgnoreCase("del")) {
				
				mediaPlanDataService.deleteMediaPlanItem(mediaPlanId, itemId);
			}
			
			result.setItemId(itemId);
			result.setPayload("Action processed successfully");
			result.setReturnCode(200);
			result.setException("");
			
		}
		catch (Exception e) {
			LOG.error(e.getMessage());
			
			result.setPayload("Error thrown");
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
