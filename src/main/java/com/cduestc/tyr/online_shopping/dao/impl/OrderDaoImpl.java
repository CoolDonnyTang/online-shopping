package com.cduestc.tyr.online_shopping.dao.impl;

import javax.annotation.Resource;

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

}
