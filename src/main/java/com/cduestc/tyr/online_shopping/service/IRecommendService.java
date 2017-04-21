package com.cduestc.tyr.online_shopping.service;

import com.cduestc.tyr.online_shopping.beans.ResultData;

public interface IRecommendService {
	/**
	 * 根据页面传入的推荐类型找到对应的推荐的商品
	 * @author tangyanrentyr
	 * @2017年4月21日 2017年4月21日
	 * @return
	 */
	public ResultData findRecommend(Integer recommendType);
}
