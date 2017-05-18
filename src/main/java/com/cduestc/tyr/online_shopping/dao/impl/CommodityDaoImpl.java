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
import com.cduestc.tyr.online_shopping.utils.RecommendKind;

@Repository
public class CommodityDaoImpl implements ICommodityDao {

	@Resource
	private SessionFactory sf;
	
	@Override
	public CommodityBean findCommModelAndEntityById(int id) {
		Session session = sf.getCurrentSession();
		return (CommodityBean) session.get(CommodityBean.class, id);
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

	@Override
	public List<Map> findSimpleCommByRecommendType(RecommendKind recommendType) {
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
		+ "where ce.id in (select commEntityId from RecommendCommEntityBean where recommendType = ?) "
				+ "and img.mainImage = true and img.serialNumber = 1 "
		+ "order by c.id";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		System.out.println(recommendType.toString());
		query.setString(0, recommendType.toString());
		query.setFirstResult(0);
		query.setMaxResults(6);
		return query.list();
	}

	@Override
	public List<Map> findSimpleCommByRecommendBrandId(int RecommendBrandId, int firstResult, int pageSize) {
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
		+ "where c.brand = (select brandName from RecommendBrandBean where id = ?) "
			+ "and img.mainImage = true and img.serialNumber = 1 "
		+ "order by ce.entryTime desc";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, RecommendBrandId);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public List<Map> findSimpleComm4SalesTop2() {
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
		+ "where img.mainImage = true and img.serialNumber = 1 "
		+ "order by ce.sales desc";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(2);
		return query.list();
	}

	@Override
	public List<Map> findSimpleCommBySearchKey(String[] key, int firstResult, int pageSize) {
		//搜索条件
		String condition = "";
		for(int i=0; i<key.length; i++) {
			condition += " and ce.searchKey like ? ";
		}
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
		+ "where img.mainImage = true and img.serialNumber = 1 " + condition;
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		//遍历加入收索条件
		for(int i=0; i<key.length; i++) {
			query.setString(i, "%"+key[i]+"%");
		}
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
}
