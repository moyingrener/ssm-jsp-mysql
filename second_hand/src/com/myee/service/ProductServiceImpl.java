package com.myee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myee.dao.ProductMapper;
import com.myee.pojo.Product;

public class ProductServiceImpl implements ProductService {
	@Autowired
		private ProductMapper productMapper;
	@Override
	public Integer insertNewProduct(Product product) {
		return productMapper.insertNewProduct(product);
	}
	@Override
	public Integer deleteProductById(String productid) {
		return productMapper.deleteProductById(productid);
	}
	@Override
	public Integer updateProductByProductId(Product product) {
		return productMapper.updateProductByProductId(product);
	}
	@Override
	public List<Product> selectAllGoodsByAllMessage(Product product) {
		return productMapper.selectAllGoodsByAllMessage(product);
	}
	@Override
	public Integer selectAllGoodsCountsByAllMessage(Product product) {
		return productMapper.selectAllGoodsCountsByAllMessage(product);
	}
	@Override
	public List<Product> selectAllGoodsByGoodsIdList(List<String> goodsIdList) {
		return productMapper.selectAllGoodsByGoodsIdList(goodsIdList);
	}
	@Override
	public List<Product> selectAllGoodsByInputText(String goodsdescription,String state,Integer from,Integer pagesize) {
		return productMapper.selectAllGoodsByInputText(goodsdescription, state, from, pagesize);
	}
	@Override
	public Integer selectAllGoodsCountsByInputText(String goodsdescription,String state) {
		return productMapper.selectAllGoodsCountsByInputText(goodsdescription, state);
	}



}
