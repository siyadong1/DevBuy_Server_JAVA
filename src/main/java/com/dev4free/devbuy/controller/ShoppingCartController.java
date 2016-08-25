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
import com.dev4free.devbuy.po.Items;
import com.dev4free.devbuy.po.ShoppingCartRecord;
import com.dev4free.devbuy.po.User;
import com.dev4free.devbuy.po_custom.UserShoppingCart;
import com.dev4free.devbuy.service.ItemsService;
import com.dev4free.devbuy.service.ShoppingCartService;
import com.dev4free.devbuy.service.UserService;
import com.dev4free.devbuy.utils.TextUtils;
import com.dev4free.devbuy.utils.UUIDUtils;

/**
 * 
 * @author lzw
 * @date:2016年8月22日
 * @project_name:devbuy
 * @description:购物车模块的相关操作
 */
@Controller
@RequestMapping(value="/java/",method={RequestMethod.GET,RequestMethod.POST})
public class ShoppingCartController {

	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ItemsService itemsService;
	

	/**
	 * 返回username对应的用户购物车商品详情
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/findShoppingCartDetailsByUserName")
	public @ResponseBody ResponseMessage findShoppingCartDetailsByUserName(String username){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//根据传入的用户名，判断该用户是否存在
		User user = userService.findUserByUsername(username);
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		String user_id = user.getUser_id();
		
		ArrayList<UserShoppingCart> userShoppingCart = shoppingCartService.findShoppingCartDetailsByUserId(user_id);
		
		responseMessage.setContent(JSON.toJSON(userShoppingCart));
		
		return responseMessage;
	}
	
	
	/**
	 * 商品加入购物车
	 * @param username
	 * @param items_id
	 * @return
	 */
	@RequestMapping(value="/appendItemsToShoppingCart")
	public @ResponseBody ResponseMessage appendItemsToShoppingCart(String username, String items_id){
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(items_id)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//根据传入的用户名，判断该用户是否存在
		User user = userService.findUserByUsername(username);
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		//根据传入的items_id，判断要加入的商品是否存在
		Items items = itemsService.findItemsByItemsId(items_id);
		if(items==null){
			responseMessage.setCode(ConstantResponse.CODE_ITEMS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_ITEMS_NOEXISTS);
			return responseMessage;
		}
		
		String user_id = user.getUser_id();
		//查询购物车中是否已经有该商品
		ShoppingCartRecord shoppingCartRecord = new ShoppingCartRecord();

		shoppingCartRecord.setUser_id(user_id);
		shoppingCartRecord.setItems_id(items_id);
		
		ShoppingCartRecord sCRecord = new ShoppingCartRecord();
		sCRecord = shoppingCartService.findShoppingCartRecordByShoppingCartRecord(shoppingCartRecord);
		if(sCRecord!=null){
			//该用户的购物车中已经添加过该商品
			Integer num = Integer.parseInt(sCRecord.getItems_num())+1;
			sCRecord.setItems_num(num.toString());
			//更新该用户购物车中该商品的数量
			shoppingCartService.updateItemsNumByCartId(sCRecord);
			return responseMessage;
		}
		
		//该用户购物车中未添加过该商品
		shoppingCartRecord.setCart_id(UUIDUtils.getId());
		shoppingCartRecord.setItems_num("1");

		shoppingCartService.insertShoppingCartRecordByShoppingCartRecord(shoppingCartRecord);
		
		return responseMessage;
	}
	
	/**
	 * 更新购物车商品数量
	 * @param username
	 * @param shoppingCartRecord
	 * @return
	 */
	@RequestMapping(value="/updateItemsNumByCartId")   //shoppingCartRecord中传入的参数cart_id,items_num
	public @ResponseBody ResponseMessage updateItemsNumByCartId(String username,ShoppingCartRecord shoppingCartRecord){
	
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)||shoppingCartRecord==null){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//根据传入的用户名，判断该用户是否存在
		User user = userService.findUserByUsername(username);
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		//判断传入的记录对应的user_id是否和当前user_id相同
		ShoppingCartRecord sCRecord = new ShoppingCartRecord();
		sCRecord = shoppingCartService.findShoppingCartRecordByCartId(shoppingCartRecord);
		if(sCRecord==null || !sCRecord.getUser_id().equals(user.getUser_id())){
			responseMessage.setCode(ConstantResponse.CODE_SHOPPINGCARTRECORD_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_SHOPPINGCARTRECORD_NOEXISTS);
			return responseMessage;
		}
		
		shoppingCartService.updateItemsNumByCartId(shoppingCartRecord);
		
		return responseMessage;
	}
	
	/**
	 * 从购物车删除商品
	 * @param username
	 * @param cart_id
	 * @return
	 */
	@RequestMapping(value="/deleteItemsFromShoppingCart")
	public @ResponseBody ResponseMessage deleteItemsFromShoppingCart(String username, String cart_id){
	
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(cart_id)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//根据传入的用户名，判断该用户是否存在
		User user = userService.findUserByUsername(username);
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		//查询是否有该记录
		ShoppingCartRecord shoppingCartRecord = new ShoppingCartRecord();
		shoppingCartRecord.setCart_id(cart_id);;

		ShoppingCartRecord sCRecord = new ShoppingCartRecord(); //存放查询结果
		sCRecord = shoppingCartService.findShoppingCartRecordByShoppingCartRecord(shoppingCartRecord);
		if(sCRecord==null || !sCRecord.getUser_id().equals(user.getUser_id())){
			responseMessage.setCode(ConstantResponse.CODE_SHOPPINGCARTRECORD_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_SHOPPINGCARTRECORD_NOEXISTS);
			return responseMessage;
		}
		
		shoppingCartService.deleteItemsFromShoppingCart(cart_id);
		
		return responseMessage;
		
		
	}
}
