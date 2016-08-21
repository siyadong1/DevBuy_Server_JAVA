package com.dev4free.devbuy.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.ItemsMapper;
import com.dev4free.devbuy.po.Items;
import com.dev4free.devbuy.service.ItemsService;


public class ItemsServiceImpl implements ItemsService{

	@Autowired
	ItemsMapper itemsMapper;
	
	/**
	 * 插入商品信息
	 * @param items
	 */
	public void insertItems(Items items) {

		itemsMapper.insertItems(items);
		
	}

}
