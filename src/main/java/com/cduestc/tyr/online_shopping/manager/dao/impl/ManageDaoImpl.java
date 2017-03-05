package com.cduestc.tyr.online_shopping.manager.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.KindBean;
import com.cduestc.tyr.online_shopping.manager.dao.ManageDao;

@Repository
public class ManageDaoImpl implements ManageDao {
	
	@Resource
	private SessionFactory sf;
	
	@Override
	public void addMainKind(KindBean kind) {
		Session session = sf.getCurrentSession();
		session.save(kind);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KindBean> findMainKind() {
		String hql = "from KindBean where belong is null";
		Query query = sf.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
}
