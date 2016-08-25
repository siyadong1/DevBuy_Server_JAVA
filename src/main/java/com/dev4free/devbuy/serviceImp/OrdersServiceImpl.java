package com.dev4free.devbuy.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.OrdersMapper;
import com.dev4free.devbuy.po.Orders;
import com.dev4free.devbuy.po_custom.OrdersCustom;
import com.dev4free.devbuy.service.OrdersService;

public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersMapper ordersMapper;
	
	/**
	 * 插入订单记录
	 * @param orders
	 */
	public void insertOrdersRecord(Orders orders) {

		ordersMapper.insertOrdersRecord(orders);
	}

	
	/**
	 * 查询用户订单
	 * @param orders
	 * @return
	 */
	public ArrayList<OrdersCustom> findOrdersByUserName(Orders orders){
		
		ArrayList<OrdersCustom> ordersCustom = ordersMapper.findOrdersByUserName(orders);
		return ordersCustom;		
	}
	
	
	/**
	 * 根据orders_id查询订单信息
	 * @param orders_id
	 * @return
	 */
	public OrdersCustom findOrdersByOrdersId(String orders_id){
		
		OrdersCustom ordersCustom = ordersMapper.findOrdersByOrdersId(orders_id);
		return ordersCustom;
	}
	
	
	/**
	 * 根据orders_id更新订单信息
	 * @param orders_id
	 */
	public void updateOrdersRecordByOrdersId(Orders orders){
		
		ordersMapper.updateOrdersRecordByOrdersId(orders);
	}

}
