package com.cduestc.tyr.online_shopping.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.service.ICommodityService;

@Controller
@RequestMapping("/comm")
public class CommodityController {
	private static final int PAGE_SIZE = 40;
	@Resource
	private ICommodityService service;
	
	@RequestMapping("/findComm.action")
	@ResponseBody
	public ResultData findCommdity(Integer mainKindId, Integer subKindId, String nameKey, Integer page) {
		ResultData result = new ResultData();
		try {
			Map map = new HashMap();
			map.put("mainKindId", mainKindId);
			map.put("subKindId", subKindId);
			map.put("nameKey", nameKey);
			if(null == page) {
				page = 1;
			}
			result.setData(service.findSimpleComm(map, (page-1)*PAGE_SIZE, PAGE_SIZE));
			result.setStatus(1);
			result.setInfo("success");
		} catch (Exception e) {
			result.setStatus(-1);
			result.setInfo("服务器出现了一个异常");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/findCommModelAndEntity.action")
	@ResponseBody
	public ResultData findCommModelAndEntity(Integer commId) {
		if(commId == null) {
			return null;
		}
		ResultData result = new ResultData();
		try {
			result.setData(service.findCommModelAndEntity(commId));
			result.setInfo("success");
			result.setStatus(1);
		} catch(Exception e) {
			result.setStatus(-1);
			result.setInfo("服务器出现了一个异常");
			e.printStackTrace();
		}
		return result;
	}
}
