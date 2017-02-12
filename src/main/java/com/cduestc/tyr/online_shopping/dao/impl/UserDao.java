package com.cduestc.tyr.online_shopping.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.dao.IUserDao;

public class UserDao implements IUserDao {
	
	private SessionFactory sf;
	
	@Override
	public UserBean getUserByNickname(String nickname) {
		return null;
	}

	@Override
	public boolean addUser(UserBean user) {
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		sf = (SessionFactory) ac.getBean("sessionFactory");
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();
		session.save(user);
		tran.commit();
		return true;
	}
	
}
