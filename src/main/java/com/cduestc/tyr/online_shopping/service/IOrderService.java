package com.cduestc.tyr.online_shopping.service;

import javax.servlet.http.HttpSession;

import com.cduestc.tyr.online_shopping.beans.ResultData;

public abstract interface IOrderService {
	public ResultData addOrder(HttpSession session, Integer addrId, String[] entityIdAndAmount, Integer payment);
}
