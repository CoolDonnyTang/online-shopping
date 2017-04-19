package com.cduestc.tyr.online_shopping.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.OrderBean;
import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.service.ICommEntityService;
import com.cduestc.tyr.online_shopping.service.IOrderService;

@Controller
@RequestMapping("/checkLogin")
public class OrderController {
	
	@Resource
	ICommEntityService commEnService;
	@Resource
	IOrderService orderservice;
	
	@RequestMapping("/findCommEntitiesById.action")
	@ResponseBody
	public ResultData findCommEntities(@RequestParam(value = "commEntitiesId[]") Integer[] commEntitiesId) {
		ResultData result = new ResultData();
		try{
			List<Map<String, String>> data = commEnService.findCommEntitiesById(commEntitiesId);
			if(null != data) {
				result.setStatus(1);
				result.setInfo("success");
				result.setData(data);
			} else {
				result.setStatus(-1);
				result.setInfo("查询数据失败");
			}
		} catch(Exception e) {
			result.setStatus(-1);
			result.setInfo("查询数据失败");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/commitOrder.action")
	@ResponseBody
	public ResultData commitOrder(HttpSession session, Integer addrId, @RequestParam(value = "entityIdAndAmount[]") String[] entityIdAndAmount, Integer payment) {
		ResultData result = new ResultData();
		try {
			result = orderservice.addOrder(session, addrId, entityIdAndAmount, payment);
		} catch(Exception e) {
			result.setStatus(-1);
			result.setInfo("提交订单失败");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/queryOrderMessage.action")
	@ResponseBody
	public ResultData queryOrderMessage(HttpSession session, Integer orderId) {
		ResultData result = new ResultData();
		try {
			result = orderservice.queryOrderMessage(orderId, session);
		} catch(Exception e) {
			result.setStatus(-1);
			result.setInfo("查询订单失败");
			e.printStackTrace();
		}
		return result;
	}
	
}
