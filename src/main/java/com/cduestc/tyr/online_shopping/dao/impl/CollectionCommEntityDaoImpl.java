package com.cduestc.tyr.online_shopping.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.CollectionCommEntityBean;
import com.cduestc.tyr.online_shopping.dao.ICollectionCommEntityDao;

@Repository
public class CollectionCommEntityDaoImpl implements ICollectionCommEntityDao {
	
	@Resource
	private SessionFactory sf;
	
	@Override
	public Long queryCommEnIsCollect(int commEntityId, int userId) {
		String hql = "select count(*) from CollectionCommEntityBean "
				+ "where conllectionCommEntityId = ? and belongUserId = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, commEntityId);
		query.setInteger(1, userId);
		return (Long) query.uniqueResult();
	}

	@Override
	public boolean addCollectionCommEn(int commEntityId, int userId) {
		CollectionCommEntityBean cce = new CollectionCommEntityBean();
		cce.setConllectionCommEntityId(commEntityId);
		cce.setBelongUserId(userId);
		cce.setEntryTime(System.currentTimeMillis());
		Session session = sf.getCurrentSession();
		try {
			session.save(cce);
		} catch(ConstraintViolationException e) {
			return false;
		}
		return true;
	}

	@Override
	public void addCollectionCommEn(Integer[] commEntitiesId, int userId) {
		CollectionCommEntityBean cce = null;
		String hql = "from CollectionCommEntityBean "
				+ "where conllectionCommEntityId = ? and belongUserId = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		for(Integer i : commEntitiesId) {
			if(i!=null) {
				query.setInteger(0, i);
				query.setInteger(1, userId);
				if(query.uniqueResult()!=null) {
					continue;
				}
				cce = new CollectionCommEntityBean();
				cce.setConllectionCommEntityId(i);
				cce.setBelongUserId(userId);
				cce.setEntryTime(System.currentTimeMillis());
				session.save(cce);
			}
		}
	}

	@Override
	public boolean removeCollectionCommEn(int commEntityId, int userId) {
		String hql = "from CollectionCommEntityBean "
				+ "where conllectionCommEntityId = ? and belongUserId = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, commEntityId);
		query.setInteger(1, userId);
		CollectionCommEntityBean cce = (CollectionCommEntityBean) query.uniqueResult();
		if(cce==null) {
			return false;
		}
		session.delete(cce);
		return true;
	}

	@Override
	public boolean removeCollectionCommEn(int[] commEntitiesId, int userId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Map> findSimpleCommByCollection(int userId) {
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
		+ "where ce.id in (select conllectionCommEntityId from CollectionCommEntityBean where belongUserId = ?) "
				+ "and img.mainImage = true and img.serialNumber = 1 "
		+ "order by c.id";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, userId);
		//query.setFirstResult(0);
		//query.setMaxResults(6);
		return query.list();
	}
	
}
