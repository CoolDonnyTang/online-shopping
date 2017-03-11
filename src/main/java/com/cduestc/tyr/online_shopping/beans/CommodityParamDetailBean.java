package com.cduestc.tyr.online_shopping.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "commodity_param_detail")
public class CommodityParamDetailBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer belongCommodityId;
	private String paramContent;
	private Integer entryId;
	private Long entryTime;
	private Long lastChangeTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParamContent() {
		return paramContent;
	}
	public void setParamContent(String paramContent) {
		this.paramContent = paramContent;
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
	public Integer getBelongCommodityId() {
		return belongCommodityId;
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
				+ ((paramContent == null) ? 0 : paramContent.hashCode());
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
		CommodityParamDetailBean other = (CommodityParamDetailBean) obj;
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
		if (paramContent == null) {
			if (other.paramContent != null)
				return false;
		} else if (!paramContent.equals(other.paramContent))
			return false;
		return true;
	}
	
}	
