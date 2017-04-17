package com.cduestc.tyr.online_shopping.dao;

import java.util.List;

import com.cduestc.tyr.online_shopping.beans.ShippingAddressBean;
import com.cduestc.tyr.online_shopping.beans.UserBean;

public interface IUserDao {
	public UserBean getUserByNickname(String nickname) throws Exception;
	public UserBean getUserByUserName(String userName) throws Exception;
	public UserBean getUserByEmail(String email) throws Exception;
	public void addUser(UserBean user) throws Exception;
	/**
	 * 
	 * @author tangyanrentyr
	 * @2017年2月19日 2017年2月19日
	 * @param user
	 * @return
	 * 		1:修改成功
	 * 		-1：两次密码相同
	 * @throws Exception
	 */
	public int updateUser(UserBean user)throws Exception;
	public List<ShippingAddressBean> findShippingAddressByUserId(int userId);
	public void addShippingAddress(ShippingAddressBean addr);
}
