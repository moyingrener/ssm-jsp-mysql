package com.myee.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myee.pojo.Order;
import com.myee.pojo.Product;
import com.myee.pojo.User;
import com.myee.service.OrderService;
import com.myee.service.ProductService;
import com.myee.service.UserService;
//���ڴ�����Ʒ֧��
@Controller
@RequestMapping("/pay")
public class PayController {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	
	//������Ʒ������дҳ��,��������product����,�����ֱ���ѯ����
	@RequestMapping("/payforproduct")
	public String payForProduct(@ModelAttribute("product_1")Product product,@ModelAttribute("product_2")Product product_2,HttpServletRequest req,HttpServletResponse res,String ID){
		//������Ʒ������,����һ����ʶȥǰ̨,��ʾ��Ʒ������(�û��ڸ�������,�ҵĶ���,�����Ʒ��Ϣ,�ֵ��˹���)
		try {
			product.setState("δ����");
			List<Product> productlist = productService.selectAllGoodsByAllMessage(product);
		if(productlist.size()>0){//��Ʒ����
				req.setAttribute("productid",ID);//����ƷID
				return "pay";//����֧��ҳ��
			}else {
				try {
					//�����Ʒ�����ڻ�������,���ص�����Ʒ��Ϣҳ�沢��ʾ��Ʒ��������ʾ
					List<Product> theProductMessage = productService.selectAllGoodsByAllMessage(product_2);
					req.setAttribute("theproductmessage",(Product)theProductMessage.toArray()[0]);//���ؼ���,�������ȡһ������ֵ
					req.setAttribute("alreadysell",1);//��Ϊǰ̨��ʾ��Ʒ�������ı�ʶ
					return "forward:/oneProductMessage.jsp";//���ص�����Ʒ��Ϣҳ��
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:/index_1.jsp";
	
		
	}
	
	//����������Ʒ����(�����ջ���ַ��֧������)
	//user�����֧������,product�����Ҫ���µ���Ʒ��Ϣ,����productidΪ��Ʒid,����addressΪ�ջ���ַ
	@RequestMapping("/createorder")
	public String createorder(HttpServletRequest req,HttpServletResponse res,@ModelAttribute("Order")Order order,
			@ModelAttribute("product")Product product,String productid,String paypassword,String address){
		String userid =(String)req.getSession().getAttribute("userid");//��ȡ�û�id(���ظ�)
		String username=(String)req.getSession().getAttribute("username");//��ȡ�û���(���ظ�)
		User user = userService.selectByUserId(userid);//��ȡ�û�������Ϣ
		//У��֧������
		if(user.getPaypassword()==null){//�����û�δ����֧������,�����û�����֧������
			req.setAttribute("productid",productid);//����ƷID
			return "pay";//����֧��ҳ��
		}else if((user.getPaypassword()).equals(paypassword)){//��֤֧�������Ƿ���ȷ
			//��ʼ������
			Integer i=null;
			Integer j=null;
			UUID randomUUID = UUID.randomUUID();
			String id=randomUUID.toString()+System.currentTimeMillis();//����id=�����+ʱ���
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			order.setID(id);//���ö�����Ϣ
			order.setProductid(productid);
			order.setUsername(username);
			order.setCreatetime(date);
			
			product.setID(productid);
			product.setState("������");//������Ʒ����״̬
			try {
				j=productService.updateProductByProductId(product);//������Ʒ��Ϣ
				i = orderService.insertNewOrder(order);//��������
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(i==1&&j==1){
				return "orderSuccess";//���ض����ύ�ɹ�ҳ��
			}else{
				req.setAttribute("productid",productid);//����Ʒid
				req.setAttribute("fail","*�����ύʧ��,�������ύ!");
				return "pay";
			}
		}else{
			req.setAttribute("productid",productid);//����Ʒid
			req.setAttribute("paypassworderror","*֧���������!");//��֧�����������Ϣ
			return "pay";
		}
	}

}
