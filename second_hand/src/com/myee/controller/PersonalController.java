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
//��Ҫ����������ĵ���Ϣ�޸ļ���ѯ
public class PersonalController {
	
	@Autowired
	private UserService userService;
	
		//���ظ�������ҳ��,����ʾ������Ϣ
		@RequestMapping(value="/personalcenter")
		public String personalcenter(HttpServletRequest req,HttpServletResponse res){
			String userid =(String)req.getSession().getAttribute("userid");
			try {
				//��ѯ���ݿ�
				User user = userService.selectByUserId(userid);
				req.setAttribute("user", user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "personalcenter";
		}
		
		//�����޸���Ϣҳ��(������Ϣ)���ذ����û�������Ϣ��modifyuser_partial��Ϊ��ʶ
		@RequestMapping(value="/modifypartialmessagepage")
		public String modifypartialmessagepage(HttpServletRequest req,HttpServletResponse res){
			String userid =(String)req.getSession().getAttribute("userid");
			try {
				User modifyuser_partial = userService.selectByUserId(userid);
				//modifyuser_1������ʾ�û�ԭ������Ϣ
				req.setAttribute("modifyuser_partial",modifyuser_partial);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "personalcenter";
		}
		
		//�޸ĳ����������Ϣ
		@RequestMapping(value="/modifypartialmessage")
		public String modifypartialmessage(@ModelAttribute("modifyuser_partial")User user,
				HttpServletRequest req,HttpServletResponse res){
			String userid =(String)req.getSession().getAttribute("userid");
			Integer i=null;
			try {
				user.setID(userid);
				//�������ݿ�
				 i = userService.updatePartialMessage(user);
				 //��ѯ���ݿ� ���ڻ���
				 user=userService.selectByUserId(userid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(i!=null){
				req.setAttribute("success","*�޸ĳɹ�!");
			}else{
				req.setAttribute("error","*�޸�ʧ��!");
			}
			return "personalcenter";
		}
		
		//�����޸���Ϣҳ��(����)����modifyuser_password��Ϊ��ʶ
		@RequestMapping(value="/modifypasswordpage")
		public String modifypasswordpage(HttpServletRequest req,HttpServletResponse res){
				req.setAttribute("modifyuser_password","����ǰ̨�ǿ�У����ʾ�޸������");
			return "personalcenter";
		}
		
		//�޸�����
		@RequestMapping(value="/modifypassword")
		public String modifypassword(HttpServletRequest req,HttpServletResponse res,
				String oldpassword,String newpassword){
			String userid =(String)req.getSession().getAttribute("userid");
			User user = userService.selectByUserId(userid);
			Integer i=1;
			//У��ԭ�����Ƿ���ȷ
			 if(user.getPassword().equals(oldpassword)){
				 //�����ȷ�Ϳ�ʼ�޸��������
				 try {
					 i = userService.updatepasswordByID(userid,newpassword);
				} catch (Exception e) {
					e.printStackTrace();
				}
				 if(i==1){
						req.setAttribute("success","*�޸ĳɹ�!");
					}else{
						req.setAttribute("error_2","*�޸�ʧ��!");
					}
				 
			 }else{
				 req.setAttribute("error_1","*��������ȷ������!");
			}
			 req.setAttribute("modifyuser_password","����ǰ̨�ǿ�У����ʾ�޸������");
			return "personalcenter";
		}
		
		//��������֧������ҳ�淵��modifyuser_paypassword��Ϊ��ʶ
		@RequestMapping("/modifypaypasswordpage")
		public String setpaymessage(HttpServletRequest req,HttpServletResponse res){
			String userid =(String)req.getSession().getAttribute("userid");
			User user = userService.selectByUserId(userid);
			//�û���һ������֧������,����ʾ����ԭ����������,��exists����ʶ
			if(user.getPaypassword()==null||(user.getPaypassword()).equals("")){//����֧������Ϊ��
				req.setAttribute("modifyuser_paypassword","����ǰ̨�ǿ�У����ʾ�޸�֧�������");
				req.setAttribute("noexists","����ǰ̨��ʾ����ԭ֧������������ʶ");
				return "personalcenter";
			}
			req.setAttribute("modifyuser_paypassword","����ǰ̨�ǿ�У����ʾ�޸�֧�������");
			return "personalcenter";
		}
		
		//�޸�֧������
		@RequestMapping(value="/modifypaypassword")
		public String modifypaypassword(HttpServletRequest req,HttpServletResponse res,
				String oldpaypassword,String newpaypassword){
					String userid =(String)req.getSession().getAttribute("userid");
					User user = userService.selectByUserId(userid);
					Integer i=null;
					if(oldpaypassword==null){//�ж��û��Ƿ��ǵ�һ������֧������
						 //�����ȷ�Ϳ�ʼ�޸�֧���������
						 try {
							 i = userService.updatepaypasswordByID(userid, newpaypassword);
						} catch (Exception e) {
							e.printStackTrace();
						}
						 if(i==1){
								req.setAttribute("success","*�޸ĳɹ�!");
							}else{
								req.setAttribute("error_2","*�޸�ʧ��!");
								req.setAttribute("noexists","����ǰ̨��ʾ����ԭ֧������������ʶ");
							}
					}else{
						//У��ԭ֧�������Ƿ���ȷ
						 if(user.getPaypassword().equals(oldpaypassword)){
							 //�����ȷ�Ϳ�ʼ�޸�֧���������
							 try {
								 i = userService.updatepaypasswordByID(userid, newpaypassword);
							} catch (Exception e) {
								e.printStackTrace();
							}
							 if(i==1){
									req.setAttribute("success","*�޸ĳɹ�!");
								}else{
									req.setAttribute("error_2","*�޸�ʧ��!");
								}
							 
						 }else{
							 req.setAttribute("error_1","*��������ȷ��֧������!");
						}
					}
					
					 req.setAttribute("modifyuser_paypassword","����ǰ̨�ǿ�У����ʾ�޸�֧�������");
					return "personalcenter";
				}
		
		
		
}
