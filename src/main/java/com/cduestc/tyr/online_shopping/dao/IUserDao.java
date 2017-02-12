package com.cduestc.tyr.online_shopping.dao;

import com.cduestc.tyr.online_shopping.beans.UserBean;

public interface IUserDao {
	public UserBean getUserByNickname(String nickname);
	public boolean addUser(UserBean user);
}
