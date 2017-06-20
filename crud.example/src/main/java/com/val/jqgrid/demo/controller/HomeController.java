package com.val.jqgrid.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	 @Value("${code.version}") 
	 String _codeVersion;
	
	private static Logger LOG = Logger.getLogger(HomeController.class);
	
	@RequestMapping("/home")
	public ModelAndView getHome()
	{
		LOG.debug("Inside HomeController.getHome()");
		//ModelAndView mv = new ModelAndView("home");
		
		ModelAndView mv = new ModelAndView("home", "codeVer", this._codeVersion);
		
		return mv;
	}

}
