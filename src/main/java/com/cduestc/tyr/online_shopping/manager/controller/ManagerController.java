package com.cduestc.tyr.online_shopping.manager.controller;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cduestc.tyr.online_shopping.beans.CommEntityBean;
import com.cduestc.tyr.online_shopping.beans.CommodityBean;
import com.cduestc.tyr.online_shopping.beans.CommodityImageBean;
import com.cduestc.tyr.online_shopping.beans.CommodityParamDetailBean;
import com.cduestc.tyr.online_shopping.beans.CommodityPropertyBean;
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
				String property1, String property2, String property1Content,
				String property2Content, HttpServletRequest req) {
		ResultData rd = new ResultData();
		try{
			System.out.println(com);
			//获得物理路径webapp所在路径  
	        String pathRoot = req.getSession().getServletContext().getRealPath("");  
	        String path = "";  
	        String url = "";
	        Set<CommodityImageBean> images = new HashSet<CommodityImageBean>();
	        Set<CommodityPropertyBean> props = new HashSet<CommodityPropertyBean>();
	        long time = System.currentTimeMillis();
	        //图片顺序
	        int num = 0;
	        //详情图片
	        for (MultipartFile mf : file) {
	            if(!mf.isEmpty() && mf.getContentType().matches("(?i)image/((jpg)|(gif)|(jpeg)|(png))")){
	            	num++;
	            	//获取文件后缀名
	            	String imageType = mf.getContentType().substring(mf.getContentType().indexOf("/")+1);
	            	//生成uuid作为文件名称  
	    			String uuid = UUID.randomUUID().toString().replaceAll("-","");
            		path = File.separator + "commodityImages" + File.separator + "detailImags" + File.separator + uuid + "." + imageType;
	    			url = req.getContextPath().replace("/", "\\") + path;
	    			File file11 = new File(pathRoot, path);
	    			System.out.println("url:  " + url);
	    			System.out.println("path:  " + file11.getPath());
	    			if(!file11.exists()) {
	    				file11.mkdirs();
	    			}
					mf.transferTo(file11);
					CommodityImageBean image = new CommodityImageBean();
					image.setUrl(url);
					image.setRealPath(pathRoot + path);
					image.setSerialNumber(num);
					image.setMainImage(false);
					image.setEntryId(1);
					image.setLastChangeTime(time);
					image.setEntryTime(time);
					images.add(image); 
	            }  
	        }
	        //属性1
	        if(!"".equals(property1)) {
	        	CommodityPropertyBean prop = new CommodityPropertyBean();
	        	prop.setPropertyName(property1);
	        	prop.setPropertyCotent(property1Content);
	        	prop.setEntryId(1);
	        	prop.setEntryTime(time);
	        	prop.setLastChangeTime(time);
	        	props.add(prop);
	        }
	        //属性2
	        if(!"".equals(property2)) {
	        	CommodityPropertyBean prop = new CommodityPropertyBean();
	        	prop.setPropertyName(property2);
	        	prop.setPropertyCotent(property2Content);
	        	prop.setEntryId(1);
	        	prop.setEntryTime(time);
	        	prop.setLastChangeTime(time);
	        	props.add(prop);
	        }
	        com.setImages(images);
	        com.setProperties(props);
	        com.setEntryId(1);
	        com.setEntryTime(time);
	        com.setLastChangeTime(time);
	        service.addCommodity(com);
	        rd.setStatus(1);
	        rd.setInfo("success");
		} catch (Exception e) {
			rd.setStatus(-1);
			rd.setInfo("出现未知错误，添加失败");
			e.printStackTrace();
		}
		return rd;
	}
	
	@RequestMapping("/addCommEntity.action")
	@ResponseBody
	public ResultData addCommodity(@RequestParam(value="file",required=false) MultipartFile file[], CommEntityBean commEntity,
						String paramss, HttpServletRequest req) {
		ResultData rd = new ResultData();
		try{
			//获得物理路径webapp所在路径  
	        String pathRoot = req.getSession().getServletContext().getRealPath("");  
	        String path = "";  
	        String url = "";
	        Set<CommodityImageBean> images = new HashSet<CommodityImageBean>();
	        Set<CommodityParamDetailBean> params = new HashSet<CommodityParamDetailBean>();
	        long time = System.currentTimeMillis();
	        //图片顺序
	        int num = 0;
	        //详情图片
	        for (MultipartFile mf : file) {
	            if(!mf.isEmpty() && mf.getContentType().matches("(?i)image/((jpg)|(gif)|(jpeg)|(png))")){
	            	num++;
	            	//获取文件后缀名
	            	String imageType = mf.getContentType().substring(mf.getContentType().indexOf("/")+1);
	            	//生成uuid作为文件名称  
	    			String uuid = UUID.randomUUID().toString().replaceAll("-","");
            		path = File.separator + "commodityImages" + File.separator + "mainImags" + File.separator + uuid + "." + imageType;
	    			url = req.getContextPath().replace("/", "\\") + path;
	    			File file11 = new File(pathRoot, path);
	    			System.out.println("url:  " + url);
	    			System.out.println("path:  " + file11.getPath());
	    			if(!file11.exists()) {
	    				file11.mkdirs();
	    			}
					mf.transferTo(file11);
					CommodityImageBean image = new CommodityImageBean();
					image.setUrl(url);
					image.setRealPath(pathRoot + path);
					image.setSerialNumber(num);
					image.setMainImage(true);
					image.setEntryId(1);
					image.setLastChangeTime(time);
					image.setEntryTime(time);
					images.add(image); 
	            }  
	        }
	        //参数处理
	        String param[] = paramss.split("\\s+");
	        for(String s : param) {
	        	s = s.trim();
	        	if(!"".equals(s)) {
	        		CommodityParamDetailBean commParam = new CommodityParamDetailBean();
	        		commParam.setEntryId(1);
	        		commParam.setEntryTime(time);
	        		commParam.setLastChangeTime(time);
	        		commParam.setParamContent(s);
	        		params.add(commParam);
	        	}
	        }
	        commEntity.setImages(images);
	        commEntity.setParams(params);
	        commEntity.setEntryId(1);
	        commEntity.setEntryTime(time);
	        commEntity.setLastChangeTime(time);
	        commEntity.setSales(0);
	        service.addCommEntity(commEntity);
	        rd.setStatus(1);
	        rd.setInfo("success");
//System.out.println("entity:" + commEntity + "  param:" + paramss);
		} catch (Exception e) {
			rd.setStatus(-1);
			rd.setInfo("出现未知错误，添加失败");
			e.printStackTrace();
		}
		return rd;
	}
	
	@RequestMapping("/selectMessage4addCommEntity.action")
	@ResponseBody
	public ResultData selectMessage4addCommEntity(Integer subKindId) {
		ResultData rd = new ResultData();
		try {
			rd.setData(service.findBrandTitlePropBySubKindId(subKindId));
			rd.setStatus(1);
	        rd.setInfo("success");
		} catch (Exception e) {
			rd.setStatus(-1);
			rd.setInfo("服务器偷懒啦 请稍后再试");
			e.printStackTrace();
		}
		return rd;
	}
	
	@RequestMapping("/addRcommendCommEntity.action")
	@ResponseBody
	public ResultData addRcommendCommEntity(@RequestParam(value="ids[]") Integer[] ids, Integer recommendType) {
		ResultData rd = new ResultData();
		try {
			int status = service.addRecommendEntity(ids, recommendType);
			rd.setStatus(status);
			if(status !=1 ) {
				rd.setInfo("添加失败");
			}
		} catch (Exception e) {
			rd.setStatus(-1);
			rd.setInfo("服务器偷懒啦 请稍后再试");
			e.printStackTrace();
		}
		return rd;
	}
	
}
