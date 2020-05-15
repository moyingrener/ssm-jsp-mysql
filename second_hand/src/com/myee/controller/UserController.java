package com.myee.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.myee.pojo.User;
import com.myee.service.UserService;
@Controller
@RequestMapping("/user")
//主要负责用户注册和登陆
public class UserController {
	@Autowired
	private UserService userService;
	
	
	//登陆验证
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String isValidate(@Validated User user,BindingResult result,Model model,HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		//判断是否有错误
		if(result.hasErrors()){
			//获取错误信息,将错误信息传到登陆页面
			List<ObjectError> allErrors = result.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			return "forward:/login.jsp";
		}
		if(user!=null){
			//获取用户登录信息并查询数据库
			String userName =user.getUsername();
			String userPassword =user.getPassword();
			User user1=null;
			 user1=userService.selectByUserNameAndPassword(userName,userPassword);
			if(user1!=null){
				//假如存在,将userCustom存入session域
					 if(user1.getUsername().equals(userName)&&user1.getPassword().equals(userPassword))
					 {	//登陆成功返回首页
						 HttpSession session = req.getSession();
						 session.setAttribute("userid",user1.getID());
						 session.setAttribute("username", user1.getUsername());
						 return "forward:/index_1.jsp";
					 }			
				
			}else{
				model.addAttribute("theerror","*账号或密码错误!");
				return "forward:/login.jsp";
			}
		}
	//否则返回登陆失败页面
		return null;
	}
	
	//注册信息处理
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registe(@Validated User user,BindingResult result,Model model,
			HttpServletRequest req,HttpServletResponse res)throws Exception {
		//判断是否有错误
				if(result.hasErrors()){
					//获取错误信息
					List<ObjectError> allErrors = result.getAllErrors();
					//将错误信息传到页面
					model.addAttribute("allErrors", allErrors);
					//返回登陆页面
					return "forward:/register.jsp";
				}
		if(user!=null){
			//获取用户注册信息并查询数据库是否存在相同账户名用户
			String userName =user.getUsername();
			User user1=userService.selectByUserName(userName);
			if(user1!=null){
				//假如账户存在,返回注册页面
				 req.setAttribute("usernameerror","*用户名已存在!");
				 return "forward:/register.jsp";
			}else{
				try {
					//注册信息存入数据库返回登陆界面
					UUID randomUUID = UUID.randomUUID();
					String id=randomUUID.toString()+System.currentTimeMillis();//用户id=随机数+时间戳
					String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					user.setID(id);
					user.setPost("会员");
					user.setCredit("极好");
					user.setRegistertime(date);
					user.setTurnover("0");
					user.setComplaint("0");
					Integer i=null;
					 i = userService.insertNewUser(user);
					 if(i==1){
							req.setAttribute("registersuccess","*注册成功!");
							return "forward:/register.jsp"; 
					 }else {
						 req.setAttribute("registererror","*注册失败!");
							return "forward:/register.jsp"; 
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//注册表为空返回注册界面
		return "forward:/register.jsp";
	}
	
	//注销
	@RequestMapping(value="/Cancellation")
	public String Cancellation(HttpServletRequest req,HttpServletResponse res,HttpSession session){
		if(session!=null){
			session.invalidate();	
		}
		return "forward:/login.jsp";
	}
}
