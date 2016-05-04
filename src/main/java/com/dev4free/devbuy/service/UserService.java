package com.dev4free.devbuy.service;

import com.dev4free.devbuy.po.User;

public interface UserService {

	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	public User findUserById(Integer id);
	
	
	/**
	 * 插入用户
	 * @param user
	 */
	public void insertUser(User user);
}
