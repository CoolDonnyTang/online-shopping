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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((belongCommodityId == null) ? 0 : belongCommodityId
						.hashCode());
		result = prime * result + ((entryId == null) ? 0 : entryId.hashCode());
		result = prime * result
				+ ((entryTime == null) ? 0 : entryTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastChangeTime == null) ? 0 : lastChangeTime.hashCode());
		result = prime * result
				+ ((mainImage == null) ? 0 : mainImage.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommodityImageBean other = (CommodityImageBean) obj;
		if (belongCommodityId == null) {
			if (other.belongCommodityId != null)
				return false;
		} else if (!belongCommodityId.equals(other.belongCommodityId))
			return false;
		if (entryId == null) {
			if (other.entryId != null)
				return false;
		} else if (!entryId.equals(other.entryId))
			return false;
		if (entryTime == null) {
			if (other.entryTime != null)
				return false;
		} else if (!entryTime.equals(other.entryTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastChangeTime == null) {
			if (other.lastChangeTime != null)
				return false;
		} else if (!lastChangeTime.equals(other.lastChangeTime))
			return false;
		if (mainImage == null) {
			if (other.mainImage != null)
				return false;
		} else if (!mainImage.equals(other.mainImage))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
}
