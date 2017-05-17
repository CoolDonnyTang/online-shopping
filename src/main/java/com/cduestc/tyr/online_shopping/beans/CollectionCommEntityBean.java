package com.cduestc.tyr.online_shopping.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="collection_comm_entity")
public class CollectionCommEntityBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer belongUserId;
	private Integer conllectionCommEntityId;
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
	public Integer getConllectionCommEntityId() {
		return conllectionCommEntityId;
	}
	public void setConllectionCommEntityId(Integer conllectionCommEntityId) {
		this.conllectionCommEntityId = conllectionCommEntityId;
	}
	public Long getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Long entryTime) {
		this.entryTime = entryTime;
	}
	
}
