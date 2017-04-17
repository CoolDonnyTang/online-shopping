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
}
