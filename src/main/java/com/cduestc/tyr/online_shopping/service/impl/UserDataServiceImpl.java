package com.cduestc.tyr.online_shopping.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.beans.ShoppingCartBean;
import com.cduestc.tyr.online_shopping.dao.IUserDataDao;
import com.cduestc.tyr.online_shopping.service.IUserDataService;

@Service
public class UserDataServiceImpl implements IUserDataService {
	
	@Resource
	private IUserDataDao dao;
	
	@Override
	public int saveCommEn2Cart(int userId, int commEntityId, int amount) {
		if(commEntityId<1 || amount<1) {
			return 0;
		}
		ShoppingCartBean entity = dao.getShoppingCartByUserIdAndCommEnId(userId, commEntityId);
		if(null == entity) {
			entity = new ShoppingCartBean();
			entity.setBelongUserId(userId);
			entity.setCommEntityId(commEntityId);
			entity.setAmount(amount);
			entity.setEntryTime(System.currentTimeMillis());
		} else {
			amount += entity.getAmount();
			if(amount<=9999) {
				entity.setAmount(amount);
			} else {
				entity.setAmount(9999);
			}
		}
		dao.saveOrUpdateShoppingCartEntity(entity);
		return 1;
	}

	@Override
	public List<Map<String, String>> findShoppingCartEnByUserId(int userId) {
		return dao.findShoppingCartEnByUserId(userId);
	}

}
