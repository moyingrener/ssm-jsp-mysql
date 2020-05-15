package com.myee.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookie")
public class CookieController {
	

	@RequestMapping("/lastAccessTime")
	public String lastAccessTime(HttpServletRequest req,HttpServletResponse res){
		String attribute = (String) req.getSession().getAttribute("a");
		System.out.println(attribute);
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = format.format(date);		
		//����cookie,���ñ��η���ʱ��
		Cookie cookie=new Cookie("lastAccessTime",currentTime);
		//�־û�cookie
		cookie.setMaxAge(60*10*500);
		//��cookie������Ӧ
		res.addCookie(cookie);	
		//��ȡcookie
				Cookie[] cookies=req.getCookies();
				String cookieValue=null;
				if(cookies!=null){
					for(Cookie coo:cookies){
						String name = coo.getName();
						if(name.equals("lastAccessTime")){//��ȡ�ϴη���ʱ�䲢�ŵ�request��
							cookieValue=coo.getValue();
						}
					}			
				}
				//�Ե�һ�η��ʺ͵ڶ��η��ʽ����ж�
				res.setContentType("UTF-8");
				if(cookieValue==null){
					
					req.setAttribute("message","���ǵ�һ�η���!");
				}else {
					req.setAttribute("message",currentTime);
				}				
		return null;		
	}
	

}
