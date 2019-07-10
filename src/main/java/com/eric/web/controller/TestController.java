package com.eric.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String User(HttpServletRequest request,Model model) throws Exception {
		logger.info("call for test");
		List<String> list=new ArrayList<String>();
		list.add("eric1");
		list.add("eric2");
		list.add("eric3");
		
		model.addAttribute("name", "back end");
		model.addAttribute("mylist", list);
		return "test.html";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginfun(Model model) {
		//@PathVariable("name")String name
		    
	        return "login.html";
	}

}
