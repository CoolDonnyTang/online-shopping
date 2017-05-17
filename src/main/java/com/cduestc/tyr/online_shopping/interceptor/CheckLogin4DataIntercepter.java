package com.cduestc.tyr.online_shopping.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cduestc.tyr.online_shopping.beans.UserBean;

public class CheckLogin4DataIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if(null == user) {
			response.setContentType("text/html;charset=UTF-8");
			// 设置request和response的字符集，防止乱码
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.flush();
			StringBuilder builder = new StringBuilder();
	        if(request.getParameter("pageUrl")==null){
	        	builder.append("href=");
		        builder.append(request.getContextPath() + "/login.html");
	        } else {
	        	builder.append("href=");
		        builder.append(request.getContextPath() + "/login.html");
	        	builder.append("?");
	        	builder.append(Base64.encodeBase64String(request.getParameter("pageUrl").getBytes()));
	        }
	        out.println(builder.toString());
			return false;
		}
		return true;
	}
	
}
