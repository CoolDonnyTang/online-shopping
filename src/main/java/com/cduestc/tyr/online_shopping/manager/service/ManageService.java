package com.cduestc.tyr.online_shopping.manager.service;

import java.util.List;
import java.util.Map;

import com.cduestc.tyr.online_shopping.beans.CommEntityBean;
import com.cduestc.tyr.online_shopping.beans.CommodityBean;
import com.cduestc.tyr.online_shopping.beans.KindBean;
import com.cduestc.tyr.online_shopping.beans.RecommendBrandBean;
import com.cduestc.tyr.online_shopping.manager.beans.Message4AddCommEntityPOJO;

public interface ManageService {
	public int addMainKindService(String mainName, String subName);
	public List<KindBean> findMainKind();
	public void addSubKind(int mainId, String subTex);
	public void deleteMainKind(int id);
	public void addCommodity(CommodityBean comm);
	public void addCommEntity(CommEntityBean comm);
	public List<Message4AddCommEntityPOJO> findBrandTitlePropBySubKindId(int subKindId);
	public int addRecommendEntity(Integer ids[], Integer recommendType);
	public void addRecommendBrand(RecommendBrandBean recommendBrand);
	public List<String> queryAllBrandNameFromCommModel();
}
