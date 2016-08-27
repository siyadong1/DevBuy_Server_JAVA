package com.dev4free.devbuy.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.HomePagesMapper;
import com.dev4free.devbuy.po.HomePages;
import com.dev4free.devbuy.service.HomePagesService;

public class HomePagesServiceImpl implements HomePagesService {

	@Autowired
	HomePagesMapper homePagesMapper;
	
	/**
	 * 根据info_name查询HomePagesInfo
	 * @param info_name
	 * @return
	 */
	public HomePages findHomePagesInfoByInfoName(String info_name){
		
		HomePages homePages = homePagesMapper.findHomePagesInfoByInfoName(info_name);
		return homePages;
	}
	
	/**
	 * 更新HomePagesInfo
	 * @param homePages
	 */
	public void updateHomePagesInfo(HomePages homePages){
		homePagesMapper.updateHomePagesInfo(homePages);
	}
	
	/**
	 * 插入HomePagesInfo
	 * @param homePages
	 */
	public void insertHomePagesInfo(HomePages homePages){
		homePagesMapper.insertHomePagesInfo(homePages);
	}

}
