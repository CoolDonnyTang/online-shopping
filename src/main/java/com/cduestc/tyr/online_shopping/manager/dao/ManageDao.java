package com.cduestc.tyr.online_shopping.manager.dao;

import java.util.List;

import com.cduestc.tyr.online_shopping.beans.KindBean;

public interface ManageDao {
	public void addMainKind(KindBean kind);
	public List<KindBean> findMainKind();
}
