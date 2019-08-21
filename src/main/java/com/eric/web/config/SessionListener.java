package com.eric.web.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebListener
public class SessionListener implements HttpSessionListener {
	private static final Logger logger = LoggerFactory.getLogger(HttpSessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        logger.info("==== Session is created ====");
        event.getSession().setMaxInactiveInterval(15*60);
        
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    	logger.info("==== Session is destroyed ====");
    }
}