package com.cduestc.tyr.online_shopping.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cduestc.tyr.online_shopping.beans.UserBean;

public class CheckLogin4DataIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if(null == user) {
			response.sendRedirect(request.getContextPath() + "/login.html");
			return false;
		}
		return true;
	}
	
}
