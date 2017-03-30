package com.cduestc.tyr.online_shopping.dao;

import java.util.List;
import java.util.Map;

import com.cduestc.tyr.online_shopping.beans.CommodityBean;

public interface ICommodityDao {
	public CommodityBean findCommModelAndEntityById(int id);
	public List<Map> findSimpleCommByMainKindId(int mainKindId, int firstResult, int pageSize);
	public List<Map> findSimpleCommBySubKindId(int subKindId, int firstResult, int pageSize);
}
