package com.cduestc.tyr.online_shopping.manager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.KindBean;
import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.manager.service.ManageService;

@Controller
@RequestMapping("/manage")
public class ManagerController {
	
	@Resource
	ManageService service;
	
	@RequestMapping("/addMainKind.action")
	@ResponseBody
	public ResultData addMain(String mainName, String subName) {
		ResultData rd = new ResultData();
		int status = service.addMainKindService(mainName, subName);
		if(1 == status) {
			rd.setStatus(1);
		} else {
			rd.setStatus(-1);
			rd.setInfo("未提取到有效输入");
		}
		return rd;
	}
	
	@RequestMapping("/findMain.action")
	@ResponseBody
	public ResultData findMain() {
		ResultData rd = new ResultData();
		List<KindBean> list = service.findMainKind();
		if(null == list) {
			rd.setStatus(-1);
			rd.setInfo("加载主分类失败");
		} else {
			rd.setStatus(1);
			rd.setInfo("成功");
			rd.setData(list);
		}
		return rd;
	}
	
}
