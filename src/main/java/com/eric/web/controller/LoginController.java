package com.eric.web.controller;

import java.io.IOException;
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

import com.eric.web.bo.CostData;
import com.eric.web.bo.Data;
import com.eric.web.bo.MonthData;
import com.eric.web.bo.NotifyInfo;
import com.eric.web.bo.TranscationInfo;
import com.eric.web.bo.User;
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
	public String test(HttpServletRequest request,Model model,HttpSession session
			,HttpServletResponse response) throws Exception {
		
		RestClient cli=new RestClient();
		JacksonUtil jt=new JacksonUtil();
		
		User user=new User();
		user.setUsername("admin");
		user.setPassword("admin");
		
		String token=cli.post(middleserver, "gettoken", jt.bean2Json(user));
		Cookie rescookie = new Cookie("token", token);
		 response.addCookie(rescookie);
		//post method
		//Data data=new Data();
		//data.setRate("1.12");
		//data.setValue(200);		
		//String pdata=jt.bean2Json(data);		
		//String pout=cli.post(middleserver, "test", pdata);
		
		
		model.addAttribute("out", "dddd");
		
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
	
	
	//Cost Report
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
      
		
		return "report_cost.html";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(HttpServletRequest request,Model model,HttpSession session
			,HttpServletResponse response) throws Exception {
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
	

		
		return "dashboard.html";
	}

	//forward to home page
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request,Model model,HttpSession session
			,HttpServletResponse response) throws Exception {
		logger.info("susses login to home page");
		
		String a=request.getHeader("token");
		response.setHeader("token", a);
		
		
		//String username=(String) session.getAttribute("USERNAME");
		//隨便輸入POST不影響(已用token驗證)
		String username="admin";
		
		String token="";
		RestClient cli=new RestClient();
		JacksonUtil ju=new JacksonUtil();		
		
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
    	        
    	        if("token".equals(cookie.getName()))
    	        {
    	        	token=cookie.getValue();
    	        }
    	      
    	    }
        }			
	
        //Get Cost Data
        
		try {
			String scostdata=cli.post(middleserver, "getCostData?token="+token, username);
		
			List<CostData> costdata=ju.json2BeanList(scostdata,CostData.class );
			if(costdata.size()>0)
			{
				 List<String> plates=new ArrayList<String>();
				 for(int i=0;i<costdata.size();i++)
				 {
					 plates.add(costdata.get(i).getPlatename());
				 }
				 model.addAttribute("plates", plates);
				 model.addAttribute("costdata", costdata);
			}else
			{
				logger.info("=====No Cost Data ====");
			}
		
							
		}catch(Exception e)
		{
			logger.error("=====error calling getCostData====");
		}       
        
		
		//test data for month report
		try {
			String smonthdata=cli.post(middleserver, "getMonthData?token="+token, username);
		
			List<MonthData> monthdata=ju.json2BeanList(smonthdata,MonthData.class );
		
			model.addAttribute("monthdata", monthdata);				
		}catch(Exception e)
		{
			logger.error("=====error calling getMonthData====");
		}
		
		
		//test data for week report

		try {
			String sweekdata=cli.post(middleserver, "getWeekData?token="+token, username);
		
			List<WeekData> weekdata=ju.json2BeanList(sweekdata,WeekData.class );
		
			model.addAttribute("weekdata", weekdata);				
		}catch(Exception e)
		{
			logger.error("=====error calling getWeekData====");
		}
				
		
		//Get Dashboard Data

		try {
			String repdata=cli.post(middleserver, "getTransInfo?token="+token, username);
		
			List<TranscationInfo> orders=ju.json2BeanList(repdata,TranscationInfo.class );
		
			model.addAttribute("orders", orders);			
		}catch(Exception e)
		{
			logger.info("=====error calling getTransInfo====");
		}
		
		
		try{
			String repdata=cli.post(middleserver, "getNoteInfo?token="+token, username);
			List<NotifyInfo> notifys=ju.json2BeanList(repdata,NotifyInfo.class );
			model.addAttribute("notifys", notifys);
		}catch(Exception e)
		{
			logger.info("=====error calling getNoteInfo====");
		}
		
		model.addAttribute("username", username);
		
		
		return "index.html";
		
	}
	
	//Translate page
	@RequestMapping(value = "/translate", method = RequestMethod.GET)
	public String translation(HttpServletRequest request,Model model,HttpSession session
			,HttpServletResponse response) {		    
		//String username=(String) session.getAttribute("USERNAME");
		
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
		
		return "translate.html";
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
			@RequestParam("password") String password) throws IOException {
		
		
		  RestClient cli=new RestClient();
		  JacksonUtil jt=new JacksonUtil();
			  
		  
		  if(!"".equals(username)||!"".equals(password))
		  {
			
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
				
				String token=cli.post(middleserver, "gettoken", jt.bean2Json(user));
				Cookie rescookie = new Cookie("token", token);
				response.addCookie(rescookie);
				
				model.setViewName("redirect:/home");
				return model;
				
		  }
	

		  model.setViewName("redirect:/login?error");
	      return model;
	}	
	
	
	@RequestMapping(value = "/logout")
    public String logout(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        session.invalidate();
        
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/websrv");
                    response.addCookie(cookie);
                }

            }
        }
        return "redirect:login?logout";
    }
}
