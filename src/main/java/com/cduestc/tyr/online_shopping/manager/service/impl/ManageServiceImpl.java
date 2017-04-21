package com.cduestc.tyr.online_shopping.manager.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.beans.CommEntityBean;
import com.cduestc.tyr.online_shopping.beans.CommodityBean;
import com.cduestc.tyr.online_shopping.beans.KindBean;
import com.cduestc.tyr.online_shopping.beans.RecommendBrandBean;
import com.cduestc.tyr.online_shopping.beans.RecommendCommEntityBean;
import com.cduestc.tyr.online_shopping.manager.beans.Message4AddCommEntityPOJO;
import com.cduestc.tyr.online_shopping.manager.beans.Prop4AddCommEntityPOJO;
import com.cduestc.tyr.online_shopping.manager.beans.Title4AddCommEntityPOJO;
import com.cduestc.tyr.online_shopping.manager.dao.ManageDao;
import com.cduestc.tyr.online_shopping.manager.service.ManageService;
import com.cduestc.tyr.online_shopping.utils.RecommendKind;

@Service
public class ManageServiceImpl implements ManageService {
	
	@Resource
	ManageDao dao;
	
	@Override
	public int addMainKindService(String mainName, String subName) {
		if(null!=subName && !"".equals(subName)) {
			Long nowTime = System.currentTimeMillis();
			KindBean kind = new KindBean();
			kind.setKindName(mainName);
			kind.setEntryId(1);
			kind.setEntryTime(nowTime);
			kind.setLastChangeTime(nowTime);
			Set<KindBean> set = new HashSet<KindBean>();
			String subStr[] = subName.split("\\s+");
			for(String str : subStr) {
				KindBean k = new KindBean();
				k.setKindName(str);
				k.setEntryId(1);
				k.setEntryTime(nowTime);
				k.setLastChangeTime(nowTime);
				set.add(k);
			}
			kind.setSubKinds(set);
			dao.addMainKind(kind);
			return 1;
		}
		return -1;
	}

	@Override
	public List<KindBean> findMainKind() {
		return dao.findMainKind();
	}

	@Override
	public void addSubKind(int mainId, String subText) {
		KindBean kind = dao.findMainKindById(mainId);
		Set<KindBean> kinds = kind.getSubKinds();
		long time = System.currentTimeMillis();
		for(String s : subText.split("\\s+")) {
			KindBean k = new KindBean();
			k.setKindName(s);
			k.setEntryTime(time);
			k.setEntryId(1);
			k.setLastChangeTime(time);
			kinds.add(k);
		}
		kind.setLastChangeTime(time);
		kind.setSubKinds(kinds);
		dao.updateSubKind(kind);
	}

	@Override
	public void deleteMainKind(int id) {
		dao.deleteMainKindById(id);
	}

	@Override
	public void addCommodity(CommodityBean comm) {
		dao.addCommodity(comm);
	}

	@Override
	public List<Message4AddCommEntityPOJO> findBrandTitlePropBySubKindId(int subKindId) {
		List<Message4AddCommEntityPOJO> resultData = new ArrayList<Message4AddCommEntityPOJO>();
		//保存品牌
		Set<String> brands = new HashSet<String>();
		List<Map> results = dao.findBrandTitlePropBySubKindId(subKindId);
		for(Map map : results) {
			String brandName = (String)map.get("brand");
			if(null == brandName) {
				continue;
			}
			if(!brands.contains(brandName)) {
				brands.add(brandName);
				Message4AddCommEntityPOJO message = new Message4AddCommEntityPOJO();
				message.setBrandName(brandName);
				//保存标题
				Set<String> titles = new HashSet<String>();
				Set<Title4AddCommEntityPOJO> commTitles = new HashSet<Title4AddCommEntityPOJO>();
				for(Map map2 : results) {
					//flag，记录第一个属性
					boolean flag = false;
					String commTitle = (String)map2.get("title");
					if(null == commTitle) {
						continue;
					}
					if(brandName.equals((String)map2.get("brand")) && !titles.contains(commTitle)) {
						//该标题下第一个属性的Id
						Integer pop1Id = new Integer(-1);
						titles.add(commTitle);
						Title4AddCommEntityPOJO title = new Title4AddCommEntityPOJO();
						title.setCommId((Integer) map2.get("id"));
						title.setCommTitle(commTitle);
						//保存属性1
						Set<String> props1 = new HashSet<String>();
						Prop4AddCommEntityPOJO prop1 = new Prop4AddCommEntityPOJO();
						//保存属性2
						Set<String> props2 = new HashSet<String>();
						Prop4AddCommEntityPOJO prop2 = new Prop4AddCommEntityPOJO();
						for(Map map3 : results) {
							String propName = (String)map3.get("popName");
							if(null == propName) {
								continue;
							}
							if(!flag) {
								pop1Id = (Integer)map2.get("propId");
								flag = true;
							}
//System.out.println(pop1Id + " " + ((Integer)map3.get("propId")));
							if(pop1Id.intValue() == ((Integer)map3.get("propId")).intValue()) {
								if(brandName.equals((String)map3.get("brand")) && commTitle.equals((String)map3.get("title")) && !props1.contains(propName)) {
									props1.add(propName);
									prop1.setPropId(pop1Id);
									prop1.setPropName(propName);
									Set<String> propContents = new HashSet<String>();
									for(String s:((String)map3.get("porpContent")).split("\\s+")) {
										propContents.add(s);
									}
									prop1.setProps(propContents);
								}
							} else {
								if(brandName.equals((String)map3.get("brand")) && commTitle.equals((String)map3.get("title")) && !props2.contains(propName)) {
									props2.add(propName);
									prop2.setPropId((Integer)map3.get("propId"));
									prop2.setPropName(propName);
									Set<String> propContents = new HashSet<String>();
									for(String s:((String)map3.get("porpContent")).split("\\s+")) {
										propContents.add(s);
									}
									prop2.setProps(propContents);
								}
							}
						}
						title.setProp1(prop1);
						title.setProp2(prop2);
						commTitles.add(title);
					}
				}
				message.setCommTitles(commTitles);
				resultData.add(message);
			}
		}
		return resultData;
	}

	@Override
	public void addCommEntity(CommEntityBean comm) {
		dao.addCommEntity(comm);
	}

	@Override
	public int addRecommendEntity(Integer[] ids, Integer recommendType) {
		if(ids==null || ids.length<1 || recommendType==null) {
			return 0;
		}
		for(Integer i : ids) {
			RecommendCommEntityBean recommend = new RecommendCommEntityBean();
			recommend.setCommEntityId(i);
			recommend.setEntryTime(System.currentTimeMillis());
			recommend.setRecommendType(RecommendKind.values()[recommendType]);
			dao.saveRecommendEntity(recommend);
		}
		return 1;
	}

	@Override
	public void addRecommendBrand(RecommendBrandBean recommendBrand) {
		dao.addRecommendBrand(recommendBrand);
	}

	@Override
	public List<String> queryAllBrandNameFromCommModel() {
		return dao.queryAllBrandNameFromCommModel();
	}
}
