package com.eric.web.funbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.eric.web.method.Test;

//install bean for com.eric.web.method package,use by html file call function
@Configuration
@ComponentScan(basePackages = "com.eric.web")
public class ThymeleafCall {
	  @Bean(name="Test")
	  public Test mytest()
	  {
		  Test a=new Test();
		  return a;
	  }
}
