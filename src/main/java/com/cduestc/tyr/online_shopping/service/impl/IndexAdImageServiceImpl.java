package com.cduestc.tyr.online_shopping.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cduestc.tyr.online_shopping.beans.IndexAdImageBean;
import com.cduestc.tyr.online_shopping.dao.IndexAdImageDao;
import com.cduestc.tyr.online_shopping.service.IndexAdImageService;

@Service
public class IndexAdImageServiceImpl implements IndexAdImageService {
	
	@Resource
	private IndexAdImageDao dao;
	
	@Override
	public List<IndexAdImageBean> findIndexAdImage() {
		return dao.findIndexAdImage();
	}

}
