package com.eric.web.bo;

import java.util.List;

public class CostData {
 private String platename;
 List<CostInfo> info;
 
 
public String getPlatename() {
	return platename;
}
public void setPlatename(String platename) {
	this.platename = platename;
}
public List<CostInfo> getInfo() {
	return info;
}
public void setInfo(List<CostInfo> info) {
	this.info = info;
}
 
 
}
