package com.cduestc.tyr.online_shopping.manager.service;

import java.util.List;

import com.cduestc.tyr.online_shopping.beans.KindBean;

public interface ManageService {
	public int addMainKindService(String mainName, String subName);
	public List<KindBean> findMainKind();
	public void addSubKind(int mainId, String subTex);
	public void deleteMainKind(int id);
}
