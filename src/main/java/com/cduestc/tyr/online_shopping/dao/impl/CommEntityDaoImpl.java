package com.cduestc.tyr.online_shopping.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.CommEntityBean;
import com.cduestc.tyr.online_shopping.dao.ICommEntityDao;

@Repository
public class CommEntityDaoImpl implements ICommEntityDao {
	
	@Resource
	private SessionFactory sf;
	
	@Override
	public List<Map<String, String>> findCommEntitiesById(Integer[] ids) {
		String hql = "select new map("
				+ "c.id as commId, "
				+ "c.brand as commBrand, "
				+ "c.titleName as commTitle, "
				+ "ce.id as commEntityId, "
				+ "ce.myPrice as price, "
				+ "ce.inventory as inventory, "
				+ "ce.propty1 as prop1, "
				+ "ce.propty2 as prop2, "
				+ "img.url as mainUrl"
		+ ") from CommodityBean as c "
		+ "join c.commEntity as ce "
		+ "join ce.images as img "
		+ "where ce.id in (" + StringUtils.join(ids, ",") + ") "
		+ "and img.mainImage = true and img.serialNumber = 1 ";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public Double queryPriceByEnId(int commEntityId) {
		String hql = "select myPrice from CommEntityBean "
				+ "where id = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, commEntityId);
		return Double.valueOf(((BigDecimal)query.uniqueResult()).toPlainString());
	}

	@Override
	public int queryInventoryByEnId(int commEntityId) {
		String hql = "select inventory from CommEntityBean "
				+ "where id = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, commEntityId);
		return (int) query.uniqueResult();
	}

	@Override
	public void updateInventoryByEnId(int commEntityId, int sales) {
		Session session = sf.getCurrentSession();
		CommEntityBean entity = (CommEntityBean) session.get(CommEntityBean.class, commEntityId);
		entity.setInventory(entity.getInventory() - sales);
		entity.setSales(entity.getSales() + sales);
		session.saveOrUpdate(entity);
	}

	@Override
	public Map<String, String> findCommEntityById(int id) {
		String hql = "select new map("
				+ "c.id as commId, "
				+ "c.brand as commBrand, "
				+ "c.titleName as commTitle, "
				+ "ce.id as commEntityId, "
				+ "ce.myPrice as price, "
				+ "ce.inventory as inventory, "
				+ "ce.propty1 as prop1, "
				+ "ce.propty2 as prop2, "
				+ "img.url as mainUrl"
		+ ") from CommodityBean as c "
		+ "join c.commEntity as ce "
		+ "join ce.images as img "
		+ "where ce.id = ? "
		+ "and img.mainImage = true and img.serialNumber = 1 ";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		return (Map<String, String>) query.uniqueResult();
	}

	@Override
	public List<Map<String, Object>> findCommEnFirstMainImageByOrderId(
			Integer orderId) {		
		String hql = "select new map("
				+ "c.id as commId, "
				+ "c.brand as commBrand, "
				+ "c.titleName as commTitle, "
				+ "ce.id as commEntityId, "
				+ "ce.myPrice as price, "
				+ "ce.inventory as inventory, "
				+ "ce.propty1 as prop1, "
				+ "ce.propty2 as prop2, "
				+ "img.url as mainUrl"
		+ ") from CommodityBean as c "
		+ "join c.commEntity as ce "
		+ "join ce.images as img "
		+ "where ce.id in(select od.commEntityId "
					+ "from OrderDetailBean as od "
					+ "where od.belongOrderId = ?) "
		+ "and img.mainImage = true and img.serialNumber = 1 ";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, orderId);
		return query.list();
	}

}
