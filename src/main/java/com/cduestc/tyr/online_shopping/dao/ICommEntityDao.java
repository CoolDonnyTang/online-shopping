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
	 * 根据id找到商品实体信息
	 * @author tangyanrentyr
	 * @2017年4月17日 2017年4月17日
	 * @return
	 */
	public Map<String,String> findCommEntityById(int id);
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
	 * 根据实体id和传入的销售数量更新实体库存和销量
	 * @author tangyanrentyr
	 * @2017年4月18日 2017年4月18日
	 * @param commEntityId
	 * @return
	 */
	public void updateInventoryByEnId(int commEntityId, int sales);
}
