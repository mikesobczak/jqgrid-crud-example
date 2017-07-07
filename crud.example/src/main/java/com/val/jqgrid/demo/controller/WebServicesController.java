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
	
	DateFormat format = new SimpleDateFormat("MMM/dd/yyyy", Locale.ENGLISH);
	
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
	
	/**
	 * This was used for testing via SoapUI.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/services/mediaplan/additem", method = RequestMethod.POST)
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
	
	/**
	 * This was the first pass at an endpoint that would be called by jqgrid.
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/services/mediaplan/addupdateitem", method = RequestMethod.POST)
	public AddItemResponse addUpdateItem2(
			@RequestParam("startDate") String startDate,
			@RequestParam("qty") Integer qty,
			@RequestParam("oper") String oper,
			@RequestParam("id") String id,
			@RequestParam("mediaPlanId") String mpId,
			@RequestParam("productName") String productName,
			@RequestParam("uom") String uom,
			@RequestParam("rate") Double rate,
			@RequestParam("investment") Double investment
			) throws Exception {
		
		LOG.debug("inside addUpdateItem2");
		
		Integer mediaPlanId = new Integer(mpId);
		
		Date date = format.parse(startDate);
		
		LOG.debug("startDate = " + date);
		LOG.debug("qty = " + qty);
		LOG.debug("oper = " + oper);
		LOG.debug("id = " + id);
		LOG.debug("mediaPlanId = " + mediaPlanId);
		LOG.debug("productName = " + productName);
		LOG.debug("uom = " + uom);
		
		AddItemResponse result = new AddItemResponse();
		
		Item item = new Item();
		item.setStartDate(date);
		item.setQty(qty);
		item.setProductName(productName);
		item.setUom(uom);
		item.setRate(rate);
		item.setInvestment(investment);
		
		try {
			Integer itemId = mediaPlanDataService.addItemToMediaPlan(mediaPlanId, item);
			
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
	@RequestMapping(value="/services/mediaplan/addupdateitem3", method = RequestMethod.POST)
	public AddItemResponse addUpdateItem3(@RequestParam("startDate") String startDate,
			@RequestParam("qty") Integer qty,
			@RequestParam("oper") String oper,
			@RequestParam("id") String id
			) throws Exception {
		
		LOG.debug("inside addUpdateItem3");
		
		Date date = format.parse(startDate);
		
		LOG.debug("startDate = " + date);
		LOG.debug("qty = " + qty);
		LOG.debug("oper = " + oper);
		LOG.debug("id = " + id);
		
		AddItemResponse result = new AddItemResponse();
		
		result.setPayload("Item added successfully");
		result.setReturnCode(200);
		result.setException("");
		
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
