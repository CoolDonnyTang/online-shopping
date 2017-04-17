package com.cduestc.tyr.online_shopping.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.dao.ICommEntityDao;
import com.cduestc.tyr.online_shopping.service.ICommEntityService;
@Service
public class CommEntityServiceImpl implements ICommEntityService {
	
	@Resource
	private ICommEntityDao dao;
	
	@Override
	public List<Map<String, String>> findCommEntitiesById(Integer[] ids) {
		if(ids==null || ids.length<1) {
			return null;
		}
		return dao.findCommEntitiesById(ids);
	}

}
