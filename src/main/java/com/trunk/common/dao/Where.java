package com.trunk.common.dao;

/**
 * 
 * @author 周颖
 *
 */
public class Where {

public Where(){}
	
	public Where(String property, String value, String matchType) {
		this.property = property;
		this.value = value;
		this.matchType = matchType;
	}


	private String property;
	private String value;
	private String matchType;
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	
}
