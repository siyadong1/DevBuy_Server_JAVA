package com.dev4free.devbuy.service;

import java.util.ArrayList;

import com.dev4free.devbuy.po.Address;



public interface AddressService {

	/**
	 * 根据用户名称查询收货地址
	 * @return 
	 */
	public ArrayList<Address> findAddressByUserName(String username);
	
	/**
	 * 根据id查询收货地址
	 * @param id
	 * @return
	 */
	public Address findAddressById(String id);
	
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
	
	
}
