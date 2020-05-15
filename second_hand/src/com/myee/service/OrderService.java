package com.myee.service;

import java.util.List;

import com.myee.pojo.Order;
import com.myee.pojo.OrderCustom;


public interface OrderService {
	//ͨ��username��ѯ�������ж�������Ʒid
	public List<String> selectOrderGoodsIDByUserName(String username);
	
	//ͨ����Ʒid���ϲ�ѯ��Ʒ��Ϣ,��������Ϣ
	public List<OrderCustom> selectAllGoodsAndOrderByGoodsIdList(List<String> goodsIdList);
	
	//ͨ����Ʒid,�û���,ʱ��,�ջ���ַ�����¶���
	public Integer insertNewOrder(Order order);
}
