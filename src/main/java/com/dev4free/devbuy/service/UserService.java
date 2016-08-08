package com.dev4free.devbuy.service;


import com.dev4free.devbuy.po.User;

public interface UserService {
	

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username);
	
	
	/**
	 * 根据user对象中，属性值不为0的属性进行查找
	 * @param user
	 * @return
	 */
	public User findUserByUser(User user);
	
	
	/**
	 * 插入用户
	 * @param user
	 */
	public void insertUser(User user);
	
	
	/**
	 * 更新用户
	 * @param user
	 */
	public void updateUser(User user);
	
}
