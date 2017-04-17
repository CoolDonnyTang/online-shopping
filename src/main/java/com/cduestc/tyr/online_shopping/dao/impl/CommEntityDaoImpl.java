package com.cduestc.tyr.online_shopping.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.dao.ICommEntityDao;

@Repository
public class CommEntityDaoImpl implements ICommEntityDao {
	
	@Resource
	private SessionFactory sf;
	
	@Override
	public List<Map<String, String>> findCommEntitiesById(Integer[] ids) {
		String hql = "select new map("
				+ "c.brand as commBrand, "
				+ "c.titleName as commTitle, "
				+ "ce.id as commEntityId, "
				+ "ce.myPrice as price, "
				+ "ce.inventory as inventory, "
				+ "ce.propty1 as prop1, "
				+ "ce.propty2 as prop2, "
				+ "img.url as mainUrl"
		+ ") from CommodityBean as c "
		+ "join c.commEntity as ce "
		+ "join ce.images as img "
		+ "where ce.id in (" + StringUtils.join(ids, ",") + ") "
		+ "and img.mainImage = true and img.serialNumber = 1 ";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}

}
