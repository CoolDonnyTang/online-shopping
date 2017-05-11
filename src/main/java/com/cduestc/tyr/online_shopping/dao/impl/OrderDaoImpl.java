package com.cduestc.tyr.online_shopping.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.OrderBean;
import com.cduestc.tyr.online_shopping.dao.IOrderDao;
import com.cduestc.tyr.online_shopping.utils.OrderPayment;
import com.cduestc.tyr.online_shopping.utils.OrderStatus;

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

	@Override
	public List<Map<String, Object>> queryBaseOrderMessageByStatus(int userId, Boolean max, OrderStatus status) {
		Session session = sf.getCurrentSession();
		Query query = null;
		if(max == true) {
			String hql = "select new map("
					+ "o.id as id, "
					+ "o.orderPrice as orderPrice, "
					+ "o.payment as payment, "
					+ "o.orderStatus as orderStatus, "
					+ "(select recipient from ShippingAddressBean "
						+ "where id = o.addressId"
					+ ") as name"
					+ ") from OrderBean as o "
					+ "where  o.belongUserId = ? and ? - o.entrtime <= ? and o.orderStatus = ? "
					+ "order by o.entrtime desc";
			query = session.createQuery(hql);
			query.setInteger(0, userId);
			query.setDouble(1, System.currentTimeMillis());
			query.setDouble(2, 365*24*60*60*1000);
			query.setString(3, status.toString());
		} else {
			String hql = "select new map("
					+ "o.id as id, "
					+ "o.entrtime as time, "
					+ "o.orderPrice as orderPrice, "
					+ "o.payment as payment, "
					+ "o.orderStatus as orderStatus, "
					+ "(select recipient from ShippingAddressBean "
						+ "where id = o.addressId"
					+ ") as name"
					+ ") from OrderBean as o "
					+ "where  o.belongUserId = ? and o.orderStatus = ? "
					+ "order by o.entrtime desc";
			query = session.createQuery(hql);
			query.setInteger(0, userId);
			query.setString(1, status.toString());
			query.setFirstResult(0);
			query.setMaxResults(2);
		}
		return query.list();
	}
}
