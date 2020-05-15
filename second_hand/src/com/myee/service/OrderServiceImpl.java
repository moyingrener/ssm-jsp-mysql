package com.myee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myee.dao.OrderMapper;
import com.myee.pojo.Order;
import com.myee.pojo.OrderCustom;

public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public List<String> selectOrderGoodsIDByUserName(String username) {
		return orderMapper.selectOrderGoodsIDByUserName(username);
	}
	
	@Override
	public List<OrderCustom> selectAllGoodsAndOrderByGoodsIdList(List<String> goodsIdList) {
		return orderMapper.selectAllGoodsAndOrderByGoodsIdList(goodsIdList);
	}

	@Override
	public Integer insertNewOrder(Order order) {
		return orderMapper.insertNewOrder(order);
	}

}
