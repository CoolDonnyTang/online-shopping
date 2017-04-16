package com.cduestc.tyr.online_shopping.service;

import java.util.List;
import java.util.Map;

public interface IShoppingCartService {
	/**
	 * 添加或者更新一天购物车记录
	 * @param userId
	 * @param commEntityId
	 * @param amount
	 * @return 1:成功   0:失败
	 */
	public int saveCommEn2Cart(int userId, int commEntityId, int amount);
	
	/**
	 * 根据userId找到对应的用户的购物车商品实体
	 * @param userId
	 * @return
	 */
	public List<Map<String,String>> findShoppingCartEnByUserId(int userId);
	/**
	 * 
	 * @author tangyanrentyr
	 * @2017年4月16日 2017年4月16日
	 * @param amount
	 * @param entityId
	 * @return 1:成功 0:失败
	 */
	public int updateAmountById(Integer amount, Integer entityId);
	/**
	 * 
	 * @author tangyanrentyr
	 * @2017年4月16日 2017年4月16日
	 * @param entityId
	 * @return 1:成功 其它：失败
	 */
	public int deleteEntityById(Integer entityId);
	/**
	 * 删除数组中的得id对应的所有实体
	 * @author tangyanrentyr
	 * @2017年4月16日 2017年4月16日
	 * @param entitiesId
	 * @return
	 */
	public int deleteEntities(Integer[] entitiesId);
	
	
	
	
	
	
}
