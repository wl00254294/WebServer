package com.eric.web.restcontroller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest")
public class LoginRest {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginRest.class);
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String User(HttpServletRequest request,Model model) throws Exception {
		return "aaa";
	}
	

}
