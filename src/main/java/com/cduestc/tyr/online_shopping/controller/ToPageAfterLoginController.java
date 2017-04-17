package com.cduestc.tyr.online_shopping.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.service.IShoppingCartService;
import com.cduestc.tyr.online_shopping.utils.RegUtil;

@Controller
@RequestMapping("/login")
public class ToPageAfterLoginController {
	
	@Resource
	IShoppingCartService service;
	
	@RequestMapping("/shopping-cart.action")
	public String toShoppingCart(HttpServletRequest req) {
		UserBean user = (UserBean) req.getSession().getAttribute("user");
		String paran = req.getQueryString();
		if(null!=paran && null!=user) {
			String s = new String(Base64.decodeBase64(paran)).toString().trim();
			if(s.matches("commEntityId=[1-9]\\d*&amount=[1-9]\\d*")) {
				Integer commEntityId = RegUtil.getIntNumber(s, "commEntityId=([1-9]\\d*)");
				Integer amount = RegUtil.getIntNumber(s, "amount=([1-9]\\d*)");
				service.saveCommEn2Cart(user.getId(), commEntityId, amount);
			}
			return "redirect:/login/shopping-cart.action";
		} else {
			return "shopping-cart";
		}
	}
	
	@RequestMapping("/order-commit.action")
	public String toOrderCommit() {
			return "order-commit";
		
	}
	
	
}
