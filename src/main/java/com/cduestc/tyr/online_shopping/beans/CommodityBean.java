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
	private Double activPrice;
	private BigDecimal myPrice;
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
	public Double getHistoryPrice() {
		return activPrice;
	}
	public void setHistoryPrice(Double historyPrice) {
		this.activPrice = historyPrice;
	}
	public BigDecimal getMyPrice() {
		return myPrice;
	}
	public void setMyPrice(BigDecimal myPrice) {
		this.myPrice = myPrice;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((belongKindId == null) ? 0 : belongKindId.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((entryId == null) ? 0 : entryId.hashCode());
		result = prime * result
				+ ((entryTime == null) ? 0 : entryTime.hashCode());
		result = prime * result
				+ ((activPrice == null) ? 0 : activPrice.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result
				+ ((lastChangeTime == null) ? 0 : lastChangeTime.hashCode());
		result = prime * result + ((myPrice == null) ? 0 : myPrice.hashCode());
		result = prime * result
				+ ((titleName == null) ? 0 : titleName.hashCode());
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
		CommodityBean other = (CommodityBean) obj;
		if (belongKindId == null) {
			if (other.belongKindId != null)
				return false;
		} else if (!belongKindId.equals(other.belongKindId))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
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
		if (activPrice == null) {
			if (other.activPrice != null)
				return false;
		} else if (!activPrice.equals(other.activPrice))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		if (lastChangeTime == null) {
			if (other.lastChangeTime != null)
				return false;
		} else if (!lastChangeTime.equals(other.lastChangeTime))
			return false;
		if (myPrice == null) {
			if (other.myPrice != null)
				return false;
		} else if (!myPrice.equals(other.myPrice))
			return false;
		if (titleName == null) {
			if (other.titleName != null)
				return false;
		} else if (!titleName.equals(other.titleName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CommodityBean [id=" + id + ", brand=" + brand + ", titleName="
				+ titleName + ", belongKindId=" + belongKindId + ", inventory="
				+ inventory + ", historyPrice=" + activPrice + ", myPrice="
				+ myPrice + ", entryId=" + entryId + ", entryTime=" + entryTime
				+ ", lastChangeTime=" + lastChangeTime + ", images=" + images
				+ ", params=" + params + ", properties=" + properties + "]";
	}
	
}
