package com.cduestc.tyr.online_shopping.manager.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.CommEntityBean;
import com.cduestc.tyr.online_shopping.beans.CommodityBean;
import com.cduestc.tyr.online_shopping.beans.IndexAdImageBean;
import com.cduestc.tyr.online_shopping.beans.KindBean;
import com.cduestc.tyr.online_shopping.beans.RecommendBrandBean;
import com.cduestc.tyr.online_shopping.beans.RecommendCommEntityBean;
import com.cduestc.tyr.online_shopping.manager.dao.ManageDao;
import com.cduestc.tyr.online_shopping.utils.RecommendKind;

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

	@Override
	public List<Map> findBrandTitlePropBySubKindId(int subKindId) {
		String hql = "select new map(c.id as id, c.brand as brand, c.titleName as title, p.id as propId, p.propertyName as popName, p.propertyCotent as porpContent) "
				+ " from CommodityBean as c join c.properties as p"
				+ " where c.belongKindId = ? order by p.id";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setInteger(0, subKindId);
		return query.list();
	}

	@Override
	public void addCommEntity(CommEntityBean comm) {
		Session session = sf.getCurrentSession();
		session.save(comm);
	}

	@Override
	public void saveRecommendEntity(RecommendCommEntityBean recommend) {
		Session session = sf.getCurrentSession();
		session.save(recommend);
	}

	@Override
	public void addRecommendBrand(RecommendBrandBean recommendBrad) {
		Session session = sf.getCurrentSession();
		session.save(recommendBrad);
	}

	@Override
	public List<String> queryAllBrandNameFromCommModel() {
		String hql = "SELECT distinct brand from CommodityBean";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteRecommendEntitiesByRecommendType(RecommendKind recommendType) {
		String hql = "from RecommendCommEntityBean where recommendType = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, recommendType.toString());
		List<RecommendCommEntityBean> list =  query.list();
		for(RecommendCommEntityBean r : list) {
			session.delete(r);
		}
	}

	@Override
	public void addAdImage(IndexAdImageBean adImage) {
		Session session = sf.getCurrentSession();
		session.save(adImage);
	}

	@Override
	public void deleteAllAdImage() {
		String hql = "from IndexAdImageBean";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		List<IndexAdImageBean> list = query.list();
		for(IndexAdImageBean i : list) {
			session.delete(i);
		}
	}
	
}
