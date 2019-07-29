package com.eric.web.method;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.eric.web.bo.Data;

public class RestClient {
	
	 private RestTemplate rest;
	 private HttpHeaders headers;
	 private HttpStatus status;
	  
	 public RestClient() {
		    this.rest = new RestTemplate();
		    this.headers = new HttpHeaders();
		    headers.add("Content-Type", "application/json");
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.add("Accept", "*/*");
    }
	  
	 public String get(String server,String uri) {
		    HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
		    this.setStatus(responseEntity.getStatusCode());
		    return responseEntity.getBody();
	 }

	 public String post(String server,String uri, String json) {   
		    HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.POST, requestEntity, String.class);
		    this.setStatus(responseEntity.getStatusCode());
		    return responseEntity.getBody();
	}

	 public HttpStatus getStatus() {
		    return status;
	 }

	 public void setStatus(HttpStatus status) {
		    this.status = status;
	 } 	  
}
