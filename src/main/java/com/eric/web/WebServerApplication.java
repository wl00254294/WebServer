package com.eric.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WebServerApplication {

	public static void main(String[] args) {
		//first
		SpringApplication.run(WebServerApplication.class, args);
	}

}
