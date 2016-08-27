package com.dev4free.devbuy.mapper;

import java.util.ArrayList;

import com.dev4free.devbuy.po.Address;


public interface AddressMapper {
	
	/**
	 * 根据用户名称查询收货地址
	 * @return 
	 */
	public ArrayList<Address> findAddressByAddress(Address address);
	
	/**
	 * 添加收货地址
	 * @param address
	 */
	public void insertShippingAddress(Address address);
	
	/**
	 * 更新已有的收货地址
	 * @param address
	 */
	public void updateShippingAddress(Address address);
	
	
	/**
	 * 删除收货地址
	 * @param address_id
	 */
	public void deleteShippingAddress(String address_id);
	
}
