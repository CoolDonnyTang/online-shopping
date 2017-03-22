package com.cduestc.tyr.online_shopping.manager.dao;

import java.util.List;
import java.util.Map;

import com.cduestc.tyr.online_shopping.beans.CommodityBean;
import com.cduestc.tyr.online_shopping.beans.KindBean;

public interface ManageDao {
	public void addMainKind(KindBean kind);
	public List<KindBean> findMainKind();
	public KindBean findMainKindById(int id);
	public void updateSubKind(KindBean kind);
	public void deleteMainKindById(int id);
	public void addCommodity(CommodityBean comm);
	public List<Map> findBrandTitlePropBySubKindId(int subKindId);
}
