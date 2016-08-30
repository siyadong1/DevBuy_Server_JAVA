package com.dev4free.devbuy.serviceImp;

import java.util.ArrayList;

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


	/**
	 * 根据商品id查询商品信息详情
	 * @param items_id
	 */
	public Items findItemsByItemsId(String items_id) {
		
		Items items = itemsMapper.findItemsByItemsId(items_id);
		return items;
	}

	
	/**
	 * 更新商品信息
	 * @param items
	 */
	public void updateItemsByItemsId(Items items) {
		
		itemsMapper.updateItemsByItemsId(items);
	}

	
	/**
	 * 根据商品类别查询商品信息
	 * @param category
	 * @return
	 */
	public ArrayList<Items> findItemsByCategory(String category){
		
		ArrayList<Items> items = itemsMapper.findItemsByCategory(category);
		return items;
	}
	
}
