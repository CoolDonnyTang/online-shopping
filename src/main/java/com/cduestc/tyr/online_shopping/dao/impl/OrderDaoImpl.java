package com.cduestc.tyr.online_shopping.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.OrderBean;
import com.cduestc.tyr.online_shopping.dao.IOrderDao;

@Repository
public class OrderDaoImpl implements IOrderDao {

	@Resource
	private SessionFactory sf;
	
	@Override
	public void addOrder(OrderBean order) {
		Session session = sf.getCurrentSession();
		session.save(order);
	}

	@Override
	public Map<String, String> queryOrderBaseInfoById(int orderId, int userId) {
		String hql = "select new map("
				+ "o.orderPrice as orderPrice, "
				+ "o.payment as payment, "
				+ "o.orderStatus as orderStatus, "
				+ "(select fullAddress from ShippingAddressBean "
					+ "where id = o.addressId"
				+ ") as address"
				+ ") from OrderBean as o "
				+ "where id = ? and belongUserId = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, orderId);
		query.setInteger(1, userId);
		return (Map<String, String>) query.uniqueResult();
	}

}
