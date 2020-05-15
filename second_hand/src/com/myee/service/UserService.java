package com.myee.service;

import com.myee.pojo.User;

public interface UserService {
	//通过账号和密码查询(登陆验证)
	public User selectByUserNameAndPassword(String userName,String password);
	//通过账号查询(验证注册账户是否已存在)
	public User selectByUserName(String userName);
	//插入新用户
	public Integer insertNewUser(User user);
	//根据session中的usetid查询用户信息
	public User selectByUserId(String userid);
	//根据userid修改邮箱,手机号,收货地址
	public Integer updatePartialMessage(User user);
	//根据userid修改密码
	public Integer updatepasswordByID(String userid,String password);
	//根据userid修改支付密码
	public Integer updatepaypasswordByID(String userid,String paypassword);
}
