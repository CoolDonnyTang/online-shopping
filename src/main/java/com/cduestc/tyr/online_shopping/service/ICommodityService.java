package com.cduestc.tyr.online_shopping.service;

import java.util.List;
import java.util.Map;

import com.cduestc.tyr.online_shopping.beans.CommEntityBean;
import com.cduestc.tyr.online_shopping.beans.CommodityBean;

public abstract interface ICommodityService {
	public List<Map> findSimpleComm(Map map, int firstResult, int pageSize);
	public CommodityBean findCommModelAndEntity (int commId);
}
