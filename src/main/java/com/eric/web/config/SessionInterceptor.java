package com.eric.web.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class SessionInterceptor implements HandlerInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
	 @Override
	    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		 	logger.info("=====start session preHandle!");
		    String uri = request.getRequestURI();
	       // HttpSession session = request.getSession();
		    String token="";
	        if (uri.endsWith("/login") || uri.endsWith("/loginact")||
	        		uri.endsWith("/register")||uri.endsWith("/registeract")) {
	            return true;
	        }
	        
	        Cookie[] cookies = request.getCookies();
	        if(cookies != null) {
	        	 for(Cookie cookie : cookies) {
	        		 if("token".equals(cookie.getName()))
	        		 {
	        			 token=cookie.getValue();
	        			 if(!"".equals(token))
	        			 {
	        			 	return true;
	        			 }
	        		 }
	        	 }	        	
	        }
	        
	       
	       response.sendRedirect("login");
   		 
	        return false;
	    }
	 
	 @Override
	    public void postHandle(HttpServletRequest request,
	                           HttpServletResponse response, Object handler,
	                           ModelAndView modelAndView) throws Exception {
	    }
	 
	 @Override
	    public void afterCompletion(HttpServletRequest request,
	                                HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {

	    }
}
