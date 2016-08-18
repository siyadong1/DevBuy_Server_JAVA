package com.dev4free.devbuy.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.AddressMapper;
import com.dev4free.devbuy.po.Address;
import com.dev4free.devbuy.service.AddressService;


public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressMapper addressMapper;

	public ArrayList<Address> findAddressByUserName(String username) {
		
		ArrayList<Address> address = addressMapper.findAddressByUserName(username);
		
		return address;
	}

	public Address findAddressById(String id) {
		
		Address address = addressMapper.findAddressById(id);
		
		return address;
	}
	
	public void insertShippingAddress(Address address) {

		addressMapper.insertShippingAddress(address);
	}

	public void updateShippingAddress(Address address) {

		addressMapper.updateShippingAddress(address);
	}


}
