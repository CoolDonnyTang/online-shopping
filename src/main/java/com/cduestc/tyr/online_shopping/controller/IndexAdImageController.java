package com.cduestc.tyr.online_shopping.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cduestc.tyr.online_shopping.beans.ResultData;
import com.cduestc.tyr.online_shopping.service.IndexAdImageService;

@Controller
public class IndexAdImageController {
	
	@Resource
	private IndexAdImageService service;
	
	@RequestMapping("/indexAdImage.action")
	@ResponseBody
	public ResultData findIndexAdImage() {
		ResultData rd = new ResultData();
		try {
			rd.setData(service.findIndexAdImage());
			rd.setStatus(1);
			rd.setInfo("success");
		} catch(Exception e) {
			e.printStackTrace();
			rd.setStatus(-1);
			rd.setInfo("异常");
		}
		return rd;
	}
}
