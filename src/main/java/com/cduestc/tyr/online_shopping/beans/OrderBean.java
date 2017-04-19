package com.cduestc.tyr.online_shopping.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cduestc.tyr.online_shopping.utils.OrderPayment;
import com.cduestc.tyr.online_shopping.utils.OrderStatus;

@Entity
@Table(name="order_table")
public class OrderBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer belongUserId;
	private Integer addressId;
	private Double orderPrice;
	private Long entrtime;
	@Enumerated(EnumType.STRING)
	private OrderPayment payment;
	private Boolean payStatus;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "belongOrderId")
	private Set<OrderDetailBean> orderDetail;
	
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
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Long getEntrtime() {
		return entrtime;
	}
	public void setEntrtime(Long entrtime) {
		this.entrtime = entrtime;
	}
	public Set<OrderDetailBean> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(Set<OrderDetailBean> orderDetail) {
		this.orderDetail = orderDetail;
	}
	public OrderPayment getPayment() {
		return payment;
	}
	public void setPayment(OrderPayment payment) {
		this.payment = payment;
	}
	public Boolean getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Boolean payStatus) {
		this.payStatus = payStatus;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
