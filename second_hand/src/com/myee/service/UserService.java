package com.myee.service;

import com.myee.pojo.User;

public interface UserService {
	//ͨ���˺ź������ѯ(��½��֤)
	public User selectByUserNameAndPassword(String userName,String password);
	//ͨ���˺Ų�ѯ(��֤ע���˻��Ƿ��Ѵ���)
	public User selectByUserName(String userName);
	//�������û�
	public Integer insertNewUser(User user);
	//����session�е�usetid��ѯ�û���Ϣ
	public User selectByUserId(String userid);
	//����userid�޸�����,�ֻ���,�ջ���ַ
	public Integer updatePartialMessage(User user);
	//����userid�޸�����
	public Integer updatepasswordByID(String userid,String password);
	//����userid�޸�֧������
	public Integer updatepaypasswordByID(String userid,String paypassword);
}
