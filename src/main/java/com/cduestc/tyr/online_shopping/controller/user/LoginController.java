package com.cduestc.tyr.online_shopping.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.service.IUserService;

@Controller
@RequestMapping("/user")
public class LoginController {
	@Resource
	IUserService service;
	
	@RequestMapping("/login.action")
	@ResponseBody
	public ResultData checkUser(String userName, String password, String checkCode, HttpSession session) {
		ResultData result = new ResultData();
		try {
			int status = service.findUserToLogin(userName, password, checkCode, session);
			if(status == 1) {
				result.setInfo("登录成功");
			} else if(status == 0) {
				result.setInfo("用户不存在，请免费注册");
			} else if(status == -1) {
				result.setInfo("用户名或密码错误");
			}else if(status == -2) {
				result.setInfo("验证码错误");
			} 
			result.setStatus(status);
		} catch (Exception e) {
			result.setStatus(-3);
			result.setInfo("服务器出错，请稍后再试");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/codeImage.action")
	public void checkImage(HttpServletResponse response, HttpSession session, Double random) {
		try {
			service.checkImage(response, session, random);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
