package com.dev4free.devbuy.controller;

import java.util.ArrayList;

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
import com.dev4free.devbuy.service.AddressService;
import com.dev4free.devbuy.service.UserService;
import com.dev4free.devbuy.utils.TextUtils;
import com.dev4free.devbuy.utils.customObjectUtils;

@Controller
@RequestMapping(value="/java/",method={RequestMethod.POST,RequestMethod.GET})
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@Autowired
	UserService userservice;
	
	@RequestMapping(value="/findAddressByUserName")
	private @ResponseBody ResponseMessage findAddressByUserName(String username){
		
		//返回给移动端的数据
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		User user = userservice.findUserByUsername(username);
		
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		ArrayList<Address> address = addressService.findAddressByUserName(username);
		
		if(address==null){
			responseMessage.setCode(ConstantResponse.CODE_CONTACTADDRESS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_CONTACTADDRESS_NOEXISTS);
			return responseMessage;			
		}
		
		responseMessage.setContent(JSON.toJSON(address));
		
		return responseMessage;
		
	}
	
	@RequestMapping(value="/insertShippingAddress")
	private @ResponseBody ResponseMessage insertShippingAddress(Address address){
		
		//返回给移动端的数据
		ResponseMessage responseMessage = new ResponseMessage();
		
		if(address==null||customObjectUtils.isAddressEmpty(address)){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_EMPTY);
			return responseMessage;
		}
		
		addressService.insertShippingAddress(address);
		
		return responseMessage;
		
	}
	
	
	
}
