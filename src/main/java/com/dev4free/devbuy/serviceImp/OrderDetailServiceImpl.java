package com.dev4free.devbuy.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.OrderDetailMapper;
import com.dev4free.devbuy.po.OrderDetail;
import com.dev4free.devbuy.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	OrderDetailMapper orderDetailMapper;
	
	/**
	 * 插入订单详情记录
	 * @param orderDetail
	 */
	public void insertOrderDetailRecord(OrderDetail orderDetail) {
		
		orderDetailMapper.insertOrderDetailRecord(orderDetail);		
	}

}
