package com.cduestc.tyr.online_shopping.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shopping_cart")
public class ShoppingCartBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer belongUserId;
	private Integer commEntityId;
	private Integer amount;
	private Long entryTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBelongUserId() {
		return belongUserId;
	}
	public void setBelongUserId(Integer belongUserId) {
		this.belongUserId = belongUserId;
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
	public Long getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Long entryTime) {
		this.entryTime = entryTime;
	}
	
}
