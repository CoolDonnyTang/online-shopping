package com.cduestc.tyr.online_shopping.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.dao.ICommodityDao;
import com.cduestc.tyr.online_shopping.service.ICommodityService;

@Service
public class CommodityServiceImpl implements ICommodityService {
	
	@Resource
	private ICommodityDao dao;

	@Override
	public List<Map> findSimpleComm(Map map) {
		if(map.get("mainKindId") != null) {
			return dao.findSimpleCommByMainKindId((int) map.get("mainKindId"));
		} else if(map.get("subKindId") != null) {
			return dao.findSimpleCommBySubKindId((int) map.get("subKindId"));
		} else if(map.get("nameKey") != null) {
			
		}
		return null;
	}

}
