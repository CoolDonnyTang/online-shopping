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
	public List<Map> findSimpleCommByMainKindId(int mainKindId) {
		String hql = "select new map(c.id as id, c.titleName as title, c.myPrice as price, c.marketPrice as marketPrice, c.sales as sales, i.url as url) "
				+ " from CommodityBean as c join c.images as i"
				+ " where c.belongKindId in (select k.id from KindBean k where k.belong = ?)"
				+ " and i.mainImage = true and i.serialNumber = 1";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, mainKindId);
		return query.list();
	}

	@Override
	public List<Map> findSimpleCommBySubKindId(int subKindId) {
		String hql = "select new map(c.id as id, c.titleName as title, c.myPrice as price, c.marketPrice as marketPrice, c.sales as sales, i.url as url) "
				+ " from CommodityBean as c join c.images as i"
				+ " where c.belongKindId = ? and i.mainImage = true and i.serialNumber = 1";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, subKindId);
		return query.list();
	}

}
