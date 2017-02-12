package com.cduestc.tyr.online_shopping.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;

@Controller
public class LoginController {
	@RequestMapping("/login.action")
	@ResponseBody
	public ResultData execute() {
		return null;
	}
}
