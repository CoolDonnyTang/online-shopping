package com.cduestc.tyr.online_shopping.dao;

import java.util.List;
import java.util.Map;

import com.cduestc.tyr.online_shopping.beans.CommodityBean;
import com.cduestc.tyr.online_shopping.utils.RecommendKind;

public interface ICommodityDao {
	
	public CommodityBean findCommModelAndEntityById(int id);
	/**
	 * 根据主分类信息查找商品模板和实体
	 * @author tangyanrentyr
	 * @2017年4月21日 2017年4月21日
	 * @param mainKindId
	 * @param firstResult
	 * @param pageSize
	 * @return
	 */
	public List<Map> findSimpleCommByMainKindId(int mainKindId, int firstResult, int pageSize);
	/**
	 * 根据子分类信息查找商品模板和实体
	 * @author tangyanrentyr
	 * @2017年4月21日 2017年4月21日
	 * @param subKindId
	 * @param firstResult
	 * @param pageSize
	 * @return
	 */
	public List<Map> findSimpleCommBySubKindId(int subKindId, int firstResult, int pageSize);
	/**
	 * 根据推荐查找商品模板和实体
	 * @author tangyanrentyr
	 * @2017年4月21日 2017年4月21日
	 * @param recommendType
	 * @return
	 */
	public List<Map> findSimpleCommByRecommendType(RecommendKind recommendType);
	
}
