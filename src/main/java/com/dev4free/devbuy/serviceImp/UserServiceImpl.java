package com.dev4free.devbuy.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.UserMapper;
import com.dev4free.devbuy.po.User;
import com.dev4free.devbuy.service.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		
		User user = userMapper.findUserById(id);
		
		return user;
	}

}
