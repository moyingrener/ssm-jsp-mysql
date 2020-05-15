package com.myee.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Intercepter1 extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();//获取请求地址
		String oldUrl=request.getRequestURI();
		//假如请求公共页面,放行
		if(oldUrl.indexOf("/login")>=0||
				oldUrl.indexOf("/register")>=0||
				oldUrl.indexOf("/defaultMessage")>=0||
				oldUrl.indexOf("/Cancellation")>=0||
				oldUrl.indexOf("/inputtextselectgoods")>=0||
				oldUrl.indexOf("/theproductmessage")>=0
				){
			return true;
		}
		//否则开始认证		
		String userId=null;
		 userId=(String) session.getAttribute("userid");
		if(userId==null||userId==""){//验证失败
			request.getRequestDispatcher("/error.jsp").forward(request, response);		
			return false;
		}
		//验证失败返回登陆界面
		return true;
		
	}

/*	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}*/
	
}
