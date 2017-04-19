package com.cduestc.tyr.online_shopping.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.dao.IOrderDetailDao;

@Repository
public class OrderDetailDaoImpl implements IOrderDetailDao {

	@Resource
	private SessionFactory sf;
	
	@Override
	public List<Map<String, Object>> findOrderDetailMessageByOrderId(int orderId) {
		String hql = "select new map("
				+ "o.commEntityId as commEntityId,"
				+ "o.amount as amount,"
				+ "o.salePrice as salePrice"
				+ ") from OrderDetailBean as o "
				+ "where belongOrderId = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, orderId);
		return query.list();
	}

}
