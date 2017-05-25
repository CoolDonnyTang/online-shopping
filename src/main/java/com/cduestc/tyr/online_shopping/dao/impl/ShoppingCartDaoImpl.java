package com.cduestc.tyr.online_shopping.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.ShoppingCartBean;
import com.cduestc.tyr.online_shopping.dao.IShoppingCartDao;

@Repository
public class ShoppingCartDaoImpl implements IShoppingCartDao {
	
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
				+ "c.id as commId, "
				+ "c.brand as commBrand, "
				+ "c.titleName as commTitle, "
				+ "(select ss.id from ShoppingCartBean ss "
					+ "where ss.belongUserId = :userId "
					+ "and ss.commEntityId = ce.id"
				+ ") as entityId, "
				+ "ce.id as commentityId, "
				+ "ce.inventory as inventory, "
				+ "ce.myPrice as price, "
				+ "ce.propty1 as prop1, "
				+ "ce.propty2 as prop2, "
				+ "img.url as mainUrl, "
				+ "(select s.amount from ShoppingCartBean s "
					+ "where s.belongUserId = :userId "
					+ "and s.commEntityId = ce.id"
				+ ") as amount,"
				+ "(select s.entryTime from ShoppingCartBean s "
					+ "where s.belongUserId = :userId "
					+ "and s.commEntityId = ce.id"
				+ ") as entryTime"
		+ ") from CommodityBean as c "
		+ "join c.commEntity as ce "
		+ "join ce.images as img "
		+ "where ce.id in ("
			+ "select commEntityId "
				+ "from ShoppingCartBean "
				+ "where belongUserId = :userId "
			+ ")"
		+ "and img.mainImage = true and img.serialNumber = 1 "
		+ "order by entryTime desc";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger("userId", userId);
		return query.list();
	}
	@Override
	public void updateAmountById(int amount, int entityId) {
		Session session = sf.getCurrentSession();
		ShoppingCartBean sc = (ShoppingCartBean) session.get(ShoppingCartBean.class, entityId);
		if(null != sc) {
			sc.setAmount(amount);
		}
		session.save(sc);
	}
	@Override
	public void deleteEntityById(int entityId) {
		Session session = sf.getCurrentSession();
		ShoppingCartBean sc = (ShoppingCartBean) session.get(ShoppingCartBean.class, entityId);
		if(null != sc) {
			session.delete(sc);
		}
	}
	@Override
	public void deleteEntities(Integer[] ids) {
		String hql = "from ShoppingCartBean where id in (" + StringUtils.join(ids,",") + ")";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		List<ShoppingCartBean> list = query.list();
		for(ShoppingCartBean sc : list) {
			session.delete(sc);
		}
	}
	@Override
	public void deleteEntitiesByCommEnIds(Set<Integer> commEnIds) {
		String hql = "from ShoppingCartBean where commEntityId in (" + StringUtils.join(commEnIds,",") + ")";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		List<ShoppingCartBean> list = query.list();
		for(ShoppingCartBean sc : list) {
			session.delete(sc);
		}
	}

}
