package com.cduestc.tyr.online_shopping.service;

import javax.servlet.http.HttpSession;

import com.cduestc.tyr.online_shopping.beans.UserBean;

public interface IUserService {
	public UserBean findUser(UserBean user) throws Exception;
	/**
	 * 
	 * @author tangyanrentyr
	 * @2017年2月12日 2017年2月12日
	 * @param email
	 * @param session
	 * @return
	 * 		1:成功
	 * 		other：失败
	 */
	public int sendEmailCode(String email, HttpSession session) throws Exception;
	/**
	 * 
	 * @author tangyanrentyr
	 * @2017年2月12日 2017年2月12日
	 * 
	 * @param email 当前输入框的email
	 * @param checkCode 验证码
	 * @param session
	 * @return 
	 * 		0:session失效、email改变 、验证码失效
	 * 		1：正确
	 * 		-1：验证码出错
	 */
	public int checkEmailCode(String email, String checkCode, HttpSession session) throws Exception;
	/**
	 * @param user
	 * @param session 用于取出验证通过的邮箱
	 * @return
	 * 		1:添加用户成功
	 * 		0:用户已存在
	 * 		-1:邮箱验证失效
	 * @throws Exception 其它原因导致失败
	 */
	public int addUser(UserBean user,HttpSession session) throws Exception;
	public Boolean updateUser(UserBean user) throws Exception;
}
