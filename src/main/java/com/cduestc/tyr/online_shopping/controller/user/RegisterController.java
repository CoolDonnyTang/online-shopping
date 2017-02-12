package com.cduestc.tyr.online_shopping.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.service.IUserService;

@Controller
@RequestMapping("/user")
public class RegisterController {
	
	@Resource
	IUserService service;
	
	@RequestMapping("/checkMail.action")
	@ResponseBody
	public ResultData checkedEmail(String email,HttpSession session) {
		ResultData result = new ResultData();
		int status = service.sendEmailCode(email, session);
		if(status == 1) {
			result.setInfo("发送成功");
		} else {
			result.setInfo("发送失败,请检查邮箱是否存在");
		}
		result.setStatus(status);
		return result;
	}
	
	@RequestMapping("/checkEmailCode.action")
	@ResponseBody
	public ResultData checkEmailCode(String email, String checkCode, HttpSession session) {
		ResultData result = new ResultData();
		int status = service.checkEmailCode(email, checkCode, session);
		result.setStatus(status);
		if(status == -1) {
			result.setInfo("验证码错误，请重新获取");
		} else if(status == 1) {
			result.setInfo("验证成功");
		} else {
			result.setInfo("验证码无效，请重新获取");
		}
		return result;
	}
	
	@RequestMapping("/addUser.action")
	public ResultData addUser() {
		ResultData result = new ResultData();
		
		return result;
	}
}
