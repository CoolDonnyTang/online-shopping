package com.cduestc.tyr.online_shopping.dao;

import java.util.List;
import java.util.Map;

public interface IOrderDetailDao {
	/**
	 * 根据订单orderId找到订单详细信息
	 * @author tangyanrentyr
	 * @2017年4月19日 2017年4月19日
	 * @param orderId
	 * @return
	 */
	public List<Map<String, Object>> findOrderDetailMessageByOrderId(int orderId);
}
