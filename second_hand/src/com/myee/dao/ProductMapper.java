package com.myee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myee.pojo.Product;

public interface ProductMapper {
	//插入(上架)新商品信息
	public Integer insertNewProduct(Product product);
	//通过商品ID删除(下架)商品
	public Integer deleteProductById(@Param("ID")String productid);
	//通过商品ID更新商品信息
	public Integer updateProductByProductId(Product product);
	//查询商品信息(可多条件)主要用于商品导航栏通过商品类型查询(分页属性在Product对象里)
	public List<Product> selectAllGoodsByAllMessage(Product product);
	//多条件查询商品信息记录数,主要用于商品导航栏通过商品类型查询
	public Integer selectAllGoodsCountsByAllMessage(Product product);
	//通过商品id集合查询商品信息
	public List<Product> selectAllGoodsByGoodsIdList(List<String> goodsIdList);
	//通过商品描述,页数,每页的大小查询商品(搜索框)
	public List<Product> selectAllGoodsByInputText(@Param("goodsdescription")String goodsdescription
			,@Param("state")String state,@Param("from")Integer from,@Param("pagesize")Integer pagesize);
	//通过商品描述获取记录条数,用于分页(搜索框)
	public Integer selectAllGoodsCountsByInputText(@Param("goodsdescription")String goodsdescription,@Param("state")String state);
	
	
	
}
