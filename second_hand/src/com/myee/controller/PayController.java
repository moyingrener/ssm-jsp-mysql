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
//用于处理商品支付
@Controller
@RequestMapping("/pay")
public class PayController {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	
	//返回商品订单填写页面,创建两个product对象,用来分别存查询条件
	@RequestMapping("/payforproduct")
	public String payForProduct(@ModelAttribute("product_1")Product product,@ModelAttribute("product_2")Product product_2,HttpServletRequest req,HttpServletResponse res,String ID){
		//假如商品已卖出,发送一个标识去前台,显示商品已卖完(用户在个人中心,我的订单,点击商品信息,又点了购买)
		try {
			product.setState("未卖出");
			List<Product> productlist = productService.selectAllGoodsByAllMessage(product);
		if(productlist.size()>0){//商品存在
				req.setAttribute("productid",ID);//传商品ID
				return "pay";//返回支付页面
			}else {
				try {
					//如果商品不存在或已卖出,返回单个商品信息页面并显示商品已卖出提示
					List<Product> theProductMessage = productService.selectAllGoodsByAllMessage(product_2);
					req.setAttribute("theproductmessage",(Product)theProductMessage.toArray()[0]);//返回集合,变成数组取一号索引值
					req.setAttribute("alreadysell",1);//作为前台显示商品已卖出的标识
					return "forward:/oneProductMessage.jsp";//返回单个商品信息页面
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward:/index_1.jsp";
	
		
	}
	
	//处理并生成商品订单(接受收货地址和支付密码)
	//user对象存支付密码,product对象存要更新的商品信息,参数productid为商品id,参数address为收货地址
	@RequestMapping("/createorder")
	public String createorder(HttpServletRequest req,HttpServletResponse res,@ModelAttribute("Order")Order order,
			@ModelAttribute("product")Product product,String productid,String paypassword,String address){
		String userid =(String)req.getSession().getAttribute("userid");//获取用户id(不重复)
		String username=(String)req.getSession().getAttribute("username");//获取用户名(不重复)
		User user = userService.selectByUserId(userid);//获取用户所有信息
		//校验支付密码
		if(user.getPaypassword()==null){//假如用户未设置支付密码,告诉用户设置支付密码
			req.setAttribute("productid",productid);//传商品ID
			return "pay";//返回支付页面
		}else if((user.getPaypassword()).equals(paypassword)){//验证支付密码是否正确
			//开始处理订单
			Integer i=null;
			Integer j=null;
			UUID randomUUID = UUID.randomUUID();
			String id=randomUUID.toString()+System.currentTimeMillis();//订单id=随机数+时间戳
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			order.setID(id);//设置订单信息
			order.setProductid(productid);
			order.setUsername(username);
			order.setCreatetime(date);
			
			product.setID(productid);
			product.setState("已卖出");//更改商品出售状态
			try {
				j=productService.updateProductByProductId(product);//更新商品信息
				i = orderService.insertNewOrder(order);//产生订单
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(i==1&&j==1){
				return "orderSuccess";//返回订单提交成功页面
			}else{
				req.setAttribute("productid",productid);//传商品id
				req.setAttribute("fail","*订单提交失败,请重新提交!");
				return "pay";
			}
		}else{
			req.setAttribute("productid",productid);//传商品id
			req.setAttribute("paypassworderror","*支付密码错误!");//传支付密码错误信息
			return "pay";
		}
	}

}
