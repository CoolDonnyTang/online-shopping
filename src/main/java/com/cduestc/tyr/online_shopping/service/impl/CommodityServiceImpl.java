package com.cduestc.tyr.online_shopping.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.beans.CommodityBean;
import com.cduestc.tyr.online_shopping.dao.ICommodityDao;
import com.cduestc.tyr.online_shopping.service.ICommodityService;

@Service
public class CommodityServiceImpl implements ICommodityService {
	
	@Resource
	private ICommodityDao dao;

	@Override
	public List<Map> findSimpleComm(Map map, int firstResult, int pageSize) {
		if(map.get("mainKindId") != null) {
			return dao.findSimpleCommByMainKindId((int) map.get("mainKindId"), firstResult, pageSize);
		} else if(map.get("subKindId") != null) {
			return dao.findSimpleCommBySubKindId((int) map.get("subKindId"), firstResult, pageSize);
		} else if(map.get("nameKey") != null) {
			String[] key = ((String)map.get("nameKey")).split("\\s+");
			if(key.length>0) {
				return dao.findSimpleCommBySearchKey(key, firstResult, pageSize);
			}
			return null;
		} else if(map.get("brandId") != null) {
			return dao.findSimpleCommByRecommendBrandId((int)map.get("brandId"), firstResult, pageSize);
		}
		return null;
	}

	@Override
	public CommodityBean findCommModelAndEntity(int commId) {
		return dao.findCommModelAndEntityById(commId);
	}

	@Override
	public List<Map> findSalesTopCommModleAndCommEntity() {
		return dao.findSimpleComm4SalesTop2();
	}

	

}
