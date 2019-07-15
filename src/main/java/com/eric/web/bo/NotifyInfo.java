package com.eric.web.bo;

//for dashboard.html notification
public class NotifyInfo {
 
	private String plateform;
	private String message;
	private String transdate;
	
	
	public String getTransdate() {
		return transdate;
	}
	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}
	public String getPlateform() {
		return plateform;
	}
	public void setPlateform(String plateform) {
		this.plateform = plateform;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
