package com.cduestc.tyr.online_shopping.beans;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cduestc.tyr.online_shopping.utils.RecommendKind;

@Entity
@Table(name="revommend_comm_entity")
public class RecommendCommEntityBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer commEntityId;
	@Enumerated(EnumType.STRING)
	private RecommendKind recomendType;
	private Long entryTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCommEntityId() {
		return commEntityId;
	}
	public void setCommEntityId(Integer commEntityId) {
		this.commEntityId = commEntityId;
	}
	public RecommendKind getRecomendType() {
		return recomendType;
	}
	public void setRecomendType(RecommendKind recomendType) {
		this.recomendType = recomendType;
	}
	public Long getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Long entryTime) {
		this.entryTime = entryTime;
	}
}
