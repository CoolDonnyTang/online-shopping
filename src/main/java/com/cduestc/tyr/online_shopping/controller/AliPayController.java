package com.cduestc.tyr.online_shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pay")
public class AliPayController {
	
	@RequestMapping("/execute.action")
	@ResponseBody
	public String executePay() {
		return "success";
	}
}
