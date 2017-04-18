package com.cduestc.tyr.online_shopping.beans;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_detail")
public class OrderDetailBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer belongOrderId;
	private Integer commEntityId;
	private Integer amount;
	private Double salePrice;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBelongOrderId() {
		return belongOrderId;
	}
	public void setBelongOrderId(Integer belongOrderId) {
		this.belongOrderId = belongOrderId;
	}
	public Integer getCommEntityId() {
		return commEntityId;
	}
	public void setCommEntityId(Integer commEntityId) {
		this.commEntityId = commEntityId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	
}
