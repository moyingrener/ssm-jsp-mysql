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
//��Ҫ�����û�ע��͵�½
public class UserController {
	@Autowired
	private UserService userService;
	
	
	//��½��֤
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String isValidate(@Validated User user,BindingResult result,Model model,HttpServletRequest req,HttpServletResponse res)
			throws Exception {
		//�ж��Ƿ��д���
		if(result.hasErrors()){
			//��ȡ������Ϣ,��������Ϣ������½ҳ��
			List<ObjectError> allErrors = result.getAllErrors();
			model.addAttribute("allErrors", allErrors);
			return "forward:/login.jsp";
		}
		if(user!=null){
			//��ȡ�û���¼��Ϣ����ѯ���ݿ�
			String userName =user.getUsername();
			String userPassword =user.getPassword();
			User user1=null;
			 user1=userService.selectByUserNameAndPassword(userName,userPassword);
			if(user1!=null){
				//�������,��userCustom����session��
					 if(user1.getUsername().equals(userName)&&user1.getPassword().equals(userPassword))
					 {	//��½�ɹ�������ҳ
						 HttpSession session = req.getSession();
						 session.setAttribute("userid",user1.getID());
						 session.setAttribute("username", user1.getUsername());
						 return "forward:/index_1.jsp";
					 }			
				
			}else{
				model.addAttribute("theerror","*�˺Ż��������!");
				return "forward:/login.jsp";
			}
		}
	//���򷵻ص�½ʧ��ҳ��
		return null;
	}
	
	//ע����Ϣ����
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registe(@Validated User user,BindingResult result,Model model,
			HttpServletRequest req,HttpServletResponse res)throws Exception {
		//�ж��Ƿ��д���
				if(result.hasErrors()){
					//��ȡ������Ϣ
					List<ObjectError> allErrors = result.getAllErrors();
					//��������Ϣ����ҳ��
					model.addAttribute("allErrors", allErrors);
					//���ص�½ҳ��
					return "forward:/register.jsp";
				}
		if(user!=null){
			//��ȡ�û�ע����Ϣ����ѯ���ݿ��Ƿ������ͬ�˻����û�
			String userName =user.getUsername();
			User user1=userService.selectByUserName(userName);
			if(user1!=null){
				//�����˻�����,����ע��ҳ��
				 req.setAttribute("usernameerror","*�û����Ѵ���!");
				 return "forward:/register.jsp";
			}else{
				try {
					//ע����Ϣ�������ݿⷵ�ص�½����
					UUID randomUUID = UUID.randomUUID();
					String id=randomUUID.toString()+System.currentTimeMillis();//�û�id=�����+ʱ���
					String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					user.setID(id);
					user.setPost("��Ա");
					user.setCredit("����");
					user.setRegistertime(date);
					user.setTurnover("0");
					user.setComplaint("0");
					Integer i=null;
					 i = userService.insertNewUser(user);
					 if(i==1){
							req.setAttribute("registersuccess","*ע��ɹ�!");
							return "forward:/register.jsp"; 
					 }else {
						 req.setAttribute("registererror","*ע��ʧ��!");
							return "forward:/register.jsp"; 
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//ע���Ϊ�շ���ע�����
		return "forward:/register.jsp";
	}
	
	//ע��
	@RequestMapping(value="/Cancellation")
	public String Cancellation(HttpServletRequest req,HttpServletResponse res,HttpSession session){
		if(session!=null){
			session.invalidate();	
		}
		return "forward:/login.jsp";
	}
}
