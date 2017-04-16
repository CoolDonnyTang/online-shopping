package com.cduestc.tyr.online_shopping.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cduestc.tyr.online_shopping.beans.UserBean;
/**
 * 检查登陆后才能跳转至指定页面
 * @author donnyt
 *
 */
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if(null == user) {
			String param = request.getQueryString();
			String nextUrl = "?";
			if(null==param || "".equals(param.trim())) {
				nextUrl += Base64.encodeBase64String((request.getRequestURI()).getBytes());
			} else {
				nextUrl += Base64.encodeBase64String((request.getRequestURI() + "?" + param).getBytes());
			}
			response.sendRedirect(request.getContextPath() + "/login.html" + nextUrl);
			return false;
		}
		return true;
	}
	
}
