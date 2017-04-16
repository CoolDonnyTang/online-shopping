package com.cduestc.tyr.online_shopping.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.dao.IUserDao;
import com.cduestc.tyr.online_shopping.service.IUserService;
import com.cduestc.tyr.online_shopping.utils.CheckCode;
import com.cduestc.tyr.online_shopping.utils.MD5;
import com.cduestc.tyr.online_shopping.utils.SendEmail;

@Service
public class UserService implements IUserService {
	
	@Resource
	IUserDao dao;
	
	@Override
	public int findUserToLogin(String userName, String password, String checkCode, HttpSession session) throws Exception {
		String s_checkCode = (String) session.getAttribute("checkCode");
		//清除session中的验证码
		session.removeAttribute("checkCode");
		if(!checkCode.equalsIgnoreCase(s_checkCode)) {
			return -2;
		}
		UserBean user = dao.getUserByUserName(userName);
		if(null == user) {
			return 0;
		}
		if(!(user.getPassword().equals(MD5.toMD5(password)))) {
			return -1;
		}
		//将用户名存入session
		session.setAttribute("user", user);
		//登录成功设置session时间为30分钟
		session.setMaxInactiveInterval(30*60);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findUserOrsendEmailCode(String email, Boolean exist, HttpSession session) throws Exception {
		int status;
		UserBean user = dao.getUserByEmail(email);
		//用户注册
		if((null != user) && (exist==null)) {
			return 0;
		}
		//修改密码
		if((null==user) && (exist!=null)) {
			return 0;
		}
		String code = CheckCode.getChckCode();
System.out.println(code);
		status = SendEmail.send(email, "安全验证", "您此次操作的验证码为<b>"+code+"</b> 有效期10分钟");
		if(status == 1) {
			@SuppressWarnings("rawtypes")
			Map map = new HashMap();
			map.put("checkCode", code);
			map.put("start", Long.valueOf(System.currentTimeMillis()));
			map.put("email", email);
			session.setAttribute("checkCodeMessage", map);
			session.setMaxInactiveInterval(10*60);
		}
		return status;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int checkEmailCode(String email, String checkCode, HttpSession session) throws Exception {
		Map message = (Map) session.getAttribute("checkCodeMessage");
		session.removeAttribute("checkCodeMessage");
		if(null == message) {
			return 0;
		}
		String s_email = (String) message.get("email");
		String s_checkCode = (String) message.get("checkCode");
		Long startTime = (Long) message.get("start");
		Long now = System.currentTimeMillis();
		if(!email.equals(s_email) || (now-startTime > 10*60*100)) {
			return 0;
		}
		if(!checkCode.equals(s_checkCode)) {
			return -1;
		}
		session.setAttribute("registerEmail", s_email);
		return 1;
	}

	@Override
	public int addUser(UserBean user,HttpSession session) throws Exception {
		String email = (String) session.getAttribute("registerEmail");
		if(email == null) {
			return -1;
		}
		//检查用户是否存在
		UserBean queryUser = dao.getUserByNickname(user.getNickname());
		if(null != queryUser) {
			return 0;
		}
		user.setEmail(email);
		user.setPassword(MD5.toMD5(user.getPassword()));
		dao.addUser(user);
		session.removeAttribute("registerEmail");
		return 1;
		
	}

	@Override
	public int updateUser(UserBean user, HttpSession session) throws Exception {
		//获取email,可从session中取registerEmail或user
		String email = (String) ((session.getAttribute("registerEmail")!=null)?session.getAttribute("registerEmail") : ((UserBean)session.getAttribute("user")).getEmail());
		if(null == email) {
			return 0;
		}
		//判断是否是更改密码
		if(null != user.getPassword()) {
			user.setPassword(MD5.toMD5(user.getPassword()));
		}
		user.setEmail(email);
		return dao.updateUser(user);
	}

	@Override
	public void checkImage(HttpServletResponse response, HttpSession session) throws Exception {
		/*
		 * 绘制图片
		 */
		//创建内存画板对象
		BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		//获取画笔
		Graphics g = image.getGraphics();
		//设置画笔颜色
		g.setColor(new Color(255,255,255));
		//为画板设置背景颜色
		g.fillRect(0, 0, 80, 30);
		//获取5位0-9、a-z的组成字符串
		String number = CheckCode.getChckCode();
		//将验证码绑订到session,用来验证。
		session.setAttribute("checkCode", number);
		//设置画笔颜色位随机
		Random r = new Random();
		g.setColor(new Color(175,8,2));
		//设置字体大小和格式
		g.setFont(new Font(null,Font.ITALIC,24));
		//绘制字符串(可以每次只画一个字符，画5次，每个字符的位置和大小不同)
		g.drawString(number, 2, 24);
		//绘制一些干扰线
		for(int i=0;i<(r.nextInt(10)+10);i++) {
			//设置画笔颜色
			g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(80));
		}
		/*
		 * 压缩图片
		 */
		//设置服务器返回数据类型
		response.setContentType("image/jpeg");
		//获得字节输出流
		OutputStream os = response.getOutputStream();
		//压缩图片并输出
		ImageIO.write(image, "jpeg", os);
		//关闭输出流
		os.close();
		
	}
	
}
