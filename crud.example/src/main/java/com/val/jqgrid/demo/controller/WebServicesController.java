package com.val.jqgrid.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.val.jqgrid.demo.payload.response.GetMediaPlanResponse;
import com.val.jqgrid.demo.payload.response.GetTestResponse;
import com.val.jqgrid.demo.payload.response.Payload;
import com.val.jqgrid.demo.service.MediaPlanDataService;
import com.val.jqgrid.demo.view.Item;
import com.val.jqgrid.demo.view.MediaPlan;

@Controller
public class WebServicesController {
	
	private static Logger LOG = Logger.getLogger(WebServicesController.class);
	
	@Autowired
	private MediaPlanDataService mediaPlanDataService;

	@ResponseBody
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
	
	@ResponseBody
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
	
	@ResponseBody
	@RequestMapping(value="/services/test", method = RequestMethod.GET)
	public GetTestResponse getTest() {
		
		LOG.debug("inside getTest");
		
		GetTestResponse result = new GetTestResponse();
		
		result.setMsg("Hello, World!");
		
		return result;
	}
	
}
