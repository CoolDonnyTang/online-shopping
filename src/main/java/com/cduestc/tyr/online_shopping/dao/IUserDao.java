package com.cduestc.tyr.online_shopping.dao;

import com.cduestc.tyr.online_shopping.beans.UserBean;

public interface IUserDao {
	public UserBean getUserByNickname(String nickname) throws Exception;
	public UserBean getUserByUserName(String userName) throws Exception;
	public UserBean getUserByEmail(String email) throws Exception;
	public boolean addUser(UserBean user) throws Exception;
}
