package com.myee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myee.pojo.User;
import com.myee.service.UserService;

@Controller
@RequestMapping("/personal")
//主要负责个人中心的信息修改及查询
public class PersonalController {
	
	@Autowired
	private UserService userService;
	
		//返回个人中心页面,并显示个人信息
		@RequestMapping(value="/personalcenter")
		public String personalcenter(HttpServletRequest req,HttpServletResponse res){
			String userid =(String)req.getSession().getAttribute("userid");
			try {
				//查询数据库
				User user = userService.selectByUserId(userid);
				req.setAttribute("user", user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "personalcenter";
		}
		
		//返回修改信息页面(部分信息)返回包含用户部分信息的modifyuser_partial作为标识
		@RequestMapping(value="/modifypartialmessagepage")
		public String modifypartialmessagepage(HttpServletRequest req,HttpServletResponse res){
			String userid =(String)req.getSession().getAttribute("userid");
			try {
				User modifyuser_partial = userService.selectByUserId(userid);
				//modifyuser_1用来显示用户原本的信息
				req.setAttribute("modifyuser_partial",modifyuser_partial);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "personalcenter";
		}
		
		//修改除密码外的信息
		@RequestMapping(value="/modifypartialmessage")
		public String modifypartialmessage(@ModelAttribute("modifyuser_partial")User user,
				HttpServletRequest req,HttpServletResponse res){
			String userid =(String)req.getSession().getAttribute("userid");
			Integer i=null;
			try {
				user.setID(userid);
				//更新数据库
				 i = userService.updatePartialMessage(user);
				 //查询数据库 用于回显
				 user=userService.selectByUserId(userid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(i!=null){
				req.setAttribute("success","*修改成功!");
			}else{
				req.setAttribute("error","*修改失败!");
			}
			return "personalcenter";
		}
		
		//返回修改信息页面(密码)返回modifyuser_password作为标识
		@RequestMapping(value="/modifypasswordpage")
		public String modifypasswordpage(HttpServletRequest req,HttpServletResponse res){
				req.setAttribute("modifyuser_password","用于前台非空校验显示修改密码表单");
			return "personalcenter";
		}
		
		//修改密码
		@RequestMapping(value="/modifypassword")
		public String modifypassword(HttpServletRequest req,HttpServletResponse res,
				String oldpassword,String newpassword){
			String userid =(String)req.getSession().getAttribute("userid");
			User user = userService.selectByUserId(userid);
			Integer i=1;
			//校验原密码是否正确
			 if(user.getPassword().equals(oldpassword)){
				 //如果正确就开始修改密码操作
				 try {
					 i = userService.updatepasswordByID(userid,newpassword);
				} catch (Exception e) {
					e.printStackTrace();
				}
				 if(i==1){
						req.setAttribute("success","*修改成功!");
					}else{
						req.setAttribute("error_2","*修改失败!");
					}
				 
			 }else{
				 req.setAttribute("error_1","*请输入正确的密码!");
			}
			 req.setAttribute("modifyuser_password","用于前台非空校验显示修改密码表单");
			return "personalcenter";
		}
		
		//返回设置支付密码页面返回modifyuser_paypassword作为标识
		@RequestMapping("/modifypaypasswordpage")
		public String setpaymessage(HttpServletRequest req,HttpServletResponse res){
			String userid =(String)req.getSession().getAttribute("userid");
			User user = userService.selectByUserId(userid);
			//用户第一次设置支付密码,不显示输入原密码的输入框,用exists作标识
			if(user.getPaypassword()==null||(user.getPaypassword()).equals("")){//假如支付密码为空
				req.setAttribute("modifyuser_paypassword","用于前台非空校验显示修改支付密码表单");
				req.setAttribute("noexists","用于前台显示输入原支付密码输入框标识");
				return "personalcenter";
			}
			req.setAttribute("modifyuser_paypassword","用于前台非空校验显示修改支付密码表单");
			return "personalcenter";
		}
		
		//修改支付密码
		@RequestMapping(value="/modifypaypassword")
		public String modifypaypassword(HttpServletRequest req,HttpServletResponse res,
				String oldpaypassword,String newpaypassword){
					String userid =(String)req.getSession().getAttribute("userid");
					User user = userService.selectByUserId(userid);
					Integer i=null;
					if(oldpaypassword==null){//判断用户是否是第一次设置支付密码
						 //如果正确就开始修改支付密码操作
						 try {
							 i = userService.updatepaypasswordByID(userid, newpaypassword);
						} catch (Exception e) {
							e.printStackTrace();
						}
						 if(i==1){
								req.setAttribute("success","*修改成功!");
							}else{
								req.setAttribute("error_2","*修改失败!");
								req.setAttribute("noexists","用于前台显示输入原支付密码输入框标识");
							}
					}else{
						//校验原支付密码是否正确
						 if(user.getPaypassword().equals(oldpaypassword)){
							 //如果正确就开始修改支付密码操作
							 try {
								 i = userService.updatepaypasswordByID(userid, newpaypassword);
							} catch (Exception e) {
								e.printStackTrace();
							}
							 if(i==1){
									req.setAttribute("success","*修改成功!");
								}else{
									req.setAttribute("error_2","*修改失败!");
								}
							 
						 }else{
							 req.setAttribute("error_1","*请输入正确的支付密码!");
						}
					}
					
					 req.setAttribute("modifyuser_paypassword","用于前台非空校验显示修改支付密码表单");
					return "personalcenter";
				}
		
		
		
}
