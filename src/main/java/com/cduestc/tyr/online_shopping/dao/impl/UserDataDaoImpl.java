package com.cduestc.tyr.online_shopping.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.ShoppingCartBean;
import com.cduestc.tyr.online_shopping.dao.IUserDataDao;

@Repository
public class UserDataDaoImpl implements IUserDataDao {
	
	@Resource
	private SessionFactory sf;
	
	@Override
	public ShoppingCartBean getShoppingCartByUserIdAndCommEnId(int userId, int CommEnId) {
		String hql = "from ShoppingCartBean where belongUserId = ? and commEntityId = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, userId);
		query.setInteger(1, CommEnId);
		return (ShoppingCartBean) query.uniqueResult();
	}
	@Override
	public void saveOrUpdateShoppingCartEntity(ShoppingCartBean entity) {
		Session session = sf.getCurrentSession();
		session.saveOrUpdate(entity);
	}
	@Override
	public List<Map<String, String>> findShoppingCartEnByUserId(int userId) {
		String hql = "select new map("
				+ "c.brand as commBrand, "
				+ "c.titleName as commTitle, "
				+ "ce.id as commEntityId, "
				+ "ce.myPrice as price, "
				+ "ce.propty1 as prop1, "
				+ "ce.propty2 as prop2, "
				+ "img.url as mainUrl, "
				+ "(select amount from ShoppingCartBean s "
					+ "where s.belongUserId = :userId "
					+ "and s.commEntityId = ce.id"
				+ ") as amount "
		+ ") from CommodityBean as c "
		+ "join c.commEntity as ce "
		+ "join ce.images as img "
		+ "where ce.id in ("
			+ "select commEntityId "
				+ "from ShoppingCartBean "
				+ "where belongUserId = :userId "
			+ ")"
		+ "and img.mainImage = true and img.serialNumber = 1 "
		+ "order by c.id";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		return query.list();
	}

}
