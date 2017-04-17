package com.cduestc.tyr.online_shopping.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.service.ICommEntityService;

@Controller
@RequestMapping("/checkLogin")
public class OrderController {
	
	@Resource
	ICommEntityService commEnService;
	
	@RequestMapping("/findCommEntitiesById.action")
	@ResponseBody
	public ResultData findCommEntities(@RequestParam(value = "commEntitiesId[]") Integer[] commEntitiesId) {
		ResultData result = new ResultData();
		try{
			List<Map<String, String>> data = commEnService.findCommEntitiesById(commEntitiesId);
			if(null != data) {
				result.setStatus(1);
				result.setInfo("success");
				result.setData(data);
			} else {
				result.setStatus(-1);
				result.setInfo("查询数据失败");
			}
		} catch(Exception e) {
			result.setStatus(-1);
			result.setInfo("查询数据失败");
			e.printStackTrace();
		}
		return result;
	}
}
