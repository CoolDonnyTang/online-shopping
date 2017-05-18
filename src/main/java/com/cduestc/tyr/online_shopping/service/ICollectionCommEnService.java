package com.cduestc.tyr.online_shopping.service;

import javax.servlet.http.HttpSession;

import com.cduestc.tyr.online_shopping.beans.ResultData;

public interface ICollectionCommEnService {
	/**
	 * 查询当前商品实体id是否被用户收藏
	 * @author tangyanrentyr
	 * @2017年5月17日 2017年5月17日
	 * @param CommEntityId
	 * @return
	 */
	public ResultData queryCommEnIsCollect(Integer commEntityId, HttpSession session);
	/**
	 * 根据状态加入收藏或移除收藏
	 * @author tangyanrentyr
	 * @2017年5月17日 2017年5月17日
	 * @param commEntityId
	 * @param nowStatus
	 * @param session
	 * @return
	 */
	public ResultData addOrRemoveCollectionCommEn(Integer commEntityId, Boolean nowStatus, HttpSession session);
	/**
	 * 添加多个商品到收藏夹中
	 * @author tangyanrentyr
	 * @2017年5月18日 2017年5月18日
	 * @param commEntitiesId
	 * @param session
	 * @return
	 */
	public ResultData addMoreToCollection(Integer[] commEntitiesId, HttpSession session);
	
	public ResultData queryCollectionCommEntitiesByUser(HttpSession session);
	
}
