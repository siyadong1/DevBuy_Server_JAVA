package com.dev4free.devbuy.mapper;

import com.dev4free.devbuy.po.OrderDetail;

public interface OrderDetailMapper {

	/**
	 * 插入订单详情记录
	 * @param orderDetail
	 */
	public void insertOrderDetailRecord(OrderDetail orderDetail);
}
