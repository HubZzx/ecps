package com.rl.ecps.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rl.ecps.model.TsPtlUser;

public class loginInterceptor implements HandlerInterceptor {
	
	//前置拦截
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		TsPtlUser user=(TsPtlUser) session.getAttribute("user");
		if(user==null) {
			//user/toLogin.do
			response.sendRedirect(request.getContextPath()+"/user/toLogin.do");
			return false;
		}
		return true;
	}

	//后置拦截
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	//最终拦截
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
