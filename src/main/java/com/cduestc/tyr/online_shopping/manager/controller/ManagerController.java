package com.cduestc.tyr.online_shopping.manager.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cduestc.tyr.online_shopping.beans.CommodityBean;
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
	
	@RequestMapping("/addSubKind.action")
	@ResponseBody
	public ResultData addSubKind(int mainId, String subName) {
		ResultData rd = new ResultData();
		try {
			service.addSubKind(mainId, subName);
			rd.setStatus(1);
		} catch(Exception e) {
			rd.setStatus(-1);
			rd.setInfo("添加子类出错");
			throw new RuntimeException("添加子类出错");
		}
		return rd;
	}
	@RequestMapping("/deleteMain.action")
	@ResponseBody
	public ResultData deleteMainKind(int mainId) {
		ResultData rd = new ResultData();
		try {
			service.deleteMainKind(mainId);
			rd.setStatus(1);
		} catch(Exception e) {
			rd.setStatus(-1);
			rd.setInfo("删除主分类出错");
			e.printStackTrace();
			//throw new RuntimeException("删除主分类出错");
		}
		return rd;
	}
	
	@RequestMapping("/addCommodity.action")
	@ResponseBody
	public ResultData addCommodity(@RequestParam(value="file",required=false) MultipartFile file[], CommodityBean com,  
			HttpServletRequest req) {
		ResultData rd = new ResultData();
		System.out.println(com);
		//获得物理路径webapp所在路径  
        String pathRoot = req.getSession().getServletContext().getRealPath(File.separator + "commodityImage");  
        String path="";  
        List<String> listImagePath = new ArrayList<String>();  
        for (MultipartFile mf : file) {  
            if(!mf.isEmpty()){  
            	System.out.println(mf.getName());
            	System.out.println(mf.getOriginalFilename());
//                //生成uuid作为文件名称  
//                String uuid = UUID.randomUUID().toString().replaceAll("-","");  
//                //获得文件类型（可以判断如果不是图片，禁止上传）  
//                String contentType=mf.getContentType();  
//                //获得文件后缀名称  
//                String imageName=contentType.substring(contentType.indexOf("/")+1);  
//                path="/static/images/"+uuid+"."+imageName;  
//                mf.transferTo(new File(pathRoot+path));  
//                listImagePath.add(path);  
            }  
        } 
        System.out.println(file.length);   
        System.out.println(pathRoot);   
		return rd;
	}
}
