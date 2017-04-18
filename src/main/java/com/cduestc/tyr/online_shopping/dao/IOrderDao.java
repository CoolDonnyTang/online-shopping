package com.cduestc.tyr.online_shopping.dao;

import com.cduestc.tyr.online_shopping.beans.OrderBean;

public interface IOrderDao {
	/**
	 * 保存一个订单对象到数据库
	 * @author tangyanrentyr
	 * @2017年4月18日 2017年4月18日
	 * @param order
	 */
	public void addOrder(OrderBean order);
	
}
