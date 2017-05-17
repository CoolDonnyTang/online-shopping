package com.cduestc.tyr.online_shopping.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		session.save(cce);
		return true;
	}

	@Override
	public boolean addCollectionCommEn(int[] commEntityId, int userId) {
		// TODO Auto-generated method stub
		return true;
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

}
