package com.cduestc.tyr.online_shopping.manager.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.beans.KindBean;
import com.cduestc.tyr.online_shopping.manager.dao.ManageDao;
import com.cduestc.tyr.online_shopping.manager.service.ManageService;

@Service
public class ManageServiceImpl implements ManageService {
	
	@Resource
	ManageDao dao;
	
	@Override
	public int addMainKindService(String mainName, String subName) {
		if(null!=subName && !"".equals(subName)) {
			Long nowTime = System.currentTimeMillis();
			KindBean kind = new KindBean();
			kind.setName(mainName);
			kind.setEntryId(1);
			kind.setEntryTime(nowTime);
			kind.setLastChangeTime(nowTime);
			Set<KindBean> set = new HashSet<KindBean>();
			String subStr[] = subName.split("\\s+");
			for(String str : subStr) {
				KindBean k = new KindBean();
				k.setName(str);
				k.setEntryId(1);
				k.setEntryTime(nowTime);
				k.setLastChangeTime(nowTime);
				set.add(k);
			}
			kind.setSubKinds(set);
			dao.addMainKind(kind);
			return 1;
		}
		return -1;
	}

	@Override
	public List<KindBean> findMainKind() {
		return dao.findMainKind();
	}

	@Override
	public void addSubKind(int mainId, String subText) {
		KindBean kind = dao.findMainKindById(mainId);
		Set<KindBean> kinds = kind.getSubKinds();
		long time = System.currentTimeMillis();
		for(String s : subText.split("\\s+")) {
			KindBean k = new KindBean();
			k.setName(s);
			k.setEntryTime(time);
			k.setEntryId(1);
			k.setLastChangeTime(time);
			kinds.add(k);
		}
		kind.setLastChangeTime(time);
		kind.setSubKinds(kinds);
		dao.updateSubKind(kind);
	}

	@Override
	public void deleteMainKind(int id) {
		dao.deleteMainKindById(id);
	}
}
