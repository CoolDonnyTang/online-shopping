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
	
	@Resource
	private ICommodityService service;
	
	@RequestMapping("/findComm.action")
	@ResponseBody
	public ResultData findCommdity(Integer mainKindId, Integer subKindId, String nameKey) {
		ResultData result = new ResultData();
		try {
			Map map = new HashMap();
			map.put("mainKindId", mainKindId);
			map.put("subKindId", subKindId);
			map.put("nameKey", nameKey);
			result.setData(service.findSimpleComm(map));
			result.setStatus(1);
			result.setInfo("success");
		} catch (Exception e) {
			result.setStatus(-1);
			result.setInfo("服务器出现了一个异常");
			e.printStackTrace();
		}
		return result;
	}
}
