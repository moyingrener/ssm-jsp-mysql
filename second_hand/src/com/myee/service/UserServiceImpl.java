package com.myee.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.myee.dao.UserMapper;
import com.myee.pojo.User;

public class UserServiceImpl implements UserService {
		@Autowired
		private UserMapper userMapper;

		@Override
		public User selectByUserNameAndPassword(String userName, String password) {
			return userMapper.selectByUserNameAndPassword(userName, password);
		}

		@Override
		public User selectByUserName(String userName) {
			return userMapper.selectByUserName(userName);
		}

		@Override
		public Integer insertNewUser(User user) {
			return userMapper.insertNewUser(user);
		}

		@Override
		public User selectByUserId(String userid) {
			return userMapper.selectByUserId(userid);
		}

		@Override
		public Integer updatePartialMessage(User user) {
			return userMapper.updatePartialMessage(user);
		}

		@Override
		public Integer updatepasswordByID(String userid, String password) {
			return userMapper.updatepasswordByID(userid, password);
		}

		@Override
		public Integer updatepaypasswordByID(String userid, String paypassword) {
			return userMapper.updatepaypasswordByID(userid, paypassword);
		}


}
