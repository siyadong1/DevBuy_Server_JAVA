package com.dev4free.devbuy.service;

import java.util.ArrayList;

import com.dev4free.devbuy.po.ShoppingCartRecord;
import com.dev4free.devbuy.po_custom.UserShoppingCart;

public interface ShoppingCartService {

	/**
	 * 商品加入购物车
	 * @param shoppingCartRecord
	 */
	public void appendItemsToShoppingCart(ShoppingCartRecord shoppingCartRecord);
	
	/**
	 * 查询购物车记录
	 * @param shoppingCartRecord
	 * @return
	 */
	public ShoppingCartRecord findShoppingCartRecordByShoppingCartRecord(ShoppingCartRecord shoppingCartRecord);
	
	/**
	 * 根据cart_id查询购物车添加记录
	 * @param shoppingCartRecord
	 * @return
	 */
	public ShoppingCartRecord findShoppingCartRecordByCartId(ShoppingCartRecord shoppingCartRecord);
	
	/**
	 * 更新购物车商品数量
	 * @param shoppingCartRecord
	 */
	public void updateItemsNumByCartId(ShoppingCartRecord shoppingCartRecord);
	
	/**
	 * 购物车中添加新的商品
	 * @param shoppingCartRecord
	 */
	public void insertShoppingCartRecordByShoppingCartRecord(ShoppingCartRecord shoppingCartRecord);
	
	/**
	 * 根据cart_id删除购物车记录
	 * @param cart_id
	 */
	public void deleteItemsFromShoppingCart(String cart_id);
	
	/**
	 * 根据用户id返回购物车商品详情
	 * @param username
	 * @return
	 */
	public ArrayList<UserShoppingCart> findShoppingCartDetailsByUserId(String user_id);
}
