package com.cduestc.tyr.online_shopping.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.service.IUserDataService;

@Controller
@RequestMapping("/checkLogin")
public class GetDataAfterLoginController {
	
	@Resource
	IUserDataService service;
	
	@RequestMapping("/get-shopping-cart-entity.action")
	@ResponseBody
	public ResultData toShoppingCart(HttpServletRequest req) {
		ResultData result = new ResultData();
		UserBean user = (UserBean) req.getSession().getAttribute("user");
		result.setData(service.findShoppingCartEnByUserId(user.getId()));
		return result;
	}
}
