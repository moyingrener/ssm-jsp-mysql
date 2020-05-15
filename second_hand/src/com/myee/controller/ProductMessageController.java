package com.myee.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myee.pojo.OrderCustom;
import com.myee.pojo.Product;
import com.myee.service.OrderService;
import com.myee.service.ProductService;
@Controller
@RequestMapping("/product")
public class ProductMessageController {
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	
	
	//返回商品上传页面
	@RequestMapping(value="/productUpLoad")
	public String productUpLoad(){
		return "productUpLoad";
	}
	
	//打开首页默认查询的商品信息(商品导航栏通过商品类型查询)
	@RequestMapping("/defaultMessage")
	public String defaultMessage(HttpServletRequest req,HttpServletResponse res,Product product,
			String selectmessage,Integer from,String goodstype){
		product.setState("未卖出");//增加未卖出条件
		Integer count=null;//商品记录数索引
		List<Product> productList=null;
			goodstype=selectmessage;//selectmessage赋值给商品类型
			product.setGoodstype(goodstype);//设置商品类型
			product.setPagesize(4);//设置单页最大记录数
			try {
				//查询商品记录数
				 count = productService.selectAllGoodsCountsByAllMessage(product);
				 int len=count/4;
				 if(count>0&&count!=null){//假如商品记录数大于0
					//查询商品信息
					 if(from!=null){//页数大于0(一般是分页按钮传来的from,通过分页按钮调用此控制器)
						 if(from>=len+1){//假如请求大于等于最大页数,使用最大页数查询
							 Integer a=len*4;
							 product.setFrom(a);//设置分页起点
							 productList = productService.selectAllGoodsByAllMessage(product);
							 from=len+1;//from越界归位
						 }else if(from>1){//假如请求小于最大页数但大于1,使用计算后的页数查询
							 Integer a=(from-1)*4;
							 product.setFrom(a);
							 productList = productService.selectAllGoodsByAllMessage(product);
						 }
						 else{//假如请求小于1,使用最小页数查询
							 product.setFrom(0);
							 productList = productService.selectAllGoodsByAllMessage(product);
							 from=1;
						 }
					 }else{//页数为空时,使用最小页数查询
						 product.setFrom(0);
						 productList = productService.selectAllGoodsByAllMessage(product);
						 from=1;//from越界归位
					 }
					 //创建一个数组用于前台遍历显示页数按钮
						if(len==0){
							 Integer[] pageArray=new Integer[1];
							 Arrays.fill(pageArray,0);
							 req.setAttribute("pageArray",pageArray);
						}else if(len>=1){
							 int[] pageArray=new int[len+1];
							 Arrays.fill(pageArray,0);
							 req.setAttribute("pageArray",pageArray);
						}
					//查到商品,返回首页
						 req.setAttribute("productlist_1",productList);//商品信息标识
						 req.setAttribute("currentpage",from);//当前页标识
						 req.setAttribute("pagecount_1",count);//总共的记录数标识,判断显示分页的标识
						 req.setAttribute("selectmessage",selectmessage);//设置查询信息用于回显,分页查询
						 return "forward:/index_1.jsp";
				 }else{
					 req.setAttribute("productlist_null",1);//记录为0,未查询到商品,前台的标识
					 req.setAttribute("nogoods","没有人出售该商品哦");
					 req.setAttribute("selectmessage",selectmessage);//设置查询信息用于回显,分页查询
					 return "forward:/index_1.jsp";
				 }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		//输入为空,返回首页
		req.setAttribute("productlist_null",1);//未查询到商品,前台的标识
		req.setAttribute("nogoods","没有人出售该商品哦");
		req.setAttribute("selectmessage",selectmessage);//查询信息回显
		return "forward:/index_1.jsp";
		
	}
	
	//个人中心查询用户上架的商品
	@RequestMapping("/myupproduct")
	public String myupproduct(@ModelAttribute("product_1")Product product,
			HttpServletRequest req,HttpServletResponse res){
		String userid =(String)req.getSession().getAttribute("userid");
		product.setUserid(userid);
		List<Product> productList=null;
		try {
			productList = productService.selectAllGoodsByAllMessage(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(productList.size()!=0){
			req.setAttribute("myupproductlist", productList);
		}else{
			req.setAttribute("myupproductlist_1","前台用于显示空空如也");
		}
		return "personalcenter";
		
	}
	
	//个人中心查询用户订单信息
		@RequestMapping("/myorderlist")
		public String myorderlist(@ModelAttribute("product_1")Product product,HttpServletRequest req,HttpServletResponse res){
			String username =(String)req.getSession().getAttribute("username");
			//通过username查询订单的商品id集合
			List<String> goodsIdList=null;
			 goodsIdList = orderService.selectOrderGoodsIDByUserName(username);
			 if(goodsIdList.size()!=0){//如果用户存在订单,根据订单中商品id查询商品信息
				 //通过商品id查询商品信息
				 List<OrderCustom> myorderlist = orderService.selectAllGoodsAndOrderByGoodsIdList(goodsIdList);
				 req.setAttribute("myorderlist", myorderlist);
			 }else{
					req.setAttribute("myorderlist_1","前台用于显示空空如也");
				}
			return "personalcenter";
			
		}
		
	//搜索框模糊查询商品信息,分页按钮也用这个控制器
		@RequestMapping("/inputtextselectgoods")
		public String inputtextselectgoods(HttpServletRequest req,HttpServletResponse res,
				String selectmessage,Integer from){
			if(selectmessage!=null&&!selectmessage.equals("")){//查询信息非空校验
				Integer count=null;//商品记录数索引
				String state="未卖出";
				List<Product> productList=null;
				try {
					//查询商品记录数
					 count = productService.selectAllGoodsCountsByInputText(selectmessage,state);
					 int len=count/4;
					 if(count>0&&count!=null){//假如商品记录数大于0
						//查询商品信息
						 if(from!=null){//页数大于0(一般是分页按钮传来的from,通过分页按钮调用此控制器)
							 if(from>=len+1){//假如请求大于等于最大页数,使用最大页数查询
								 productList = productService.selectAllGoodsByInputText(selectmessage,state,len*4,4);
								 from=len+1;//from越界归位
							 }else if(from>1){//假如请求小于最大页数但大于1,使用计算后的页数查询
								 productList = productService.selectAllGoodsByInputText(selectmessage,state,(from-1)*4,4);
							 }
							 else{//假如请求小于1,使用最小页数查询
								 productList = productService.selectAllGoodsByInputText(selectmessage,state,0,4);
							 }
						 }else{//页数为空时,使用最小页数查询(一般是搜索框传来的from,通过搜索框调用此控制器)
							 productList = productService.selectAllGoodsByInputText(selectmessage,state,0,4); 
							 from=1;//from越界归位
						 }
						 //创建一个数组用于前台遍历显示页数按钮
							if(len==0){
								 Integer[] pageArray=new Integer[1];
								 Arrays.fill(pageArray,0);
								 req.setAttribute("pageArray",pageArray);
							}else if(len>=1){
								 int[] pageArray=new int[len+1];
								 Arrays.fill(pageArray,0);
								 req.setAttribute("pageArray",pageArray);
							}
						//查到商品,返回首页
							 req.setAttribute("productlist",productList);//商品信息标识
							 req.setAttribute("currentpage",from);//当前页标识
							 req.setAttribute("pagecount",count);//总共的记录数标识,判断显示分页的标识
							 req.setAttribute("selectmessage",selectmessage);//设置查询信息用于回显,分页查询
							 return "forward:/index_1.jsp";
					 }else{
						 req.setAttribute("productlist_null",1);//记录为0,未查询到商品,前台的标识
						 req.setAttribute("nogoods","没有人出售该商品哦");
						 req.setAttribute("selectmessage",selectmessage);//设置查询信息用于回显,分页查询
					 }
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//输入为空,返回首页
			req.setAttribute("productlist_null",1);//未查询到商品,前台的标识
			req.setAttribute("nogoods","没有人出售该商品哦");
			req.setAttribute("selectmessage",selectmessage);//查询信息回显
			return "forward:/index_1.jsp";
			
		}
		
		//根据商品ID查询并返回显示单个商品信息
		@RequestMapping("/theproductmessage")
		public String theproductmessage(HttpServletRequest req,HttpServletResponse res,Product product){
			try {
				List<Product> theProductMessage = productService.selectAllGoodsByAllMessage(product);
				req.setAttribute("theproductmessage",(Product)theProductMessage.toArray()[0]);//返回集合,变成数组取一号索引值
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "forward:/oneProductMessage.jsp";
		}
		
		
}




	
	

	
	
	
