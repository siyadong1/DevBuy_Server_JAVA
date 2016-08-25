package com.dev4free.devbuy.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.AddressMapper;
import com.dev4free.devbuy.po.Address;
import com.dev4free.devbuy.po_custom.AddressCustom;
import com.dev4free.devbuy.service.AddressService;


public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressMapper addressMapper;

	/**
	 * 查询收货地址
	 */
	public ArrayList<Address> findAddressByAddress(AddressCustom addressCustom) {
		
		ArrayList<Address> address = addressMapper.findAddressByAddress(addressCustom);
		
		return address;
	}

	/**
	 * 添加收货地址
	 */
	public void insertShippingAddress(Address address) {

		addressMapper.insertShippingAddress(address);
	}

	/**
	 * 更新已有的收货地址
	 */
	public void updateShippingAddress(Address address) {

		addressMapper.updateShippingAddress(address);
	}

	
	/**
	 * 删除收货地址
	 * @param address_id
	 */
	public void deleteShippingAddress(String address_id){
		
		addressMapper.deleteShippingAddress(address_id);
	}

}
