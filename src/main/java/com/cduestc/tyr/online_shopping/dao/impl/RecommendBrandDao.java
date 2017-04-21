package com.cduestc.tyr.online_shopping.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.RecommendBrandBean;
import com.cduestc.tyr.online_shopping.dao.IRecommendBrandDao;

@Repository
public class RecommendBrandDao implements IRecommendBrandDao {

	@Resource
	private SessionFactory sf;
	
	@Override
	public List<RecommendBrandBean> findRecommendBrand() {
		String hql = "from RecommendBrandBean order by entryTime desc";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(6);
		return query.list();
	}
	
}
