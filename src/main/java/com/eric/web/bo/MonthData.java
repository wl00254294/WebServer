package com.eric.web.bo;

import java.util.List;

public class MonthData {
	private String categorie;
	private List<Data> values;
	
	
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public List<Data> getValues() {
		return values;
	}
	public void setValues(List<Data> values) {
		this.values = values;
	}
}
