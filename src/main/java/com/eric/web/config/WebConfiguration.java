package com.eric.web.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;





@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.eric.web")
public class WebConfiguration extends WebMvcConfigurerAdapter{

	  @Bean  
	  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		  return new PropertySourcesPlaceholderConfigurer();
	  }
	  
	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {  
		  registry.addResourceHandler(
	                "/img/**",
	                "/css/**",
	                "/js/**",
	                "/fonts/**",
	                "/less/**",
	                "/scss/**",
	                "/webfonts/**")
	                .addResourceLocations(
	                        "classpath:/static/img/",
	                        "classpath:/static/css/",
	                        "classpath:/static/js/",
	                        "classpath:/static/fonts/",
	                        "classpath:/static/less/",
	                        "classpath:/static/scss/",
	                        "classpath:/static/webfonts/");
		  
		  
	    
	  }  
	  
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**")
	        .excludePathPatterns("/css/**",
	        		"/img/**",
	        		"/js/**",
	        		"/fonts/**",
	                "/less/**",
	                "/scss/**",
	                "/webfonts/**");
	    }
}
