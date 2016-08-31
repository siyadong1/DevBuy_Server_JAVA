package com.dev4free.devbuy.service;

import java.util.ArrayList;

import com.dev4free.devbuy.po.Items;

public interface ItemsService {

	/**
	 * 插入商品信息
	 * @param items
	 */
	public void insertItems(Items items);
	
	/**
	 * 根据商品的id查询商品信息详情
	 * @param items_id
	 * @return
	 */
	public Items findItemsByItemsId(String items_id);
	
	/**
	 * 更新商品信息
	 * @param items
	 */
	public void updateItemsByItemsId(Items items);
	
	/**
	 * 根据商品类别查询商品信息
	 * @param category
	 * @return
	 */
	public ArrayList<Items> findItemsByCategory(String category);
	
	/**
	 * 查询所有商品信息
	 * @return
	 */
	public ArrayList<Items> findItems();
	
}
