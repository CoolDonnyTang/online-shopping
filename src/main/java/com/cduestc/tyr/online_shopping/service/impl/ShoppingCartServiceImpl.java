package com.cduestc.tyr.online_shopping.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.beans.ShoppingCartBean;
import com.cduestc.tyr.online_shopping.dao.IShoppingCartDao;
import com.cduestc.tyr.online_shopping.service.IShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {
	
	@Resource
	private IShoppingCartDao dao;
	
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

	@Override
	public int updateAmountById(Integer amount, Integer entityId) {
		if(null==amount || null==entityId) {
			return 0;
		}
		dao.updateAmountById(amount, entityId);
		return 1;
	}

	@Override
	public int deleteEntityById(Integer entityId) {
		if(entityId == null) {
			return 0;
		}
		dao.deleteEntityById(entityId);
		return 1;
	}

	@Override
	public int deleteEntities(Integer[] entitiesId) {
		if(null==entitiesId || entitiesId.length<1) {
			return 0;
		}
		dao.deleteEntities(entitiesId);
		return 1;
	}

}
