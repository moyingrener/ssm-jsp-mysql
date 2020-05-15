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
	
	
	//������Ʒ�ϴ�ҳ��
	@RequestMapping(value="/productUpLoad")
	public String productUpLoad(){
		return "productUpLoad";
	}
	
	//����ҳĬ�ϲ�ѯ����Ʒ��Ϣ(��Ʒ������ͨ����Ʒ���Ͳ�ѯ)
	@RequestMapping("/defaultMessage")
	public String defaultMessage(HttpServletRequest req,HttpServletResponse res,Product product,
			String selectmessage,Integer from,String goodstype){
		product.setState("δ����");//����δ��������
		Integer count=null;//��Ʒ��¼������
		List<Product> productList=null;
			goodstype=selectmessage;//selectmessage��ֵ����Ʒ����
			product.setGoodstype(goodstype);//������Ʒ����
			product.setPagesize(4);//���õ�ҳ����¼��
			try {
				//��ѯ��Ʒ��¼��
				 count = productService.selectAllGoodsCountsByAllMessage(product);
				 int len=count/4;
				 if(count>0&&count!=null){//������Ʒ��¼������0
					//��ѯ��Ʒ��Ϣ
					 if(from!=null){//ҳ������0(һ���Ƿ�ҳ��ť������from,ͨ����ҳ��ť���ô˿�����)
						 if(from>=len+1){//����������ڵ������ҳ��,ʹ�����ҳ����ѯ
							 Integer a=len*4;
							 product.setFrom(a);//���÷�ҳ���
							 productList = productService.selectAllGoodsByAllMessage(product);
							 from=len+1;//fromԽ���λ
						 }else if(from>1){//��������С�����ҳ��������1,ʹ�ü�����ҳ����ѯ
							 Integer a=(from-1)*4;
							 product.setFrom(a);
							 productList = productService.selectAllGoodsByAllMessage(product);
						 }
						 else{//��������С��1,ʹ����Сҳ����ѯ
							 product.setFrom(0);
							 productList = productService.selectAllGoodsByAllMessage(product);
							 from=1;
						 }
					 }else{//ҳ��Ϊ��ʱ,ʹ����Сҳ����ѯ
						 product.setFrom(0);
						 productList = productService.selectAllGoodsByAllMessage(product);
						 from=1;//fromԽ���λ
					 }
					 //����һ����������ǰ̨������ʾҳ����ť
						if(len==0){
							 Integer[] pageArray=new Integer[1];
							 Arrays.fill(pageArray,0);
							 req.setAttribute("pageArray",pageArray);
						}else if(len>=1){
							 int[] pageArray=new int[len+1];
							 Arrays.fill(pageArray,0);
							 req.setAttribute("pageArray",pageArray);
						}
					//�鵽��Ʒ,������ҳ
						 req.setAttribute("productlist_1",productList);//��Ʒ��Ϣ��ʶ
						 req.setAttribute("currentpage",from);//��ǰҳ��ʶ
						 req.setAttribute("pagecount_1",count);//�ܹ��ļ�¼����ʶ,�ж���ʾ��ҳ�ı�ʶ
						 req.setAttribute("selectmessage",selectmessage);//���ò�ѯ��Ϣ���ڻ���,��ҳ��ѯ
						 return "forward:/index_1.jsp";
				 }else{
					 req.setAttribute("productlist_null",1);//��¼Ϊ0,δ��ѯ����Ʒ,ǰ̨�ı�ʶ
					 req.setAttribute("nogoods","û���˳��۸���ƷŶ");
					 req.setAttribute("selectmessage",selectmessage);//���ò�ѯ��Ϣ���ڻ���,��ҳ��ѯ
					 return "forward:/index_1.jsp";
				 }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		//����Ϊ��,������ҳ
		req.setAttribute("productlist_null",1);//δ��ѯ����Ʒ,ǰ̨�ı�ʶ
		req.setAttribute("nogoods","û���˳��۸���ƷŶ");
		req.setAttribute("selectmessage",selectmessage);//��ѯ��Ϣ����
		return "forward:/index_1.jsp";
		
	}
	
	//�������Ĳ�ѯ�û��ϼܵ���Ʒ
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
			req.setAttribute("myupproductlist_1","ǰ̨������ʾ�տ���Ҳ");
		}
		return "personalcenter";
		
	}
	
	//�������Ĳ�ѯ�û�������Ϣ
		@RequestMapping("/myorderlist")
		public String myorderlist(@ModelAttribute("product_1")Product product,HttpServletRequest req,HttpServletResponse res){
			String username =(String)req.getSession().getAttribute("username");
			//ͨ��username��ѯ��������Ʒid����
			List<String> goodsIdList=null;
			 goodsIdList = orderService.selectOrderGoodsIDByUserName(username);
			 if(goodsIdList.size()!=0){//����û����ڶ���,���ݶ�������Ʒid��ѯ��Ʒ��Ϣ
				 //ͨ����Ʒid��ѯ��Ʒ��Ϣ
				 List<OrderCustom> myorderlist = orderService.selectAllGoodsAndOrderByGoodsIdList(goodsIdList);
				 req.setAttribute("myorderlist", myorderlist);
			 }else{
					req.setAttribute("myorderlist_1","ǰ̨������ʾ�տ���Ҳ");
				}
			return "personalcenter";
			
		}
		
	//������ģ����ѯ��Ʒ��Ϣ,��ҳ��ťҲ�����������
		@RequestMapping("/inputtextselectgoods")
		public String inputtextselectgoods(HttpServletRequest req,HttpServletResponse res,
				String selectmessage,Integer from){
			if(selectmessage!=null&&!selectmessage.equals("")){//��ѯ��Ϣ�ǿ�У��
				Integer count=null;//��Ʒ��¼������
				String state="δ����";
				List<Product> productList=null;
				try {
					//��ѯ��Ʒ��¼��
					 count = productService.selectAllGoodsCountsByInputText(selectmessage,state);
					 int len=count/4;
					 if(count>0&&count!=null){//������Ʒ��¼������0
						//��ѯ��Ʒ��Ϣ
						 if(from!=null){//ҳ������0(һ���Ƿ�ҳ��ť������from,ͨ����ҳ��ť���ô˿�����)
							 if(from>=len+1){//����������ڵ������ҳ��,ʹ�����ҳ����ѯ
								 productList = productService.selectAllGoodsByInputText(selectmessage,state,len*4,4);
								 from=len+1;//fromԽ���λ
							 }else if(from>1){//��������С�����ҳ��������1,ʹ�ü�����ҳ����ѯ
								 productList = productService.selectAllGoodsByInputText(selectmessage,state,(from-1)*4,4);
							 }
							 else{//��������С��1,ʹ����Сҳ����ѯ
								 productList = productService.selectAllGoodsByInputText(selectmessage,state,0,4);
							 }
						 }else{//ҳ��Ϊ��ʱ,ʹ����Сҳ����ѯ(һ��������������from,ͨ����������ô˿�����)
							 productList = productService.selectAllGoodsByInputText(selectmessage,state,0,4); 
							 from=1;//fromԽ���λ
						 }
						 //����һ����������ǰ̨������ʾҳ����ť
							if(len==0){
								 Integer[] pageArray=new Integer[1];
								 Arrays.fill(pageArray,0);
								 req.setAttribute("pageArray",pageArray);
							}else if(len>=1){
								 int[] pageArray=new int[len+1];
								 Arrays.fill(pageArray,0);
								 req.setAttribute("pageArray",pageArray);
							}
						//�鵽��Ʒ,������ҳ
							 req.setAttribute("productlist",productList);//��Ʒ��Ϣ��ʶ
							 req.setAttribute("currentpage",from);//��ǰҳ��ʶ
							 req.setAttribute("pagecount",count);//�ܹ��ļ�¼����ʶ,�ж���ʾ��ҳ�ı�ʶ
							 req.setAttribute("selectmessage",selectmessage);//���ò�ѯ��Ϣ���ڻ���,��ҳ��ѯ
							 return "forward:/index_1.jsp";
					 }else{
						 req.setAttribute("productlist_null",1);//��¼Ϊ0,δ��ѯ����Ʒ,ǰ̨�ı�ʶ
						 req.setAttribute("nogoods","û���˳��۸���ƷŶ");
						 req.setAttribute("selectmessage",selectmessage);//���ò�ѯ��Ϣ���ڻ���,��ҳ��ѯ
					 }
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//����Ϊ��,������ҳ
			req.setAttribute("productlist_null",1);//δ��ѯ����Ʒ,ǰ̨�ı�ʶ
			req.setAttribute("nogoods","û���˳��۸���ƷŶ");
			req.setAttribute("selectmessage",selectmessage);//��ѯ��Ϣ����
			return "forward:/index_1.jsp";
			
		}
		
		//������ƷID��ѯ��������ʾ������Ʒ��Ϣ
		@RequestMapping("/theproductmessage")
		public String theproductmessage(HttpServletRequest req,HttpServletResponse res,Product product){
			try {
				List<Product> theProductMessage = productService.selectAllGoodsByAllMessage(product);
				req.setAttribute("theproductmessage",(Product)theProductMessage.toArray()[0]);//���ؼ���,�������ȡһ������ֵ
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "forward:/oneProductMessage.jsp";
		}
		
		
}




	
	

	
	
	
