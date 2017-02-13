package com.cduestc.tyr.online_shopping.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class UserBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nickname;
	private String password;
	private String email;
	private String idNumber;
	private Short level;
	private Long entryTime;
	private Long lastChangeTime;
	
	public UserBean() {
		this.level = 1;
		this.entryTime = System.currentTimeMillis();
		this.lastChangeTime = System.currentTimeMillis();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}
	public Long getEntryTime() {
		return entryTime;
	}
	public Long getLastChangeTime() {
		return lastChangeTime;
	}
	public void setLastChangeTime(Long lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", nickname=" + nickname + ", password="
				+ password + ", email=" + email + ", idNumber=" + idNumber
				+ ", level=" + level + ", entryTime=" + entryTime
				+ ", lastChangeTime=" + lastChangeTime + "]";
	}
	
}
