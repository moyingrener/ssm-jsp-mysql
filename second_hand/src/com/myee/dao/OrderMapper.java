package com.myee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myee.pojo.Order;
import com.myee.pojo.OrderCustom;

public interface OrderMapper {
	//ͨ��username��ѯ�������ж�������Ʒid
	public List<String> selectOrderGoodsIDByUserName(@Param("username")String username);
	
	//ͨ����Ʒid���ϲ�ѯ��Ʒ��Ϣ,��������Ϣ
	public List<OrderCustom> selectAllGoodsAndOrderByGoodsIdList(List<String> goodsIdList);
	
	//ͨ����Ʒid,�û���,ʱ��,�ջ���ַ�����¶���
	public Integer insertNewOrder(Order order);
}
