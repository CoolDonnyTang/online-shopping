package com.cduestc.tyr.online_shopping.dao;

import java.util.List;
import java.util.Map;

import com.cduestc.tyr.online_shopping.beans.OrderBean;
import com.cduestc.tyr.online_shopping.utils.OrderStatus;

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
	/**
	 * 根据用户id查询用户的已完成订单用于订单页面显示
	 * @author tangyanrentyr
	 * @2017年5月11日 2017年5月11日
	 * @param userId：用户id
	 * @param max true:查询近一年订单
	 * @return
	 */
	public List<Map<String, Object>> queryBaseOrderMessageByStatus(int userId, Boolean max, OrderStatus status);
}
