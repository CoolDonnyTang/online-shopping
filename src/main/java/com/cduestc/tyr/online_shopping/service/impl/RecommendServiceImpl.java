package com.cduestc.tyr.online_shopping.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.dao.ICommodityDao;
import com.cduestc.tyr.online_shopping.service.IRecommendService;
import com.cduestc.tyr.online_shopping.utils.RecommendKind;

@Service
public class RecommendServiceImpl implements IRecommendService {
	
	@Resource
	private ICommodityDao commDao;
	
	@Override
	public ResultData findRecommend(Integer recommendType) {
		ResultData result = new ResultData();
		//检查该推荐类别是否存在
		if(recommendType==null || RecommendKind.values()[recommendType]==null) {
			result.setStatus(0);
			result.setInfo("推荐类别不存在");
			return result;
		}
		result.setStatus(1);
		result.setInfo("success");
		result.setData(commDao.findSimpleCommByRecommendType(RecommendKind.values()[recommendType]));
		return result;
	}

}
