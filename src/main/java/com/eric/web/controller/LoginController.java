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

import com.eric.web.bo.NotifyInfo;
import com.eric.web.bo.TranscationInfo;

@Controller
public class LoginController {
private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request,Model model,HttpSession session) throws Exception {
		return "test.html";
	}

	@RequestMapping(value = "/weekreport", method = RequestMethod.GET)
	public String report(HttpServletRequest request,Model model,HttpSession session) throws Exception {
		
	
		
		
		return "report_week.html";
	}
	
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(HttpServletRequest request,Model model,HttpSession session) throws Exception {
		
	
		
		
		return "dashboard.html";
	}

	//forward to home page
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request,Model model,HttpSession session) throws Exception {
		logger.info("susses login to home page");
		String username=(String) session.getAttribute("USERNAME");
		
		
		List<TranscationInfo> list=new ArrayList<TranscationInfo>();
		List<NotifyInfo> list2=new ArrayList<NotifyInfo>();
        
		//test data for transcation
		for(int i=0;i<4;i++)
		{
			TranscationInfo tif=new TranscationInfo();
			tif.setOrderno("#1234");
			if(i==0)
			{
			 tif.setState("Finish");
			}else if(i==1)
			{
				tif.setState("Pend");
			}else
			{
				tif.setState("Cancle");
			}
			tif.setOperator("Eric Wu");
			tif.setLocate("Tawian");
			tif.setDistance(Integer.toString((i+1)*10)+ " km");
			tif.setSdt("2019-01-01");
			tif.setDue("2019-08-09");
			list.add(tif);
		}
		model.addAttribute("orders", list);			

		//test data for notification
		for(int i=0;i<5;i++)
		{
			NotifyInfo notify=new NotifyInfo();
			notify.setPlateform("Plateform"+Integer.toString((i+1)));
			notify.setMessage("You receive message from "+ Integer.toString(i+1));
			notify.setTransdate("2018-07-15");
			list2.add(notify);
		}
		
		model.addAttribute("notifys", list2);
		model.addAttribute("username", username);
		
		
		return "index.html";
		
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
		   
		if(("admin".equals(username) || "eric".equals(username)) && "admin".equals(password))
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
	
	
	@RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
       
        return "redirect:login?logout";
    }
}
