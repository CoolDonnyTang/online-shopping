package com.cduestc.tyr.online_shopping.manager.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.CommodityBean;
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

	@Override
	public KindBean findMainKindById(int id) {
		Session session = sf.getCurrentSession();
		return (KindBean) session.load(KindBean.class, id);
	}

	@Override
	public void updateSubKind(KindBean kind) {
		Session session = sf.getCurrentSession();
		session.update(kind);
	}

	@Override
	public void deleteMainKindById(int id) {
		Session session = sf.getCurrentSession();
		KindBean kind = (KindBean) session.load(KindBean.class, id);
		session.delete(kind);
	}

	@Override
	public void addCommodity(CommodityBean comm) {
		Session session = sf.getCurrentSession();
		session.save(comm);
	}
	
}
