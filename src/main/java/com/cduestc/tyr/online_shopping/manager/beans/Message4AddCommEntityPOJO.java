package com.cduestc.tyr.online_shopping.manager.beans;

import java.util.Set;

public class Message4AddCommEntityPOJO {
	private String brandName;
	private Set<Title4AddCommEntityPOJO> commTitles;
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Set<Title4AddCommEntityPOJO> getCommTitles() {
		return commTitles;
	}
	public void setCommTitles(Set<Title4AddCommEntityPOJO> commTitles) {
		this.commTitles = commTitles;
	}
	
}
