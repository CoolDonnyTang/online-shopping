package com.cduestc.tyr.online_shopping.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.beans.UserBean;
import com.cduestc.tyr.online_shopping.dao.ICollectionCommEntityDao;
import com.cduestc.tyr.online_shopping.service.ICollectionCommEnService;

@Service
public class CollectionCommEnServiceImpl implements ICollectionCommEnService {
	
	@Resource
	private ICollectionCommEntityDao dao;

	@Override
	public ResultData queryCommEnIsCollect(Integer commEntityId, HttpSession session) {
		ResultData rd = new ResultData();
		if(commEntityId==null || session.getAttribute("user")==null) {
			rd.setStatus(-1);
			rd.setInfo("查询失败");
			return rd;
		}
		Long result = dao.queryCommEnIsCollect(commEntityId, ((UserBean)(session.getAttribute("user"))).getId());
		if(result==null || result<1) {
			rd.setStatus(1);
			rd.setData(false);
			return rd;
		}
		rd.setStatus(1);
		rd.setData(true);
		return rd;
	}

	@Override
	public ResultData addOrRemoveCollectionCommEn(Integer commEntityId, Boolean nowStatus, HttpSession session) {
		ResultData rd = new ResultData();
		if(nowStatus==true) {
			if(dao.removeCollectionCommEn(commEntityId, ((UserBean)(session.getAttribute("user"))).getId())){
				rd.setStatus(1);
				//该false表示商品已不再用户收藏列表中
				rd.setData(false);
			}
		} else if(nowStatus==false) {
			if(dao.addCollectionCommEn(commEntityId, ((UserBean)(session.getAttribute("user"))).getId())){
				rd.setStatus(1);
				//该true表示商品已在收藏列表中
				rd.setData(true);
			}
		}
		return rd;
	}

	@Override
	public ResultData addMoreToCollection(Integer[] commEntitiesId, HttpSession session) {
		ResultData rd = new ResultData();
		if(commEntitiesId==null) {
			rd.setStatus(-1);
			rd.setInfo("参数错误");
			return rd;
		}
		dao.addCollectionCommEn(commEntitiesId, ((UserBean)(session.getAttribute("user"))).getId());
		rd.setStatus(1);
		return rd;
	}

	@Override
	public ResultData queryCollectionCommEntitiesByUser(HttpSession session) {
		ResultData rd = new ResultData();
		rd.setStatus(1);
		rd.setInfo("success");
		rd.setData(dao.findSimpleCommByCollection(((UserBean)(session.getAttribute("user"))).getId()));
		return rd;
	}

}
