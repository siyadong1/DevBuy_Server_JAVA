package com.dev4free.devbuy.mapper;

import java.util.ArrayList;

import com.dev4free.devbuy.po.Address;
import com.dev4free.devbuy.po_custom.AddressCustom;


public interface AddressMapper {
	
	/**
	 * 根据用户名称查询收货地址
	 * @return 
	 */
	public ArrayList<Address> findAddressByAddress(AddressCustom addressCustom);
	
	/**
	 * 插入收货地址
	 * @param address
	 */
	public void insertShippingAddress(Address address);
	
	/**
	 * 更新已有的收货地址
	 * @param address
	 */
	public void updateShippingAddress(Address address);
	
	/**
	 * 设置默认收货地址
	 * @param address
	 */
	public void setDefaultShippingAddress(Address address);
	
}
