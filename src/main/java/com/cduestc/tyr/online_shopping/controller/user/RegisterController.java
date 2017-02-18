package com.cduestc.tyr.online_shopping.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.service.IUserService;

@Controller
@RequestMapping("/user")
public class RegisterController {
	
	@Resource
	IUserService service;
	
	@RequestMapping("/checkMail.action")
	@ResponseBody
	public ResultData checkedEmail(String email,Boolean exist, HttpSession session) {
		ResultData result = new ResultData();
		int status;
		try {
			status = service.findUserOrsendEmailCode(email, exist, session);
			if(status == 1) {
				result.setInfo("发送成功");
			} else if((status==0) && (exist == null)) {
				result.setInfo("您已注册过，请直接登录");
			} else if((status==0) && (exist != null)) {
				result.setInfo("您还未注册过，请先注册");
			} else {
				result.setInfo("发送失败,请检查邮箱是否存在");
			}
			result.setStatus(status);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(-2);
			result.setInfo("发送失败，请稍后重试");
			return result;
		}
		
	}
	
	@RequestMapping("/checkEmailCode.action")
	@ResponseBody
	public ResultData checkEmailCode(String email, String checkCode, HttpSession session) {
		ResultData result = new ResultData();
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(-1);
			result.setInfo("验证失败，请稍后重试");
			return result;
		}
		
	}
	
	@RequestMapping("/addUser.action")
	@ResponseBody
	public ResultData addUser(UserBean user, HttpSession session) {
		ResultData result = new ResultData();
		try {
			int status = service.addUser(user, session); 
			if(status == 1) {
				result.setStatus(1);
				result.setInfo("注册成功");
			} else if(status == -1) {
				result.setStatus(-1);
				result.setInfo("邮箱验证失效,请刷新页面");
			} else {
				result.setStatus(0);
				result.setInfo("用户名已存在");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(-2);
			result.setInfo("注册失败，请稍后重试");
			return result;
		}
	}
	@RequestMapping("/updateUser")
	@ResponseBody
	public ResultData updateUser(UserBean user, HttpSession session) {
		
	}
}
