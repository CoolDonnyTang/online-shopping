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
			String subStr[] = subName.split("\\s*,\\s*");
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

	
}
