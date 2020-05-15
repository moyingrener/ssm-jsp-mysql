package com.myee.service;

import java.util.List;

import com.myee.pojo.Order;
import com.myee.pojo.OrderCustom;


public interface OrderService {
	//通过username查询名下所有订单中商品id
	public List<String> selectOrderGoodsIDByUserName(String username);
	
	//通过商品id集合查询商品信息,及订单信息
	public List<OrderCustom> selectAllGoodsAndOrderByGoodsIdList(List<String> goodsIdList);
	
	//通过商品id,用户名,时间,收货地址产生新订单
	public Integer insertNewOrder(Order order);
}
