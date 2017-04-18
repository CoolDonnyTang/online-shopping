package com.cduestc.tyr.online_shopping.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.beans.OrderBean;
import com.cduestc.tyr.online_shopping.beans.OrderDetailBean;
import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.dao.ICommEntityDao;
import com.cduestc.tyr.online_shopping.dao.IOrderDao;
import com.cduestc.tyr.online_shopping.service.IOrderService;
import com.cduestc.tyr.online_shopping.utils.RegUtil;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Resource
	private IOrderDao dao;
	@Resource
	private ICommEntityDao commEnDao;
	
	@Override
	public ResultData addOrder(HttpSession session, Integer addrId, String[] entityIdAndAmount, Integer payment) {
		ResultData result = new ResultData();
		Map<Integer, Integer> updateAmount = new HashMap<Integer, Integer>();
		if(addrId==null || entityIdAndAmount==null || entityIdAndAmount.length<1 || null==payment) {
			result.setStatus(0);
			result.setInfo("订单参数有误，请返回购物车重试");
			return result;
		}
		Set<OrderDetailBean> details = new HashSet<OrderDetailBean>();
		boolean flag = false;
		double orderPrice = 0;
		for(String s : entityIdAndAmount) {
			s = s.trim();
			if(s.matches("commEntityId=\\d+&amount=\\d+")) {
				flag = true;
				int commEntityId = RegUtil.getIntNumber(s, "commEntityId=(\\d+)");
				int amount = RegUtil.getIntNumber(s, "amount=(\\d+)");
				Double enitytPrice = commEnDao.queryPriceByEnId(commEntityId);
				if(null == enitytPrice) {
					result.setStatus(0);
					result.setInfo("订单参数有误，请返回购物车重试");
					return result;
				}
				//检查库存
				int inventory = commEnDao.queryInventoryByEnId(commEntityId);
				if(inventory<1 || inventory<amount) {
					result.setStatus(0);
					result.setInfo("部分商品库存不足，请返回购物修改");
					return result;
				}
				//用于稍后修改商品实体数量
				updateAmount.put(commEntityId, inventory-amount);
				//生成订单每个商品信息
				OrderDetailBean detail = new OrderDetailBean();
				detail.setAmount(amount);
				detail.setCommEntityId(commEntityId);
				detail.setSalePrice(enitytPrice * amount);
				orderPrice += enitytPrice * amount;
				details.add(detail);
			}
		}
		if(!flag) {
			return null;
		}
		OrderBean order = new OrderBean();
		order.setBelongAddressId(addrId);
		order.setBelongUserId(((UserBean)(session.getAttribute("user"))).getId());
		order.setEntrtime(System.currentTimeMillis());
		order.setOrderPrice(orderPrice);
		order.setOrderDetail(details);
		order.setPayStatus(false);
		if(0==payment) {
			order.setPayment("货到付款");
		} else if(1==payment) {
			order.setPayment("网银支付");
		} else if(2==payment) {
			order.setPayment("支付宝支付");
		}
		dao.addOrder(order);
		//更新商品库存
		Set<Entry<Integer,Integer>> set = updateAmount.entrySet();
		for(Entry<Integer,Integer> entry : set) {
			commEnDao.updateInventoryByEnId(entry.getKey(), entry.getValue());
		}
		//构造返回数据
		result.setStatus(1);
		result.setData(order);
		result.setInfo("部分商品库存不足，请返回购物修改");
		return result;
	}

}
