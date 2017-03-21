package com.cduestc.tyr.online_shopping.beans;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="commodity_entity")
public class CommEntityBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer belongCommId;
	private Integer inventory;
	private Double marketPrice;
	private BigDecimal myPrice;
	private Integer sales;
	private Integer entryId;
	private Long entryTime;
	private Long lastChangeTime;
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "belongCommEntityId")
	private Set<CommodityImageBean> images; //主图片
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "belongCommEntityId")
	private Set<CommodityParamDetailBean> params; //商品参数
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBelongCommId() {
		return belongCommId;
	}
	public void setBelongCommId(Integer belongCommId) {
		this.belongCommId = belongCommId;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public BigDecimal getMyPrice() {
		return myPrice;
	}
	public void setMyPrice(BigDecimal myPrice) {
		this.myPrice = myPrice;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	public Integer getEntryId() {
		return entryId;
	}
	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}
	public Long getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Long entryTime) {
		this.entryTime = entryTime;
	}
	public Long getLastChangeTime() {
		return lastChangeTime;
	}
	public void setLastChangeTime(Long lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}
	public Set<CommodityParamDetailBean> getParams() {
		return params;
	}
	public void setParams(Set<CommodityParamDetailBean> params) {
		this.params = params;
	}
	
}
