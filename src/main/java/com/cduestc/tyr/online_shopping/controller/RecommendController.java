package com.cduestc.tyr.online_shopping.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.service.IRecommendService;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
	
	@Resource
	IRecommendService service;
	
	@RequestMapping("/findRecommend.action")
	@ResponseBody
	public ResultData findRecommend(Integer recommendType) {
		try {
			return service.findRecommend(recommendType);
		} catch(Exception e) {
			ResultData result = new ResultData();
			e.printStackTrace();
			result.setStatus(-1);
			result.setInfo("异常");
			return result;
		}
	}
}
