package com.cduestc.tyr.online_shopping.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cduestc.tyr.online_shopping.beans.UserBean;

public interface IUserService {
	/**
	 * 
	 * @param userName 用户名：可能是邮箱或昵称
	 * @param password 
	 * @param checkCode 验证码
	 * @param session 
	 * @return 
	 * 		1:登录成功
	 * 		0:用户不存在
	 * 		-1：用户名或密码错误（实际为密码错误）
	 * 		-2：验证码错误
	 * @throws Exception
	 */
	public int findUserToLogin(String userName, String password, String checkCode, HttpSession session) throws Exception;
	/**
	 * 
	 * @author tangyanrentyr
	 * @2017年2月12日 2017年2月12日
	 * @param email
	 * @param session
	 * @return
	 * 		1:成功
	 * 		0:您已注册过，请直接登录
	 * 		other：失败
	 */
	public int findUserOrsendEmailCode(String email, Boolean exist, HttpSession session) throws Exception;
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
	/**
	 * 用于获取五位验证码图片
	 * @throws Exception
	 */
	public void checkImage(HttpServletResponse response, HttpSession session) throws Exception;
	/**
	 * 更新用户信息
	 * @param user 传入对象
	 * @return 	1:成功
	 *  		0：登录失效	
	 *  		-1:两次密码相同
	 * @throws Exception
	 */
	public int updateUser(UserBean user, HttpSession session) throws Exception;
	
}
