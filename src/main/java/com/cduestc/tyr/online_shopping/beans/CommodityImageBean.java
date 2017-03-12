package com.cduestc.tyr.online_shopping.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="commodity_image")
public class CommodityImageBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer belongCommodityId;
	private Boolean mainImage;	//标识是否属于主图片
	private String url;
	private String realPath;
	private Integer serialNumber;
	private Integer entryId;
	private Long entryTime;
	private Long lastChangeTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBelongCommodityId() {
		return belongCommodityId;
	}
	public void setBelongCommodityId(Integer belongCommodityId) {
		this.belongCommodityId = belongCommodityId;
	}
	public Boolean getMainImage() {
		return mainImage;
	}
	public void setMainImage(Boolean mainImage) {
		this.mainImage = mainImage;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
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
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
}
