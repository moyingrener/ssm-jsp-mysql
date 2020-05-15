package com.myee.dao;

import org.apache.ibatis.annotations.Param;

import com.myee.pojo.User;

public interface UserMapper {
	//通过账号和密码查询(登陆验证)
	public User selectByUserNameAndPassword(@Param("username")String userName,@Param("password")String password);
	//通过账号查询(验证注册账户是否已存在)
	public User selectByUserName(@Param("username")String userName);
	//插入新用户
	public Integer insertNewUser(User user);
	//根据session中的usetid查询用户信息
	public User selectByUserId(@Param("ID")String userid);
	//根据userid修改邮箱,手机号,收货地址
	public Integer updatePartialMessage(User user);
	//根据userid修改密码
	public Integer updatepasswordByID(@Param("ID")String userid,@Param("password")String password);
	//根据userid修改支付密码
	public Integer updatepaypasswordByID(@Param("ID")String userid,@Param("paypassword")String paypassword);
}


