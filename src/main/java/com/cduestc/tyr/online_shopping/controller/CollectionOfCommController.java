package com.cduestc.tyr.online_shopping.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.service.ICollectionCommEnService;

/**
 * 处理和商品收藏相关的ajax请求
 * @author tangyanrentyr
 * @2017年5月17日 2017年5月17日
 */
@Controller
public class CollectionOfCommController {
	
	@Resource
	private ICollectionCommEnService service;
	
	/**
	 * 查询当前商品实体id是否被用户收藏
	 * @author tangyanrentyr
	 * @2017年5月17日 2017年5月17日
	 * @param commEntityId
	 * @return
	 */
	@RequestMapping("/isCollect.action")
	@ResponseBody
	public ResultData isCollect(Integer commEntityId, HttpSession session) {
		try{
			ResultData rd  = service.queryCommEnIsCollect(commEntityId, session);
			return rd;
		} catch(Exception e) {
			e.printStackTrace();
			ResultData rd  = new ResultData();
			rd.setStatus(-1);
			rd.setInfo("服务器异常");
			return rd;
		}
	}
	
	/**
	 * 根据状态加入收藏或移除收藏
	 * @author tangyanrentyr
	 * @2017年5月17日 2017年5月17日
	 * @param commEntityId
	 * @return
	 */
	@RequestMapping("/checkLogin/collectionOperate.action")
	@ResponseBody
	public ResultData collectionOperate(Integer commEntityId, Boolean nowStatus, HttpSession session) {
		try{
			ResultData rd  = service.addOrRemoveCollectionCommEn(commEntityId, nowStatus, session);
			return rd;
		} catch(Exception e) {
			e.printStackTrace();
			ResultData rd  = new ResultData();
			rd.setStatus(-1);
			rd.setInfo("服务器异常");
			return rd;
		}
	}
	
	/**
	 * 添加多个商品到收藏夹中
	 * @author tangyanrentyr
	 * @2017年5月18日 2017年5月18日
	 * @param commEntitiesId
	 * @param session
	 * @return
	 */
	@RequestMapping("/checkLogin/addMoreToCollection.action")
	@ResponseBody
	public ResultData addMoreToCollection(@RequestParam(value = "commEntitiesId[]")Integer[] commEntitiesId, HttpSession session) {
		try{
			ResultData rd  = service.addMoreToCollection(commEntitiesId, session);
			return rd;
		} catch(Exception e) {
			e.printStackTrace();
			ResultData rd  = new ResultData();
			rd.setStatus(-1);
			rd.setInfo("服务器异常");
			return rd;
		}
	}
	
}
