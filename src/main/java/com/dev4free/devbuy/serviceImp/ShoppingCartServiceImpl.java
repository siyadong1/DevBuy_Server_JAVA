package com.dev4free.devbuy.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.ShoppingCartMapper;
import com.dev4free.devbuy.po.ShoppingCartRecord;
import com.dev4free.devbuy.po_custom.UserShoppingCart;
import com.dev4free.devbuy.service.ShoppingCartService;

public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	ShoppingCartMapper shoppingCartMapper;
	
	/**
	 * 商品加入购物车
	 * @param shoppingCartRecord
	 */
	public void appendItemsToShoppingCart(ShoppingCartRecord shoppingCartRecord) {
		
		shoppingCartMapper.appendItemsToShoppingCart(shoppingCartRecord);
		
	}

	/**
	 * 查询购物车记录
	 * @param shoppingCartRecord
	 * @return
	 */
	public ShoppingCartRecord findShoppingCartRecordByShoppingCartRecord(ShoppingCartRecord shoppingCartRecord) {

		ShoppingCartRecord sCRecord = shoppingCartMapper.findShoppingCartRecordByShoppingCartRecord(shoppingCartRecord);
		return sCRecord;
	}

	/**
	 * 根据cart_id查询购物车添加记录
	 * @param shoppingCartRecord
	 * @return
	 */
	public ShoppingCartRecord findShoppingCartRecordByCartId(ShoppingCartRecord shoppingCartRecord){
		
		ShoppingCartRecord sCRecord = shoppingCartMapper.findShoppingCartRecordByCartId(shoppingCartRecord);
		return sCRecord;
	}
	
	/**
	 * 更新购物车商品数量
	 * @param shoppingCartRecord
	 */
	public void updateItemsNumByCartId(ShoppingCartRecord shoppingCartRecord) {

		shoppingCartMapper.updateItemsNumByCartId(shoppingCartRecord);
	}
	
	/**
	 * 购物车中添加新的商品
	 * @param shoppingCartRecord
	 */
	public void insertShoppingCartRecordByShoppingCartRecord(ShoppingCartRecord shoppingCartRecord){
		shoppingCartMapper.insertShoppingCartRecordByShoppingCartRecord(shoppingCartRecord);
		
	}
	
	/**
	 * 根据cart_id删除购物车记录
	 * @param cart_id
	 */
	public void deleteItemsFromShoppingCart(String cart_id){
		shoppingCartMapper.deleteItemsFromShoppingCart(cart_id);
		
	}
	
	/**
	 * 根据用户名返回购物车商品详情
	 * @param username
	 * @return
	 */
	public ArrayList<UserShoppingCart> findShoppingCartDetailsByUserId(String user_id){
		
		ArrayList<UserShoppingCart> userShoppingCart = shoppingCartMapper.findShoppingCartDetailsByUserId(user_id);
		
		return userShoppingCart;
	}

}
