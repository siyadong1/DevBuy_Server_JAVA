package com.dev4free.devbuy.service;

import java.util.ArrayList;

import com.dev4free.devbuy.po.Orders;
import com.dev4free.devbuy.po_custom.OrdersCustom;

public interface OrdersService {

	/**
	 * 插入订单记录
	 * @param orders
	 */
	public void insertOrdersRecord(Orders orders);
	
	/**
	 * 查询用户订单
	 * @param orders
	 * @return
	 */
	public ArrayList<OrdersCustom> findOrdersByUserName(Orders orders);
	
	/**
	 * 根据orders_id查询订单信息
	 * @param orders_id
	 * @return
	 */
	public OrdersCustom findOrdersByOrdersId(String orders_id);
	
	/**
	 * 根据orders_id更新订单信息
	 * @param orders_id
	 */
	public void updateOrdersRecordByOrdersId(Orders orders);
	
}
