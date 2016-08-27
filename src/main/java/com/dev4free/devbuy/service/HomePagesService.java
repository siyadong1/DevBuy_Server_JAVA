package com.dev4free.devbuy.service;

import com.dev4free.devbuy.po.HomePages;

public interface HomePagesService {

	/**
	 * 根据info_name查询HomePagesInfo
	 * @param info_name
	 * @return
	 */
	public HomePages findHomePagesInfoByInfoName(String info_name);
	
	/**
	 * 更新HomePagesInfo
	 * @param homePages
	 */
	public void updateHomePagesInfo(HomePages homePages);
	
	/**
	 * 插入HomePagesInfo
	 * @param homePages
	 */
	public void insertHomePagesInfo(HomePages homePages);
}
