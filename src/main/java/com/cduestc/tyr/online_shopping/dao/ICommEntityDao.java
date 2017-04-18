package com.cduestc.tyr.online_shopping.dao;

import java.util.List;
import java.util.Map;

public interface ICommEntityDao {
	/**
	 * 找到id符合的实体
	 * @author tangyanrentyr
	 * @2017年4月17日 2017年4月17日
	 * @return
	 */
	public List<Map<String,String>> findCommEntitiesById(Integer[] ids);
	/**
	 * 根据实体id查询实体价格
	 * @author tangyanrentyr
	 * @2017年4月18日 2017年4月18日
	 * @param commEntityId
	 * @return
	 */
	public Double queryPriceByEnId(int commEntityId);
	/**
	 * 根据实体id查询实体库存
	 * @author tangyanrentyr
	 * @2017年4月18日 2017年4月18日
	 * @param commEntityId
	 * @return
	 */
	public int queryInventoryByEnId(int commEntityId);
	/**
	 * 根据实体id更新实体库存
	 * @author tangyanrentyr
	 * @2017年4月18日 2017年4月18日
	 * @param commEntityId
	 * @return
	 */
	public void updateInventoryByEnId(int commEntityId, int newInventory);
}
