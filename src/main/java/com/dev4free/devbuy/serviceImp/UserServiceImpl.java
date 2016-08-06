package com.dev4free.devbuy.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.UserMapper;
import com.dev4free.devbuy.po.City;
import com.dev4free.devbuy.po.User;
import com.dev4free.devbuy.service.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */

	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		User user = userMapper.findUserByUsername(username);
		return user;
	}

	/**
	 * 根据user对象中，属性值不为0的属性进行查找
	 * @param user
	 * @return
	 */

	public User findUserByUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.findUserByUser(user);
	}
	
	
	
	/**
	 * 插入用户
	 * @param user
	 */
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userMapper.insertUser(user);
	}


	/**
	 * 更新用户
	 * @param user
	 */
	public void updateUser(User user){
		
		userMapper.updateUser(user);
		
	}
	
	/**
	 * 查询城市名称
	 * 
	 */
	public City modifycityname(String cityName) {
		
		City city = userMapper.modifycityname(cityName);
		
		return city;
	}


}
