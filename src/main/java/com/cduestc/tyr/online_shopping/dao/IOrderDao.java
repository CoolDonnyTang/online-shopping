package com.cduestc.tyr.online_shopping.dao;

import java.util.Map;

import com.cduestc.tyr.online_shopping.beans.OrderBean;

public interface IOrderDao {
	/**
	 * 保存一个订单对象到数据库
	 * @author tangyanrentyr
	 * @2017年4月18日 2017年4月18日
	 * @param order
	 */
	public void addOrder(OrderBean order);
	/**
	 * 根据订单id及用户Id查询订单的基本信息
	 * @author tangyanrentyr
	 * @2017年4月19日 2017年4月19日
	 * @return
	 */
	public Map<String, String> queryOrderBaseInfoById(int orderId, int userId);
	
}
