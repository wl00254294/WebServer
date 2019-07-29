package com.eric.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eric.web.bo.Data;
import com.eric.web.bo.MonthData;
import com.eric.web.bo.NotifyInfo;
import com.eric.web.bo.TranscationInfo;
import com.eric.web.bo.WeekData;
import com.eric.web.method.JacksonUtil;
import com.eric.web.method.RestClient;

@Controller
@PropertySource("classpath:production.properties")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Value("${middleserver}")
    private String middleserver;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request,Model model,HttpSession session) throws Exception {
		
		RestClient cli=new RestClient();
		JacksonUtil jt=new JacksonUtil();
		
		//post method
		Data data=new Data();
		data.setRate("1.12");
		data.setValue(100);		
		String pdata=jt.bean2Json(data);		
		String pout=cli.post(middleserver, "test", pdata);
		
		
		model.addAttribute("out", pout);
		
		return "test.html";
	}

	@RequestMapping(value = "/weekreport", method = RequestMethod.GET)
	public String dreport(HttpServletRequest request,Model model,HttpSession session
			,HttpServletResponse response) throws Exception {
	 	String cookieval="";
		Cookie[] cookies = request.getCookies();
        if(cookies != null) {
    	    for(Cookie cookie : cookies) {
    	    	//get AWS Application Load Balance Cookie(Stick session)
    	        if("AWSALB".equals(cookie.getName()))
    	        {
    	        	cookieval = cookie.getValue();
    	            Cookie rescookie = new Cookie("AWSALB", cookieval);
    	            response.addCookie(rescookie);
    	        }
    	      
    	    }
        }			
      
			
		return "report_week.html";
	}
	
	@RequestMapping(value = "/monthreport", method = RequestMethod.GET)
	public String mreport(HttpServletRequest request,Model model,HttpSession session
			,HttpServletResponse response) throws Exception {
	 	String cookieval="";
		Cookie[] cookies = request.getCookies();
        if(cookies != null) {
    	    for(Cookie cookie : cookies) {
    	    	//get AWS Application Load Balance Cookie(Stick session)
    	        if("AWSALB".equals(cookie.getName()))
    	        {
    	        	cookieval = cookie.getValue();
    	            Cookie rescookie = new Cookie("AWSALB", cookieval);
    	            response.addCookie(rescookie);
    	        }
    	      
    	    }
        }			
      		
		return "report_month.html";
	}	
	
	@RequestMapping(value = "/costreport", method = RequestMethod.GET)
	public String creport(HttpServletRequest request,Model model,HttpSession session
			,HttpServletResponse response) throws Exception {
	 	String cookieval="";
		Cookie[] cookies = request.getCookies();
        if(cookies != null) {
    	    for(Cookie cookie : cookies) {
    	    	//get AWS Application Load Balance Cookie(Stick session)
    	        if("AWSALB".equals(cookie.getName()))
    	        {
    	        	cookieval = cookie.getValue();
    	            Cookie rescookie = new Cookie("AWSALB", cookieval);
    	            response.addCookie(rescookie);
    	        }
    	      
    	    }
        }			
      
		
		List<String> plates=new ArrayList<String>();
		plates.add("Plate1");
		plates.add("Plate2");
		plates.add("Plate3");
		plates.add("Plate4");
		plates.add("Plate5");
		plates.add("Plate6");
		
		model.addAttribute("myplates", plates);
		return "report_cost.html";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(HttpServletRequest request,Model model,HttpSession session
			,HttpServletResponse response) throws Exception {
		
	 	String cookieval="";
		Cookie[] cookies = request.getCookies();
        if(cookies != null) {
    	    for(Cookie cookie : cookies) {
    	    	//get AWS Application Load Balance Cookie(Stick session)
    	        if("AWSALB".equals(cookie.getName()))
    	        {
    	        	cookieval = cookie.getValue();
    	            Cookie rescookie = new Cookie("AWSALB", cookieval);
    	            response.addCookie(rescookie);
    	        }
    	      
    	    }
        }			
	
		
		
		return "dashboard.html";
	}

	//forward to home page
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request,Model model,HttpSession session
			,HttpServletResponse response) throws Exception {
		logger.info("susses login to home page");
		String username=(String) session.getAttribute("USERNAME");
		
		
	 	String cookieval="";
		Cookie[] cookies = request.getCookies();
        if(cookies != null) {
    	    for(Cookie cookie : cookies) {
    	    	//get AWS Application Load Balance Cookie(Stick session)
    	        if("AWSALB".equals(cookie.getName()))
    	        {
    	        	cookieval = cookie.getValue();
    	            Cookie rescookie = new Cookie("AWSALB", cookieval);
    	            response.addCookie(rescookie);
    	        }
    	      
    	    }
        }			
	

		List<NotifyInfo> list2=new ArrayList<NotifyInfo>();
		List<WeekData> list3 =new ArrayList<WeekData>();
		List<MonthData> list4 =new ArrayList<MonthData>();
		
		//test data for month report
		for(int i=0;i<6;i++)
		{
			
			MonthData month=new MonthData();
			List<Data> k=new ArrayList<Data>();
			for(int j=0;j<2;j++)
			{
				Data tmp=new Data();
				if(j==0)
				{
					tmp.setValue((j+i+1)*10);
					tmp.setRate("This Month");
				}else{
					tmp.setValue((j+i+1)*10);
					tmp.setRate("Last Month");					
				}
				k.add(tmp);
			}
			
			month.setValues(k);
			month.setCategorie("Plate"+Integer.toString((i+1)));
			list4.add(month);
			
		}
		model.addAttribute("monthdata", list4);	
		
		//test data for week report
		for(int i=0;i<6;i++)
		{
			
			WeekData week=new WeekData();
			List<Data> k=new ArrayList<Data>();
			for(int j=0;j<2;j++)
			{
				Data tmp=new Data();
				if(j==0)
				{
					tmp.setValue((j+i+1)*10);
					tmp.setRate("This Week");
				}else{
					tmp.setValue((j+i+1)*10);
					tmp.setRate("Last Week");					
				}
				k.add(tmp);
			}
			
			week.setValues(k);
			week.setCategorie("Plate"+Integer.toString((i+1)));
			list3.add(week);
			
		}
		model.addAttribute("weekdata", list3);	
		
		
		//Get Dashboard Data
		RestClient cli=new RestClient();
		JacksonUtil ju=new JacksonUtil();
		try {
			String repdata=cli.post(middleserver, "getTransInfo", username);
		
			List<TranscationInfo> orders=ju.json2BeanList(repdata,TranscationInfo.class );
		
			model.addAttribute("orders", orders);			
		}catch(Exception e)
		{
			logger.info("=====error calling getTransInfo====");
		}
		
		
		try{
			String repdata=cli.post(middleserver, "getNoteInfo", username);
			List<NotifyInfo> notifys=ju.json2BeanList(repdata,NotifyInfo.class );
			model.addAttribute("notifys", notifys);
		}catch(Exception e)
		{
			logger.info("=====error calling getNoteInfo====");
		}
		
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
        	            Cookie rescookie = new Cookie("AWSALB", cookieval);
        	            response.addCookie(rescookie);
        	        }
        	      
        	    }
            }			
            
            model.setViewName("redirect:/home");
			return model;
		}

		  model.setViewName("redirect:/login?error");
	      return model;
	}	
	
	
	@RequestMapping(value = "/logout")
    public String logout(HttpSession session,HttpServletRequest request){
        session.invalidate();
      
        return "redirect:login?logout";
    }
}
