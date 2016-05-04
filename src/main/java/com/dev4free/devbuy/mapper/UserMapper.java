package com.dev4free.devbuy.mapper;

import com.dev4free.devbuy.po.User;

public interface UserMapper {

	/**
	 * 根据id找用户
	 * @param id
	 * @return
	 */
	public User findUserById(Integer id);
	
	
	
	
	public void insertUser(User user);
}
