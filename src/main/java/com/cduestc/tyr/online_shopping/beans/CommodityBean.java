package com.cduestc.tyr.online_shopping.beans;

import java.io.Serializable;
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
@Table(name="commodity")
public class CommodityBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String brand;
	private String titleName;
	private Integer belongKindId;
	private Integer inventory;
	private Double marketPrice;
	private BigDecimal myPrice;
	private Integer sales;
	private Integer entryId;
	private Long entryTime;
	private Long lastChangeTime;
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "belongCommodityId")
	private Set<CommodityImageBean> images; //图片
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "belongCommodityId")
	private Set<CommodityParamDetailBean> params; //商品参数
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "belongCommodityId")
	private Set<CommodityPropertyBean> properties; //商品参数
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public Integer getBelongKindId() {
		return belongKindId;
	}
	public void setBelongKindId(Integer belongKindId) {
		this.belongKindId = belongKindId;
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
	public Set<CommodityImageBean> getImages() {
		return images;
	}
	public void setImages(Set<CommodityImageBean> images) {
		this.images = images;
	}
	public Set<CommodityParamDetailBean> getParams() {
		return params;
	}
	public void setParams(Set<CommodityParamDetailBean> params) {
		this.params = params;
	}
	public Set<CommodityPropertyBean> getProperties() {
		return properties;
	}
	public void setProperties(Set<CommodityPropertyBean> properties) {
		this.properties = properties;
	}
}
