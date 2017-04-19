package com.cduestc.tyr.online_shopping.service;

import javax.servlet.http.HttpSession;

import com.cduestc.tyr.online_shopping.beans.ResultData;

public abstract interface IOrderService {
	public ResultData addOrder(HttpSession session, Integer addrId, String[] entityIdAndAmount, Integer payment);
	/**
	 * 根据订单Id查询订单信息并封装成对象返回
	 * @author tangyanrentyr
	 * @2017年4月19日 2017年4月19日
	 * @param orderId
	 * @return
	 */
	public ResultData queryOrderMessage(Integer orderId, HttpSession session);
}
