package com.myee.service;

import java.util.List;


import com.myee.pojo.Product;

public interface ProductService {
	//��������Ʒ��Ϣ
	public Integer insertNewProduct(Product product);
	//ͨ����ƷIDɾ��(�¼�)��Ʒ
	public Integer deleteProductById(String productid);
	//ͨ����ƷID������Ʒ��Ϣ
	public Integer updateProductByProductId(Product product);
	//��ѯ��Ʒ��Ϣ(�ɶ�����)��Ҫ������Ʒ������ͨ����Ʒ���Ͳ�ѯ(��ҳ������Product������)
	public List<Product> selectAllGoodsByAllMessage(Product product);
	//��������ѯ��Ʒ��Ϣ��¼��,��Ҫ������Ʒ������ͨ����Ʒ���Ͳ�ѯ
	public Integer selectAllGoodsCountsByAllMessage(Product product);
	//ͨ����Ʒid���ϲ�ѯ��Ʒ��Ϣ
	public List<Product> selectAllGoodsByGoodsIdList(List<String> goodsIdList);
	//ͨ����Ʒ����,ҳ��,ÿҳ�Ĵ�С��ѯ��Ʒ(������)
	public List<Product>selectAllGoodsByInputText(String goodsdescription,String state,Integer from,Integer pagesize);
	//ͨ����Ʒ������ȡ��¼����,���ڷ�ҳ(������)
	public Integer selectAllGoodsCountsByInputText(String goodsdescription,String state);
}
