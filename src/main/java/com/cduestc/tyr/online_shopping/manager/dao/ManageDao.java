package com.cduestc.tyr.online_shopping.manager.dao;

import java.util.List;
import java.util.Map;

import com.cduestc.tyr.online_shopping.beans.CommEntityBean;
import com.cduestc.tyr.online_shopping.beans.CommodityBean;
import com.cduestc.tyr.online_shopping.beans.IndexAdImageBean;
import com.cduestc.tyr.online_shopping.beans.KindBean;
import com.cduestc.tyr.online_shopping.beans.RecommendBrandBean;
import com.cduestc.tyr.online_shopping.beans.RecommendCommEntityBean;
import com.cduestc.tyr.online_shopping.utils.RecommendKind;

public interface ManageDao {
	public void addMainKind(KindBean kind);
	public List<KindBean> findMainKind();
	public KindBean findMainKindById(int id);
	public void updateSubKind(KindBean kind);
	public void deleteMainKindById(int id);
	public void addCommodity(CommodityBean comm);
	public void addCommEntity(CommEntityBean comm);
	public List<Map> findBrandTitlePropBySubKindId(int subKindId);
	/**
	 * 保存推荐商品
	 * @author tangyanrentyr
	 * @2017年4月19日 2017年4月19日
	 * @param recommend
	 */
	public void saveRecommendEntity(RecommendCommEntityBean recommend);
	/**
	 * 添加推荐品牌
	 * @author tangyanrentyr
	 * @2017年4月21日 2017年4月21日
	 * @param recommendBrad
	 */
	public void addRecommendBrand(RecommendBrandBean recommendBrad);
	/**
	 * 从商品模板中查询所有的品牌
	 * @author tangyanrentyr
	 * @2017年4月21日 2017年4月21日
	 * @return
	 */
	public List<String> queryAllBrandNameFromCommModel();
	/**
	 * 根据推荐类别删除该推荐类别下的推荐数据
	 * @author tangyanrentyr
	 * @2017年4月22日 2017年4月22日
	 * @param recommendType
	 */
	public void deleteRecommendEntitiesByRecommendType(RecommendKind recommendType);
	/**
	 * 添加首页广告图片
	 * @author tangyanrentyr
	 * @2017年4月22日 2017年4月22日
	 * @param adImage
	 */
	public void addAdImage(IndexAdImageBean adImage);
	/**
	 * 删除所有首页广告数据
	 * @author tangyanrentyr
	 * @2017年4月22日 2017年4月22日
	 */
	public void deleteAllAdImage();
	
	
	
	
	
}
