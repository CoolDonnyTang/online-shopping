package com.cduestc.tyr.online_shopping.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import com.cduestc.tyr.online_shopping.dao.IOrderDetailDao;
import com.cduestc.tyr.online_shopping.dao.IShoppingCartDao;
import com.cduestc.tyr.online_shopping.service.IOrderService;
import com.cduestc.tyr.online_shopping.utils.OrderPayment;
import com.cduestc.tyr.online_shopping.utils.OrderStatus;
import com.cduestc.tyr.online_shopping.utils.RegUtil;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Resource
	private IOrderDao dao;
	@Resource
	private ICommEntityDao commEnDao;
	@Resource
	private IShoppingCartDao cartDao;
	@Resource
	private IOrderDetailDao orderDetailDao;
	
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
				updateAmount.put(commEntityId, amount);
				//生成订单每个商品信息
				OrderDetailBean detail = new OrderDetailBean();
				detail.setAmount(amount);
				detail.setCommEntityId(commEntityId);
				detail.setSalePrice(enitytPrice);
				orderPrice += enitytPrice * amount;
				details.add(detail);
			}
		}
		//检测订单是否有orderDetail信息，有则继续
		if(!flag) {
			return null;
		}
		OrderBean order = new OrderBean();
		order.setAddressId(addrId);
		order.setBelongUserId(((UserBean)(session.getAttribute("user"))).getId());
		order.setEntrtime(System.currentTimeMillis());
		order.setOrderPrice(orderPrice);
		order.setOrderDetail(details);
		order.setPayStatus(false);
		order.setPayment(OrderPayment.values()[payment]);
		if(payment==0) {
			order.setOrderStatus(OrderStatus.进行中);
		} else {
			order.setOrderStatus(OrderStatus.待付款);
		}
		dao.addOrder(order);
		//更新商品库存和销量
		Set<Entry<Integer,Integer>> set = updateAmount.entrySet();
		for(Entry<Integer,Integer> entry : set) {
			commEnDao.updateInventoryByEnId(entry.getKey(), entry.getValue());
		}
		//生成订单后删除购物车记录
		cartDao.deleteEntitiesByCommEnIds(updateAmount.keySet());
		//构造返回数据
		result.setStatus(1);
		result.setData(order);
		result.setInfo("success");
		return result;
	}

	@Override
	public ResultData queryOrderMessage(Integer orderId, HttpSession session) {
		ResultData result = new ResultData();
		if(orderId==null) {
			result.setStatus(0);
			result.setInfo("查询订单失败");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("orderBaseInfo",dao.queryOrderBaseInfoById(orderId, ((UserBean)(session.getAttribute("user"))).getId()));
		//等于空则说明该用户下没有该订单
		if(data.get("orderBaseInfo") == null) {
			result.setStatus(0);
			result.setInfo("查询订单失败");
		}
		//获取orderDetail信息
		List<Map<String, Object>> orderDetail = orderDetailDao.findOrderDetailMessageByOrderId(orderId);
		if(orderDetail==null || orderDetail.size()<1) {
			result.setStatus(0);
			result.setInfo("查询订单失败");
		}
		//查询orderDetail对应的实体的基本信息
		for(Map<String, Object> map : orderDetail) {
			System.out.println("+++++" + map.get("commEntityId"));
			Integer commEntityId = (Integer) map.get("commEntityId");
			map.putAll(commEnDao.findCommEntityById(commEntityId));
		}
		data.put("orderDetail", orderDetail);
		result.setStatus(1);
		result.setInfo("success");
		result.setData(data);
		return result;
	}
	
	

}
