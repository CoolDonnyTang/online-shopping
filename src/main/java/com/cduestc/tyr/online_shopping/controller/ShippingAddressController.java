package com.cduestc.tyr.online_shopping.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.beans.ShippingAddressBean;
import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.service.IUserService;

@Controller
@RequestMapping("/checkLogin")
public class ShippingAddressController {
	
	@Resource
	IUserService userService;
	
	@RequestMapping("/getShippingAddr.action")
	@ResponseBody
	public ResultData findShippingAddr(HttpServletRequest req) {
		ResultData result = new ResultData();
		try{
			List<ShippingAddressBean> data = userService.findShippingAddressByUserId(((UserBean)(req.getSession().getAttribute("user"))).getId());
			result.setStatus(1);
			result.setInfo("success");
			result.setData(data);
		} catch(Exception e) {
			result.setStatus(-1);
			result.setInfo("请求数据失败");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/addShippingAddr.action")
	@ResponseBody
	public ResultData addShippingAddr(HttpServletRequest req, ShippingAddressBean addr) {
		ResultData result = new ResultData();
		try{
			int status = userService.addShippingAddress(addr, req.getSession());
			if(status==1) {
				result.setStatus(1);
				result.setInfo("success");
			} else {
				result.setStatus(0);
				result.setInfo("请求数据失败");
			}
		} catch(Exception e) {
			result.setStatus(-1);
			result.setInfo("请求数据失败");
			e.printStackTrace();
		}
		return result;
	}
	
	
}
