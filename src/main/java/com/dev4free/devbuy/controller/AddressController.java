package com.dev4free.devbuy.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
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
import com.dev4free.devbuy.utils.UUIDUtils;
import com.dev4free.devbuy.utils.customObjectUtils;


/**
 * 
 * @author lzw
 * @date:2016年8月21日
 * @project_name:devbuy
 * @description:AddressController这个类对于数据库中address这张表
 */

@Controller
@RequestMapping(value="/java/",method={RequestMethod.POST,RequestMethod.GET})
public class AddressController {

	//LOGGER用于打印日志，一般在调试的时候打印DEBUG级别的日志
	private static final Logger LOGGER = Logger.getLogger(AddressController.class);
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	UserService userservice;
	
	/**
	 * 根据用户登录账号查找收货地址
	 * @param addressCustom
	 * @return
	 */
	@RequestMapping(value="/findAddressByUserName") //传入参数username,default_address(不是必选)
	private @ResponseBody ResponseMessage findAddressByUserName(String username, String default_address){
		
		//返回给移动端的数据
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//根据username查找对应的user_id
		User user = userservice.findUserByUsername(username);
		
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}

		String user_id = user.getUser_id(); //移动端传入的是username，但address表外键是user_id
		
		Address addr = new Address();
		addr.setUser_id(user_id);
		
		if(default_address.equals("0")){
			addr.setDefault_address(default_address);
		}
		
		ArrayList<Address> address = addressService.findAddressByAddress(addr);
		
		if(address.size()==0){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_NOEXISTS);
			return responseMessage;			
		}
		
		responseMessage.setContent(JSON.toJSON(address));
		
		return responseMessage;
		
	}
	
	/**
	 * 添加用户收货地址
	 * @param addressCustom
	 * @return
	 */
	@RequestMapping(value="/insertShippingAddress")
	private @ResponseBody ResponseMessage insertShippingAddress(String username, Address address){
		
		//返回给移动端的数据
		ResponseMessage responseMessage = new ResponseMessage();
		
		if(address==null||TextUtils.isEmpty(username)||customObjectUtils.isAddressEmpty(address)){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_EMPTY);
			return responseMessage;
		}
		
		//根据username查找对应的user_id
		User user = userservice.findUserByUsername(username);
		
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		String user_id = user.getUser_id();
		
		if(address.getDefault_address().equals("0")){

			//先查询该用户是否已经设置默认地址
			Address addr1 = new Address();
			addr1.setUser_id(user_id);
			addr1.setDefault_address("0");
			ArrayList<Address> temp = addressService.findAddressByAddress(addr1);
			
			if(temp.size()!=0){
				//已经设置默认地址，则更改原来的默认地址的default_address属性为null
				Address addr3 = temp.get(0) ;
				addr3.setDefault_address(null);
				addressService.updateShippingAddress(addr3);
			}
		}
		
		//若该地址不设置为默认地址，查询该用户是否已有收货地址（设置用户插入的第一条地址为默认收货地址）
		Address addr1 = new Address();
		addr1.setUser_id(user_id);
		ArrayList<Address> temp = addressService.findAddressByAddress(addr1);
		
		if(temp.size()==0){
			address.setDefault_address("0");
		}
		
		//不需要设置该地址为默认地址
		address.setAddress_id(UUIDUtils.getId()); //传入参数中不包括id项
		address.setUser_id(user_id);
		
		addressService.insertShippingAddress(address);
		return responseMessage;
		
	}
	
	/**
	 * 修改用户收货地址
	 * @param addressCustom
	 * @return
	 */
	@RequestMapping(value="/updateShippingAddress") //传入参数username,address_id
	private @ResponseBody ResponseMessage updateShippingAddress(String username, Address address){
		
		//返回给移动端的数据
		ResponseMessage responseMessage = new ResponseMessage();
		
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(address.getAddress_id())||customObjectUtils.isAddressEmpty(address)){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_EMPTY);
			return responseMessage;
		}
		String address_id = address.getAddress_id();
		
		//根据username查找对应的user_id
		User user = userservice.findUserByUsername(username);
		
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		String user_id = user.getUser_id();
		
		//判断传入address_id对应的收货地址是否存在,若存在，判断是否是username对应用户的收货地址
		Address addr1 = new Address();
		addr1.setAddress_id(address_id);
		
		ArrayList<Address> temp = addressService.findAddressByAddress(addr1);
		
		if(temp.size()==0 || !temp.get(0).getUser_id().equals(user_id)){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_NOEXISTS);
			return responseMessage;
		}

		
		//传入参数default_address=="0"表示，要将此地址设置为默认收货地址
		//首先判断该用户是否已经设置了默认地址
		//若已经设置，将原来的默认地址的efault_address设置为null
		if(address.getDefault_address().equals("0")){
			
			Address addr2 = new Address();
			addr2.setUser_id(user_id);
			addr2.setDefault_address("0");
			ArrayList<Address> temp1 = addressService.findAddressByAddress(addr2);
			
			if(temp1.size()!=0){
				//已经设置默认地址
				Address addr3 = temp1.get(0) ;
				if(!addr3.getAddress_id().equals(address_id)){
					addr3.setDefault_address(null);
					addressService.updateShippingAddress(addr3);
				}
			}
		}

		address.setUser_id(user_id);
		
		//更新收货地址
		addressService.updateShippingAddress(address);
		
		return responseMessage;
		
	}
	
	
	/**
	 * 删除收货地址
	 * @param username
	 * @param address_id
	 * @return
	 */
	@RequestMapping(value="/deleteShippingAddress")
	private @ResponseBody ResponseMessage deleteShippingAddress(String username,String address_id){
		
		//返回给移动端的数据
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(address_id)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//传入的用户名是否存在
		User user = userservice.findUserByUsername(username);
				
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		//传入的address_id是否为username对应用户的收货地址
		Address addr1 = new Address();
		addr1.setAddress_id(address_id);
		
		ArrayList<Address> temp = addressService.findAddressByAddress(addr1);
		
		if(temp.size()==0){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_NOEXISTS);
			return responseMessage;
		}else if(temp.size()!=0){
			String ss = temp.get(0).getAddress_id();
			if(!ss.equals(address_id)){
				responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_NOEXISTS);
				responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_NOEXISTS);
				return responseMessage;
			}
		}
		
		addressService.deleteShippingAddress(address_id);
		
		return responseMessage;
	}
	
	
}
