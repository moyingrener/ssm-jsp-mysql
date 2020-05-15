package com.myee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myee.pojo.Product;

public interface ProductMapper {
	//����(�ϼ�)����Ʒ��Ϣ
	public Integer insertNewProduct(Product product);
	//ͨ����ƷIDɾ��(�¼�)��Ʒ
	public Integer deleteProductById(@Param("ID")String productid);
	//ͨ����ƷID������Ʒ��Ϣ
	public Integer updateProductByProductId(Product product);
	//��ѯ��Ʒ��Ϣ(�ɶ�����)��Ҫ������Ʒ������ͨ����Ʒ���Ͳ�ѯ(��ҳ������Product������)
	public List<Product> selectAllGoodsByAllMessage(Product product);
	//��������ѯ��Ʒ��Ϣ��¼��,��Ҫ������Ʒ������ͨ����Ʒ���Ͳ�ѯ
	public Integer selectAllGoodsCountsByAllMessage(Product product);
	//ͨ����Ʒid���ϲ�ѯ��Ʒ��Ϣ
	public List<Product> selectAllGoodsByGoodsIdList(List<String> goodsIdList);
	//ͨ����Ʒ����,ҳ��,ÿҳ�Ĵ�С��ѯ��Ʒ(������)
	public List<Product> selectAllGoodsByInputText(@Param("goodsdescription")String goodsdescription
			,@Param("state")String state,@Param("from")Integer from,@Param("pagesize")Integer pagesize);
	//ͨ����Ʒ������ȡ��¼����,���ڷ�ҳ(������)
	public Integer selectAllGoodsCountsByInputText(@Param("goodsdescription")String goodsdescription,@Param("state")String state);
	
	
	
}
