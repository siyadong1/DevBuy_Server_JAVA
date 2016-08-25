package com.dev4free.devbuy.service;

import com.dev4free.devbuy.po.OrderDetail;

public interface OrderDetailService {
	
	/**
	 * 插入订单详情记录
	 * @param orderDetail
	 */
	public void insertOrderDetailRecord(OrderDetail orderDetail);

}
