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

	public ArrayList<Address> findAddressByAddress(AddressCustom addressCustom) {
		
		ArrayList<Address> address = addressMapper.findAddressByAddress(addressCustom);
		
		return address;
	}

	
	public void insertShippingAddress(Address address) {

		addressMapper.insertShippingAddress(address);
	}

	public void updateShippingAddress(Address address) {

		addressMapper.updateShippingAddress(address);
	}

	public void setDefaultShippingAddress(Address address){
		
		addressMapper.setDefaultShippingAddress(address);
	}

}
