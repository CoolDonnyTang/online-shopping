package com.cduestc.tyr.online_shopping.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.beans.RecommendBrandBean;
import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.dao.IRecommendBrandDao;
import com.cduestc.tyr.online_shopping.service.IRecommendBrandService;

@Service
public class RecommendBrandService implements IRecommendBrandService {
	
	@Resource
	private IRecommendBrandDao dao;
	
	@Override
	public ResultData findRecommendBrand() {
		ResultData rd = new ResultData();
		List<RecommendBrandBean> data = dao.findRecommendBrand();
		if(null != data) {
			rd.setStatus(1);
			rd.setData(data);
		} else {
			rd.setStatus(0);
		}
		return rd;		
	}

}
