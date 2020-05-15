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
		//创建cookie,设置本次访问时间
		Cookie cookie=new Cookie("lastAccessTime",currentTime);
		//持久化cookie
		cookie.setMaxAge(60*10*500);
		//将cookie加入响应
		res.addCookie(cookie);	
		//获取cookie
				Cookie[] cookies=req.getCookies();
				String cookieValue=null;
				if(cookies!=null){
					for(Cookie coo:cookies){
						String name = coo.getName();
						if(name.equals("lastAccessTime")){//获取上次访问时间并放到request域
							cookieValue=coo.getValue();
						}
					}			
				}
				//对第一次访问和第二次访问进行判断
				res.setContentType("UTF-8");
				if(cookieValue==null){
					
					req.setAttribute("message","您是第一次访问!");
				}else {
					req.setAttribute("message",currentTime);
				}				
		return null;		
	}
	

}
