package com.eric.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	//forward to home page
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String User(HttpServletRequest request,Model model,HttpSession session) throws Exception {
		logger.info("susses login to home page");
		String username=(String) session.getAttribute("USERNAME");
		
		model.addAttribute("username", username);
		
		return "home.html";
		
	}
	
	//forward to login page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginfun(Model model) {		    
	        return "login.html";
	}
	
	//login action
	@RequestMapping(value = "/loginact", method = RequestMethod.POST)
	public ModelAndView loginact(HttpServletRequest request,ModelAndView model, HttpSession session,
			HttpServletResponse response,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		   
		if("admin".equals(username) && "admin".equals(password))
		{
			session.setAttribute("USERNAME",username);
			
		 	String cookieval="";
  		 	Cookie[] cookies = request.getCookies();
            if(cookies != null) {
        	    for(Cookie cookie : cookies) {
        	    	//get AWS Application Load Balance Cookie(Stick session)
        	        if("AWSALB".equals(cookie.getName()))
        	        {
        	        	cookieval = cookie.getValue();
        	        }
        	      
        	    }
            }			
            
            model.setViewName("redirect:/home");
            Cookie rescookie = new Cookie("AWSALB", cookieval);
            response.addCookie(rescookie);
			return model;
		}

		  model.setViewName("redirect:/login?error");
	      return model;
	}	

}
