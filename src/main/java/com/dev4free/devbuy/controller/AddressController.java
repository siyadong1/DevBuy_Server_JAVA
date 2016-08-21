package com.dev4free.devbuy.controller;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dev4free.devbuy.constant.ConstantResponse;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.Address;
import com.dev4free.devbuy.po.User;
import com.dev4free.devbuy.po_custom.AddressCustom;
import com.dev4free.devbuy.service.AddressService;
import com.dev4free.devbuy.service.UserService;
import com.dev4free.devbuy.utils.TextUtils;
import com.dev4free.devbuy.utils.UUIDUtils;
import com.dev4free.devbuy.utils.customObjectUtils;

@Controller
@RequestMapping(value="/java/",method={RequestMethod.POST,RequestMethod.GET})
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@Autowired
	UserService userservice;
	
	@RequestMapping(value="/findAddressByUserName")
	private @ResponseBody ResponseMessage findAddressByUserName(AddressCustom addressCustom){
		
		//返回给移动端的数据
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(addressCustom.getUsername())){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		User user = userservice.findUserByUsername(addressCustom.getUsername());
		
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		String user_id = user.getUser_id(); //移动端传入的是username，但address表外键是user_id
		addressCustom.setUser_id(user_id);
		
		ArrayList<Address> address = addressService.findAddressByAddress(addressCustom);
		
		if(address==null){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_NOEXISTS);
			return responseMessage;			
		}
		
		responseMessage.setContent(JSON.toJSON(address));
		
		return responseMessage;
		
	}
	
	@RequestMapping(value="/insertShippingAddress")
	private @ResponseBody ResponseMessage insertShippingAddress(AddressCustom addressCustom){
		
		//返回给移动端的数据
		ResponseMessage responseMessage = new ResponseMessage();
		
		if(addressCustom==null||customObjectUtils.isAddressEmpty(addressCustom)){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_EMPTY);
			return responseMessage;
		}
		
		User user = userservice.findUserByUsername(addressCustom.getUsername());
		
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		String user_id = user.getUser_id();
		
		AddressCustom addr1 = new AddressCustom();
		addr1.setUser_id(user_id);
		addr1.setDefault_address("true");
		
		ArrayList<Address> temp = addressService.findAddressByAddress(addr1);
		
		Address address = new Address();
		BeanUtils.copyProperties(addressCustom, address);
		address.setUser_id(user_id);
		
		if(temp!=null){
			address.setDefault_address(null);
		}
		else if(temp==null){
			address.setDefault_address("true");
		}
		
		address.setAddress_id(UUIDUtils.getId());; //传入参数中不包括id项
		addressService.insertShippingAddress(address);
		
		return responseMessage;
		
	}
	
	@RequestMapping(value="/updateShippingAddress")
	private @ResponseBody ResponseMessage updateShippingAddress(AddressCustom addressCustom){
		
		//返回给移动端的数据
		ResponseMessage responseMessage = new ResponseMessage();
		
		if(addressCustom==null||customObjectUtils.isAddressEmpty(addressCustom)){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_EMPTY);
			return responseMessage;
		}
		
		if(TextUtils.isEmpty(addressCustom.getAddress_id())){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_NOEXISTS);
			return responseMessage;
		}
		
		AddressCustom addr1 = new AddressCustom();
		addr1.setAddress_id(addressCustom.getAddress_id());
		
		ArrayList<Address> temp = addressService.findAddressByAddress(addr1);
		
		if(temp==null){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_NOEXISTS);
			return responseMessage;
		}
		
		User user = userservice.findUserByUsername(addressCustom.getUsername());
		
		Address address = new Address();
		BeanUtils.copyProperties(addressCustom, address);
		address.setUser_id(user.getUser_id());
		
		addressService.updateShippingAddress(address);
		
		return responseMessage;
		
	}
	
	@RequestMapping(value="/setDefaultShippingAddress")
	private @ResponseBody ResponseMessage setDefaultShippingAddress(AddressCustom addressCustom){
		
		//返回给移动端的数据
		ResponseMessage responseMessage = new ResponseMessage();
		
		if(addressCustom==null||TextUtils.isEmpty(addressCustom.getAddress_id())||TextUtils.isEmpty(addressCustom.getUsername())){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//判断该用户是否已经设置了默认地址
		User user = userservice.findUserByUsername(addressCustom.getUsername());
		
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		String user_id = user.getUser_id();
		
		AddressCustom addr1 = new AddressCustom();
		addr1.setUser_id(user_id);
		addr1.setDefault_address("true");
		
		ArrayList<Address> temp = addressService.findAddressByAddress(addr1);
		
		Address address = new Address();
		BeanUtils.copyProperties(addressCustom, address);

		if(temp!=null){
			Address address1 = new Address();
			address1.setAddress_id(temp.get(0).getAddress_id());
			address1.setDefault_address(null);
			addressService.setDefaultShippingAddress(address1);
		}
		
		address.setDefault_address("true");
		addressService.setDefaultShippingAddress(address);
		
		return responseMessage;
		
	}
	
	
}
