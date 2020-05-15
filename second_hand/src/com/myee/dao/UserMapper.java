package com.myee.dao;

import org.apache.ibatis.annotations.Param;

import com.myee.pojo.User;

public interface UserMapper {
	//ͨ���˺ź������ѯ(��½��֤)
	public User selectByUserNameAndPassword(@Param("username")String userName,@Param("password")String password);
	//ͨ���˺Ų�ѯ(��֤ע���˻��Ƿ��Ѵ���)
	public User selectByUserName(@Param("username")String userName);
	//�������û�
	public Integer insertNewUser(User user);
	//����session�е�usetid��ѯ�û���Ϣ
	public User selectByUserId(@Param("ID")String userid);
	//����userid�޸�����,�ֻ���,�ջ���ַ
	public Integer updatePartialMessage(User user);
	//����userid�޸�����
	public Integer updatepasswordByID(@Param("ID")String userid,@Param("password")String password);
	//����userid�޸�֧������
	public Integer updatepaypasswordByID(@Param("ID")String userid,@Param("paypassword")String paypassword);
}


