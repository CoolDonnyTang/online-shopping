package com.cduestc.tyr.online_shopping.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.service.IShoppingCartService;

@Controller
@RequestMapping("/checkLogin")
public class ShoppingCartController {
	
	@Resource
	IShoppingCartService service;
	
	@RequestMapping("/get-shopping-cart-entity.action")
	@ResponseBody
	public ResultData toShoppingCart(HttpServletRequest req) {
		ResultData result = new ResultData();
		try {
			UserBean user = (UserBean) req.getSession().getAttribute("user");
			result.setData(service.findShoppingCartEnByUserId(user.getId()));
			result.setInfo("success");
			result.setStatus(1);
		} catch(Exception e) {
			result.setInfo("服务器异常！！");
			result.setStatus(-1);
		}
		return result;
	}
	
	@RequestMapping("/updateShoppingCart.action")
	@ResponseBody
	public ResultData updataAmount(Integer amount, Integer entityId){
		ResultData result = new ResultData();
		try {
			int status = service.updateAmountById(amount, entityId);
			result.setStatus(status);
			if(status == 1) {
				result.setInfo("success");
			} else {
				result.setInfo("更新数量失败");
			}
		} catch(Exception e) {
			result.setStatus(-1);;
			result.setInfo("更新数量失败");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/deleteShoppingCart.action")
	@ResponseBody
	public ResultData deleteEntity(Integer entityId) {
		ResultData result = new ResultData();
		try{
			int status = service.deleteEntityById(entityId);
			result.setStatus(status);
			if(status == 1) {
				result.setInfo("success");
			} else {
				result.setInfo("删除失败");
			}
		} catch(Exception e) {
			result.setStatus(-1);
			result.setInfo("删除失败");
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("/deleteShoppingCarts.action")
	@ResponseBody
	public 	ResultData deleteEntities(@RequestParam(value = "entitiesId[]") Integer[] entitiesId) {
		ResultData result = new ResultData();
		try{
			int status = service.deleteEntities(entitiesId);
			result.setStatus(status);
			if(status == 1) {
				result.setInfo("success");
			} else {
				result.setInfo("删除失败");
			}
			return result;
		} catch(Exception e) {
			result.setStatus(-1);
			result.setInfo("删除失败");
			return result;
		}
		
	}
	
}
