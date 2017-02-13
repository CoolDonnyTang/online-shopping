package com.cduestc.tyr.online_shopping.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.dao.IUserDao;

@Repository
public class UserDao implements IUserDao {
	@Resource
	private SessionFactory sf;

	@Override
	public UserBean getUserByNickname(String nickname) throws Exception {
		String hql = "from UserBean where nickname = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, nickname);
		return (UserBean) query.uniqueResult();
	}

	@Override
	public boolean addUser(UserBean user) throws Exception {
		Session session = sf.getCurrentSession();
		session.save(user);
		return false;
	}
	
}
