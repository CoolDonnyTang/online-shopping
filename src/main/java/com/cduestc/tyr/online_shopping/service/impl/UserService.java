package com.cduestc.tyr.online_shopping.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.service.IUserService;
import com.cduestc.tyr.online_shopping.utils.CheckCode;
import com.cduestc.tyr.online_shopping.utils.SendEmail;

@Service
public class UserService implements IUserService {

	@Override
	public UserBean findUser(UserBean user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int sendEmailCode(String email, HttpSession session) {
		String code = CheckCode.getChckCode();
System.out.println(code);
		int status = SendEmail.send(email, "用户注册", "您此次操作的验证码为<b>"+code+"</b> 有效期10分钟");
		if(status == 1) {
			Map map = new HashMap();
			map.put("checkCode", code);
			map.put("start", Long.valueOf(System.currentTimeMillis()));
			map.put("email", email);
			session.setAttribute("checkCodeMessage", map);
			session.setMaxInactiveInterval(30);
		}
		return status;
	}
	
	@Override
	public int checkEmailCode(String email, String checkCode, HttpSession session) {
		Map message = (Map) session.getAttribute("checkCodeMessage");
		if(null == message) {
			return 0;
		}
		String s_email = (String) message.get("email");
		String s_checkCode = (String) message.get("checkCode");
System.out.println(s_checkCode + "  ~~~~~ " + checkCode);
		Long startTime = (Long) message.get("start");
		Long now = System.currentTimeMillis();
		if(!email.equals(s_email) || (now-startTime > 10*60*100)) {
			return 0;
		}
		if(!checkCode.equals(s_checkCode)) {
			session.removeAttribute("checkCodeMessage");
			return -1;
		}
		return 1;
	}

	@Override
	public Boolean addUser(UserBean user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUser(UserBean user) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
