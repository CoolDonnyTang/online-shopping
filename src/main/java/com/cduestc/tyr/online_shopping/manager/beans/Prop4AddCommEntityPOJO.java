package com.cduestc.tyr.online_shopping.manager.beans;

import java.util.Set;

public class Prop4AddCommEntityPOJO {
	private Integer propId;
	private String propName;
	private Set<String> props;
	public Integer getPropId() {
		return propId;
	}
	public void setPropId(Integer propId) {
		this.propId = propId;
	}
	public Set<String> getProps() {
		return props;
	}
	public void setProps(Set<String> props) {
		this.props = props;
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	
}
