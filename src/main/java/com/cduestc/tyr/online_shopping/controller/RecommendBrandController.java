package com.cduestc.tyr.online_shopping.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.service.IRecommendBrandService;

@Controller
@RequestMapping("/recommend")
public class RecommendBrandController {
	
	@Resource
	IRecommendBrandService service;
	
	@RequestMapping("/findRecommendBrand.action")
	@ResponseBody
	public ResultData findRecommend(Integer recommendType) {
		try {
			return service.findRecommendBrand();
		} catch(Exception e) {
			ResultData result = new ResultData();
			e.printStackTrace();
			result.setStatus(-1);
			result.setInfo("异常");
			return result;
		}
	}
}
