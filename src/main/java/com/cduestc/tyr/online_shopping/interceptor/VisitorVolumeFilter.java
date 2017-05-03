package com.cduestc.tyr.online_shopping.interceptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.cduestc.tyr.online_shopping.utils.IpUtils;
/**
 * 检查登陆后才能跳转至指定页面
 * @author donnyt
 *
 */
public class VisitorVolumeFilter implements Filter {
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }
        String url = request.getRequestURI().trim();
        if(url.equals("/") || url.startsWith("/login/") || url.endsWith(".html")) {
        	//获取ip地址的地理位置
        	String addr = IpUtils.getAddrByIp(ip);
        	String s = new Date().toLocaleString() +"   " + ip + "   " + addr + "   " + url;
        	String path = request.getSession().getServletContext().getRealPath("/log/visitor-log.txt");
        	File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
        	FileOutputStream fos = new FileOutputStream(file,true);
        	OutputStreamWriter w = new OutputStreamWriter(fos,"UTF-8");
        	PrintWriter p = new PrintWriter(w, true);
        	p.println(s);
        	p.close();
        }
        arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
