package com.cduestc.tyr.online_shopping.dao;

import java.util.List;
import java.util.Map;

import com.cduestc.tyr.online_shopping.beans.ShoppingCartBean;

public interface IUserDataDao {
	/**
	 * 根据用户Id和商品实体Id查询购物车记录
	 * @param userId
	 * @param CommEnId
	 * @return
	 */
	public ShoppingCartBean getShoppingCartByUserIdAndCommEnId(int userId, int CommEnId);
	/**
	 * 保存或者更新一个购物车实体
	 * @param entity
	 */
	public void saveOrUpdateShoppingCartEntity(ShoppingCartBean entity);
	/**
	 * 根据userId找到用户的购物车商品
	 * donnyt
	 * 2017年4月13日
	 * @param userId
	 * @return
	 */
	public List<Map<String, String>> findShoppingCartEnByUserId(int userId);
}
