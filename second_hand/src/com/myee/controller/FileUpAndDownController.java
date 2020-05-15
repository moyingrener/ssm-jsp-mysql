package com.myee.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.myee.pojo.Product;
import com.myee.service.ProductService;

@Controller
@RequestMapping("/file")
//主要负责上/下架商品及订单的处理
public class FileUpAndDownController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/fileUp",method=RequestMethod.POST)//上传商品信息
	public String fileUp(Product product,HttpServletRequest req,HttpServletResponse res,MultipartFile attach){
		if(attach==null||attach.getSize()==0){//非空检测
			req.setAttribute("error","*图片不能为空!");
			return "productUpLoad";
		}else if(attach.getSize()>5120000){//检测文件大小
			req.setAttribute("error","*上传图片不能超过5MB");
			return "productUpLoad";
		}
		else{
			//获取文件名
			String oldName =attach.getOriginalFilename();
			//获取文件后缀名
			String suffix = FilenameUtils.getExtension(oldName);
		if(!(suffix.equalsIgnoreCase("jpg")||
				   suffix.equalsIgnoreCase("png")||
				   suffix.equalsIgnoreCase("jpeg")||
				   suffix.equalsIgnoreCase("pneg"))
			){req.setAttribute("error","*请选择格式为jpg,png,jpeg,pneg的图片");
			return "productUpLoad";
		}else{
			//获取本地存储路径
			String path ="F://文件上传数据库";
			//设置新图片名，获取用户ID
			UUID randomUUID = UUID.randomUUID();
			String userId=null;
			userId = (String)req.getSession().getAttribute("userid");
			String newName=System.currentTimeMillis()+userId+randomUUID.toString()+"."+suffix;//图片名=时间戳+随机数+用户id
			//在本地设置存储空间
			File file=new File(path,newName);
			if(!file.exists()){//假如不存在则创建一个
				file.mkdirs();
			}
			//向product存入商品id,用户id,上架时间,销售状态,图片路径
			if(userId!=null){
				String id=System.currentTimeMillis()+randomUUID.toString()+userId;//商品id=随机数+时间戳+用户id
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				product.setUserid(userId);
				product.setID(id);
				product.setShelftime(date);
				product.setGoodspic(newName);
				product.setState("未卖出");
				try {
					productService.insertNewProduct(product);
					attach.transferTo(file);
					req.setAttribute("success","*上传成功!");
					return "productUpLoad";
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			 req.setAttribute("error","*上传失败!");
			 return "productUpLoad";
			
		}
	}
}
	
	
	//下架商品     product用来存用户id并查询用户上架的商品
	//product_1用来存要删除的商品id并查询商品信息,判断商品是否已卖出(已卖出/未卖出)
	@RequestMapping(value="/lowershelf",method=RequestMethod.POST)//上传商品信息
	public String lowershelf(HttpServletRequest req,HttpServletResponse res,
			@ModelAttribute("product")Product product,@ModelAttribute("product_1")Product product_1,String productid){
		if(productid!=null){//商品id非空校验
			//检验商品是否已出售
			product_1.setID(productid);//放入商品id
			List<Product> theProductMessage = productService.selectAllGoodsByAllMessage(product_1);
			if(((Product)theProductMessage.toArray()[0]).getState().equals("已卖出")){//假如商品已卖出
				req.setAttribute("deletemessage",2);//前台显示商品已卖出标识
			}else {//假如商品未卖出
				Integer i=null;
				try {
					 i = productService.deleteProductById(productid);//开始删除操作
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(i==1){//返回1说明删除成功
					req.setAttribute("deletemessage",1);//前台显示删除成功标识
				}else {
					req.setAttribute("deletemessage",0);//前台显示删除失败标识
				}
			}

		}else {
			req.setAttribute("deletemessage",0);//前台显示删除失败标识
		}
		
		String userid =(String)req.getSession().getAttribute("userid");//获取用户id
		product.setUserid(userid);
		List<Product> productList=null;
		try {
			productList = productService.selectAllGoodsByAllMessage(product);//通过用户id查询该用户上架的商品
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(productList.size()!=0){
			req.setAttribute("myupproductlist", productList);//发送用户上架的商品到前台
		}else{
			req.setAttribute("myupproductlist_1","前台用于显示空空如也");
		}
		
		return "personalcenter";
		
}
	
	
	//查看文件
	@RequestMapping("/lookpicture")
	public void lookpicture(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String filename = req.getParameter("filename");//获取请求下载文件名
		ServletOutputStream outputStream = res.getOutputStream();//获取文件输出流
		//获取文件输入流
		InputStream inputStream=new FileInputStream(req.getServletContext().getRealPath("/statics/pic/")+File.separator+filename);
		int len=0;
		byte[] buffer=new byte[1024];
		while((len=inputStream.read(buffer))>0){//读取文件并输出
			outputStream.write(buffer,0, len);
		}
		inputStream.close();
		outputStream.close();
	}
	
	@RequestMapping("/fileDown")//下载文件
	public void fileDown(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String filename = req.getParameter("filename");//获取请求下载文件名
	//filename=new String(filename.getBytes("ISO-8859-1"),"UTF-8");//解决get乱码,重新编码,再解码(如果tomcat没设置URLEncoding就用这个)
		res.setCharacterEncoding("UTF-8");//指定服务器响应的编码
		ServletOutputStream outputStream = res.getOutputStream();//获取文件输出流
		//获取文件输入流,并从服务器上读取文件
		InputStream inputStream=new FileInputStream(req.getServletContext().getRealPath("/statics/pic/")+File.separator+filename);		
		res.setContentType(req.getServletContext().getMimeType(filename));//设置请求头的MIME类型,浏览器以此区分文件类型
		res.setHeader("Content-Disposition","attactment;filename="+URLEncoder.encode(filename, "utf-8"));//设置需要文件下载
		int len=0;
		byte[] buffer=new byte[1024];
		while((len=inputStream.read(buffer))>0){//读取文件并输出
			outputStream.write(buffer,0, len);
		}
		inputStream.close();//关闭流
		outputStream.close();
	}

}
