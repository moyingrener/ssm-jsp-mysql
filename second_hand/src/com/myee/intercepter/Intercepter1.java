package com.myee.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Intercepter1 extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();//��ȡ�����ַ
		String oldUrl=request.getRequestURI();
		//�������󹫹�ҳ��,����
		if(oldUrl.indexOf("/login")>=0||
				oldUrl.indexOf("/register")>=0||
				oldUrl.indexOf("/defaultMessage")>=0||
				oldUrl.indexOf("/Cancellation")>=0||
				oldUrl.indexOf("/inputtextselectgoods")>=0||
				oldUrl.indexOf("/theproductmessage")>=0
				){
			return true;
		}
		//����ʼ��֤		
		String userId=null;
		 userId=(String) session.getAttribute("userid");
		if(userId==null||userId==""){//��֤ʧ��
			request.getRequestDispatcher("/error.jsp").forward(request, response);		
			return false;
		}
		//��֤ʧ�ܷ��ص�½����
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
