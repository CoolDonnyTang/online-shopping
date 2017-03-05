package com.cduestc.tyr.online_shopping.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="kind")
public class KindBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer belong;
	private Integer entryId;
	private Long entryTime;
	private Long lastChangeTime;
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "belong")
	private Set<KindBean> subKinds;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBelong() {
		return belong;
	}
	public void setBelong(Integer belong) {
		this.belong = belong;
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
	public Set<KindBean> getSubKinds() {
		return subKinds;
	}
	public void setSubKinds(Set<KindBean> subKinds) {
		this.subKinds = subKinds;
	}
}
