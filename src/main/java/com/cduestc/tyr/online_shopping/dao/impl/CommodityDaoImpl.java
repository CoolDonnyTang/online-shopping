package com.cduestc.tyr.online_shopping.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.CommodityBean;
import com.cduestc.tyr.online_shopping.dao.ICommodityDao;

@Repository
public class CommodityDaoImpl implements ICommodityDao {

	@Resource
	private SessionFactory sf;
	
	@Override
	public CommodityBean findCommById(int id) {
		Session session = sf.getCurrentSession();
		return (CommodityBean) session.load(CommodityBean.class, id);
	}
	
	@Override
	public List<Map> findSimpleCommByMainKindId(int mainKindId, int firstResult, int pageSize) {
//		String hql = "from CommodityBean c "
//				+ "where c.belongKindId in (select k.id from KindBean k where k.belong = ?)"
//				+ ")";
		String hql = "select new map("
				+ "c.id as commId, "
				+ "c.brand as commBrand, "
				+ "c.titleName as commTitle, "
				+ "ce.id as commEntityId, "
				+ "ce.myPrice as price, "
				+ "ce.marketPrice as marketPrice, "
				+ "ce.propty1 as prop1, "
				+ "ce.propty2 as prop2, "
				+ "img.url as mainUrl"
		+ ") from CommodityBean as c "
		+ "join c.commEntity as ce "
		+ "join ce.images as img "
		+ "where c.belongKindId in (select k.id from KindBean k where k.belong = ?) "
		+ "and img.mainImage = true and img.serialNumber = 1 "
		+ "order by c.id";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, mainKindId);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Map> findSimpleCommBySubKindId(int subKindId, int firstResult, int pageSize) {
//		String hql = "from CommodityBean c where c.belongKindId = ?";
		String hql = "select new map("
				+ "c.id as commId, "
				+ "c.brand as commBrand, "
				+ "c.titleName as commTitle, "
				+ "ce.id as commEntityId, "
				+ "ce.myPrice as price, "
				+ "ce.marketPrice as marketPrice, "
				+ "ce.propty1 as prop1, "
				+ "ce.propty2 as prop2, "
				+ "img.url as mainUrl"
		+ ") from CommodityBean as c "
		+ "join c.commEntity as ce "
		+ "join ce.images as img "
		+ "where c.belongKindId = ? and img.mainImage = true and img.serialNumber = 1 "
		+ "order by c.id";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, subKindId);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}

}
