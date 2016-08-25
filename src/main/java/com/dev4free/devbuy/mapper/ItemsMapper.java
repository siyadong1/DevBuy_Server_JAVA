package com.dev4free.devbuy.mapper;

import com.dev4free.devbuy.po.Items;

public interface ItemsMapper {
	
	/**
	 * 插入商品信息
	 * @param items
	 */
	public void insertItems(Items items);
	
	
	/**
	 * 根据商品id查询商品信息详情
	 * @param items_id
	 * @return
	 */
	public Items findItemsByItemsId(String items_id);
	
	/**
	 * 更新商品信息
	 * @param items
	 */
	public void updateItemsByItemsId(Items items);
}
