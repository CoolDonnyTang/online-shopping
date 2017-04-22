package com.cduestc.tyr.online_shopping.dao;

import java.util.List;

import com.cduestc.tyr.online_shopping.beans.IndexAdImageBean;

public interface IndexAdImageDao {
	/**
	 * 查找所有首页广告
	 * @author tangyanrentyr
	 * @2017年4月22日 2017年4月22日
	 */
	public List<IndexAdImageBean> findIndexAdImage();
}
