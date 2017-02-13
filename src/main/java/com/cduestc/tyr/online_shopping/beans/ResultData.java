package com.cduestc.tyr.online_shopping.beans;

public class ResultData {
	private Integer status;
	private String info;
	private Object data;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResultData [status=" + status + ", info=" + info + ", data="
				+ data + "]";
	}
	
}
