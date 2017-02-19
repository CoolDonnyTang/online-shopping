package com.cduestc.tyr.online_shopping.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	public void addUser(UserBean user) throws Exception {
		Session session = sf.getCurrentSession();
		session.save(user);
	}

	@Override
	public UserBean getUserByUserName(String userName) throws Exception {
		String hql = "from UserBean where nickname = ? or email = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, userName);
		query.setString(1, userName);
		return (UserBean) query.uniqueResult();
	}

	@Override
	public UserBean getUserByEmail(String email) throws Exception {
		String hql = "from UserBean where  email = ?";
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, email);
		return (UserBean) query.uniqueResult();
	}

	@Override
	public int updateUser(UserBean user) throws Exception {
		UserBean oldUser = this.getUserByEmail(user.getEmail());
		//是更改密码则需要判断两次密码是否相同
		if((null!=user.getPassword()) && (user.getPassword().equals(oldUser.getPassword()))) {
			return -1;
		}
		user.setId(oldUser.getId());
		Session session = sf.getCurrentSession();
		session.merge(user);
		return 1;
	}
	
}
