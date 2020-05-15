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
//��Ҫ������/�¼���Ʒ�������Ĵ���
public class FileUpAndDownController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/fileUp",method=RequestMethod.POST)//�ϴ���Ʒ��Ϣ
	public String fileUp(Product product,HttpServletRequest req,HttpServletResponse res,MultipartFile attach){
		if(attach==null||attach.getSize()==0){//�ǿռ��
			req.setAttribute("error","*ͼƬ����Ϊ��!");
			return "productUpLoad";
		}else if(attach.getSize()>5120000){//����ļ���С
			req.setAttribute("error","*�ϴ�ͼƬ���ܳ���5MB");
			return "productUpLoad";
		}
		else{
			//��ȡ�ļ���
			String oldName =attach.getOriginalFilename();
			//��ȡ�ļ���׺��
			String suffix = FilenameUtils.getExtension(oldName);
		if(!(suffix.equalsIgnoreCase("jpg")||
				   suffix.equalsIgnoreCase("png")||
				   suffix.equalsIgnoreCase("jpeg")||
				   suffix.equalsIgnoreCase("pneg"))
			){req.setAttribute("error","*��ѡ���ʽΪjpg,png,jpeg,pneg��ͼƬ");
			return "productUpLoad";
		}else{
			//��ȡ���ش洢·��
			String path ="F://�ļ��ϴ����ݿ�";
			//������ͼƬ������ȡ�û�ID
			UUID randomUUID = UUID.randomUUID();
			String userId=null;
			userId = (String)req.getSession().getAttribute("userid");
			String newName=System.currentTimeMillis()+userId+randomUUID.toString()+"."+suffix;//ͼƬ��=ʱ���+�����+�û�id
			//�ڱ������ô洢�ռ�
			File file=new File(path,newName);
			if(!file.exists()){//���粻�����򴴽�һ��
				file.mkdirs();
			}
			//��product������Ʒid,�û�id,�ϼ�ʱ��,����״̬,ͼƬ·��
			if(userId!=null){
				String id=System.currentTimeMillis()+randomUUID.toString()+userId;//��Ʒid=�����+ʱ���+�û�id
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				product.setUserid(userId);
				product.setID(id);
				product.setShelftime(date);
				product.setGoodspic(newName);
				product.setState("δ����");
				try {
					productService.insertNewProduct(product);
					attach.transferTo(file);
					req.setAttribute("success","*�ϴ��ɹ�!");
					return "productUpLoad";
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			 req.setAttribute("error","*�ϴ�ʧ��!");
			 return "productUpLoad";
			
		}
	}
}
	
	
	//�¼���Ʒ     product�������û�id����ѯ�û��ϼܵ���Ʒ
	//product_1������Ҫɾ������Ʒid����ѯ��Ʒ��Ϣ,�ж���Ʒ�Ƿ�������(������/δ����)
	@RequestMapping(value="/lowershelf",method=RequestMethod.POST)//�ϴ���Ʒ��Ϣ
	public String lowershelf(HttpServletRequest req,HttpServletResponse res,
			@ModelAttribute("product")Product product,@ModelAttribute("product_1")Product product_1,String productid){
		if(productid!=null){//��Ʒid�ǿ�У��
			//������Ʒ�Ƿ��ѳ���
			product_1.setID(productid);//������Ʒid
			List<Product> theProductMessage = productService.selectAllGoodsByAllMessage(product_1);
			if(((Product)theProductMessage.toArray()[0]).getState().equals("������")){//������Ʒ������
				req.setAttribute("deletemessage",2);//ǰ̨��ʾ��Ʒ��������ʶ
			}else {//������Ʒδ����
				Integer i=null;
				try {
					 i = productService.deleteProductById(productid);//��ʼɾ������
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(i==1){//����1˵��ɾ���ɹ�
					req.setAttribute("deletemessage",1);//ǰ̨��ʾɾ���ɹ���ʶ
				}else {
					req.setAttribute("deletemessage",0);//ǰ̨��ʾɾ��ʧ�ܱ�ʶ
				}
			}

		}else {
			req.setAttribute("deletemessage",0);//ǰ̨��ʾɾ��ʧ�ܱ�ʶ
		}
		
		String userid =(String)req.getSession().getAttribute("userid");//��ȡ�û�id
		product.setUserid(userid);
		List<Product> productList=null;
		try {
			productList = productService.selectAllGoodsByAllMessage(product);//ͨ���û�id��ѯ���û��ϼܵ���Ʒ
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(productList.size()!=0){
			req.setAttribute("myupproductlist", productList);//�����û��ϼܵ���Ʒ��ǰ̨
		}else{
			req.setAttribute("myupproductlist_1","ǰ̨������ʾ�տ���Ҳ");
		}
		
		return "personalcenter";
		
}
	
	
	//�鿴�ļ�
	@RequestMapping("/lookpicture")
	public void lookpicture(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String filename = req.getParameter("filename");//��ȡ���������ļ���
		ServletOutputStream outputStream = res.getOutputStream();//��ȡ�ļ������
		//��ȡ�ļ�������
		InputStream inputStream=new FileInputStream(req.getServletContext().getRealPath("/statics/pic/")+File.separator+filename);
		int len=0;
		byte[] buffer=new byte[1024];
		while((len=inputStream.read(buffer))>0){//��ȡ�ļ������
			outputStream.write(buffer,0, len);
		}
		inputStream.close();
		outputStream.close();
	}
	
	@RequestMapping("/fileDown")//�����ļ�
	public void fileDown(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String filename = req.getParameter("filename");//��ȡ���������ļ���
	//filename=new String(filename.getBytes("ISO-8859-1"),"UTF-8");//���get����,���±���,�ٽ���(���tomcatû����URLEncoding�������)
		res.setCharacterEncoding("UTF-8");//ָ����������Ӧ�ı���
		ServletOutputStream outputStream = res.getOutputStream();//��ȡ�ļ������
		//��ȡ�ļ�������,���ӷ������϶�ȡ�ļ�
		InputStream inputStream=new FileInputStream(req.getServletContext().getRealPath("/statics/pic/")+File.separator+filename);		
		res.setContentType(req.getServletContext().getMimeType(filename));//��������ͷ��MIME����,������Դ������ļ�����
		res.setHeader("Content-Disposition","attactment;filename="+URLEncoder.encode(filename, "utf-8"));//������Ҫ�ļ�����
		int len=0;
		byte[] buffer=new byte[1024];
		while((len=inputStream.read(buffer))>0){//��ȡ�ļ������
			outputStream.write(buffer,0, len);
		}
		inputStream.close();//�ر���
		outputStream.close();
	}

}
