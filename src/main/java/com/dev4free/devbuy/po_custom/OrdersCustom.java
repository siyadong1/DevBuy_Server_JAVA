package com.dev4free.devbuy.po_custom;

import java.util.ArrayList;

import com.dev4free.devbuy.po.OrderDetail;
import com.dev4free.devbuy.po.Orders;

/**
 * orders这张表对象的扩展类，查询用户订单时的返回对象
 * @author lzw
 *
 */
public class OrdersCustom extends Orders{

//	//Orders类的属性
//	private String orders_id;
//	private String user_id;
//	private String address_id;
//	private String create_time;
//	private String sum;
//	private String state;
	
	
	private ArrayList<OrderDetailCustom> orderDetailCustom; //订单详情

	public ArrayList<OrderDetailCustom> getOrderDetailCustom() {
		return orderDetailCustom;
	}

	public void setOrderDetailCustom(ArrayList<OrderDetailCustom> orderDetailCustom) {
		this.orderDetailCustom = orderDetailCustom;
	}

	@Override
	public String toString() {
		return "OrdersCustom [orderDetailCustom=" + orderDetailCustom + "]";
	}
	
}
