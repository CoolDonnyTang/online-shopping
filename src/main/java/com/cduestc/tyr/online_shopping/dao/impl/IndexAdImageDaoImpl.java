package com.cduestc.tyr.online_shopping.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.IndexAdImageBean;
import com.cduestc.tyr.online_shopping.dao.IndexAdImageDao;

@Repository
public class IndexAdImageDaoImpl implements IndexAdImageDao {
	
	@Resource
	private SessionFactory sf;
	
	@Override
	public List<IndexAdImageBean> findIndexAdImage() {
		String hql = "from IndexAdImageBean";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}

}
