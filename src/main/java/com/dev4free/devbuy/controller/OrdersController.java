package com.dev4free.devbuy.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dev4free.devbuy.constant.ConstantResponse;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.Address;
import com.dev4free.devbuy.po.Items;
import com.dev4free.devbuy.po.OrderDetail;
import com.dev4free.devbuy.po.Orders;
import com.dev4free.devbuy.po.User;
import com.dev4free.devbuy.po_custom.AddressCustom;
import com.dev4free.devbuy.po_custom.ItemsCustom;
import com.dev4free.devbuy.po_custom.ItemsIdAndNum;
import com.dev4free.devbuy.po_custom.OrderDetailCustom;
import com.dev4free.devbuy.po_custom.OrdersCustom;
import com.dev4free.devbuy.service.AddressService;
import com.dev4free.devbuy.service.ItemsService;
import com.dev4free.devbuy.service.OrderDetailService;
import com.dev4free.devbuy.service.OrdersService;
import com.dev4free.devbuy.service.UserService;
import com.dev4free.devbuy.utils.TextUtils;
import com.dev4free.devbuy.utils.TimeUtils;
import com.dev4free.devbuy.utils.UUIDUtils;

/**
 * 订单模块相关操作
 * @author lzw
 *
 */
@Controller
@RequestMapping(value="/java/",method={RequestMethod.GET,RequestMethod.POST})
public class OrdersController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	ItemsService itemsService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	OrdersService ordersService;
	
	/**
	 * 提交订单
	 * @param username
	 * @param address_id
	 * @param itemsIdAndNum
	 * @return
	 */
	@RequestMapping(value="/submitOrders")
	public @ResponseBody ResponseMessage submitOrders(String username, String address_id, ItemsCustom itemsCustom){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(address_id)|| itemsCustom==null){
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
		String user_id = user.getUser_id();
		
		//判断传入address_id对应的收货地址是否存在,若存在，判断是否是username对应用户的收货地址
		AddressCustom addr1 = new AddressCustom();
		addr1.setAddress_id(address_id);

		ArrayList<Address> temp = addressService.findAddressByAddress(addr1);

		if(temp.size()==0 || !temp.get(0).getUser_id().equals(user_id)){
			responseMessage.setCode(ConstantResponse.CODE_SHIPPINGADDRESS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_SHIPPINGADDRESS_NOEXISTS);
			return responseMessage;
		}
		
		
		String orders_id = UUIDUtils.getId(); //先生成订单号orders_id,orderdetail表中的外键
		double sum = 0; //订单总金额
		String state = "待支付";
		
		ArrayList<ItemsIdAndNum> itemsIdAndNum = itemsCustom.getItemsIdAndNum();
		Iterator<ItemsIdAndNum> iterator = itemsIdAndNum.iterator();
		
		while(iterator.hasNext()){
			
			ItemsIdAndNum idAndNum = iterator.next();
			
			String items_id = idAndNum.getItems_id();
			String items_num = idAndNum.getItems_num();
			
			
			Items items = itemsService.findItemsByItemsId(items_id); //根据items_id查询商品信息
			if(items==null){
				responseMessage.setCode(ConstantResponse.CODE_ITEMS_NOEXISTS);
				responseMessage.setContent(ConstantResponse.CONTENT_ITEMS_NOEXISTS);
				return responseMessage;
			}
			else {
				String inventory = items.getInventory();
				
				if(inventory.equals("0")||inventory==null||Integer.parseInt(inventory)<Integer.parseInt(items_num)){
					responseMessage.setCode(ConstantResponse.CODE_ITMES_INVENTORY_ERROR);
					responseMessage.setContent(ConstantResponse.CONTENT_ITMES_INVENTORY_ERROR);
					return responseMessage;
				}
			}
			
			double num = Double.parseDouble(items_num);
			double price = Double.parseDouble(items.getPrice());
			
			sum = sum + num*price; //将所有商品的单价*数量相加，得到订单总金额
			
		}

		//当保证所有商品库存量都足够可以下订单时，再创建订单详情表记录，更新items表中商品的库存量和销量
		iterator = itemsIdAndNum.iterator();
		while(iterator.hasNext()){

			ItemsIdAndNum idAndNum = iterator.next();

			String items_id = idAndNum.getItems_id();
			String items_num = idAndNum.getItems_num();
			
			//创建订单详情记录
			OrderDetail orderDetail = new OrderDetail();
			
			orderDetail.setDetails_id(UUIDUtils.getId());
			orderDetail.setOrders_id(orders_id);
			orderDetail.setItems_id(items_id);
			orderDetail.setItems_num(items_num);
			
			orderDetailService.insertOrderDetailRecord(orderDetail);
			
			//更新items表中的商品库存量和销量
			Items items = itemsService.findItemsByItemsId(items_id); //根据items_id查询商品信息
			
			//当前库存量和销量
			int inventory = Integer.parseInt(items.getInventory())-Integer.parseInt(items_num);
			int sales_volume = Integer.parseInt(items.getSales_volume())+Integer.parseInt(items_num);
			
			Items temp_items = new Items(); //没有变动的项不更新
			temp_items.setInventory(((Integer)inventory).toString());
			temp_items.setSales_volume(((Integer)sales_volume).toString());
			
			itemsService.updateItemsByItemsId(temp_items);

		}
		
		
		//orders只记录一条记录，orderdetail记录商品id及商品详情
		Orders orders = new Orders();
		
		orders.setOrders_id(orders_id);
		orders.setUser_id(user_id);
		orders.setAddress_id(address_id);
		orders.setCreate_time(TimeUtils.getNow());
		orders.setSum(((Double)sum).toString());
		orders.setState(state);

		ordersService.insertOrdersRecord(orders);
		
		return responseMessage;
	}

	
	/**
	 * 查看用户订单，返回订单详情
	 * @param username
	 * @param state
	 * @return
	 */
	@RequestMapping(value="/findOrdersByUserName")
	public @ResponseBody ResponseMessage findOrdersByUserName(String username,String state){
		
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
		String user_id = user.getUser_id();

		//根据user_id和state查询订单
		Orders orders = new Orders();
		orders.setUser_id(user_id);
		if(!TextUtils.isEmpty(state)){
			orders.setState(state);
		}
				
		ArrayList<OrdersCustom> ordersCustoms = ordersService.findOrdersByUserName(orders);
		
		responseMessage.setContent(JSON.toJSON(ordersCustoms));
		
		return responseMessage;
		
	}
	
	
	/**
	 * 根据订单id取消订单
	 * @param orders_id
	 * @return
	 */
	@RequestMapping(value="/cancelOrdersByOrdersId")
	public @ResponseBody ResponseMessage cancelOrdersByOrdersId(String orders_id){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(orders_id)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//根据orders_id查询订单
		
		OrdersCustom ordersCustoms = ordersService.findOrdersByOrdersId(orders_id);

		if(ordersCustoms==null){
			responseMessage.setCode(ConstantResponse.CODE_ORDERS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_ORDERS_NOEXISTS);
			return responseMessage;
		}
		
		//根据当前订单的状态决定
		if(ordersCustoms.getState().equals("4")){
			//state=4说明订单已经是取消状态
			return responseMessage;
		}
		
		//取出订单中的items的items_id和items_num,更新商品inventory和sales_volume信息
		Iterator<OrderDetailCustom> iterator = (ordersCustoms.getOrderDetailCustom()).iterator();
		
		while(iterator.hasNext()){

			OrderDetailCustom orderDetailCustom = iterator.next();

			String items_id = orderDetailCustom.getItems_id();
			String items_num = orderDetailCustom.getItems_num();
			
			Items items = itemsService.findItemsByItemsId(items_id); //根据items_id查询商品信息
			if(items==null){
				responseMessage.setCode(ConstantResponse.CODE_ITEMS_NOEXISTS);
				responseMessage.setContent(ConstantResponse.CONTENT_ITEMS_NOEXISTS);
				return responseMessage;
			}

			Items temp_items = new Items(); //没有变动的项不更新
			temp_items.setItems_id(items_id);
			
			
			//库存量
			String inventory = items.getInventory();
			
			if(TextUtils.isEmpty(inventory)){
				inventory = items_num;
				temp_items.setInventory(inventory);
			}
			else{
				int inv = Integer.parseInt(items.getInventory())+Integer.parseInt(items_num);
				temp_items.setInventory(((Integer)inv).toString());
			}
			
			//销量
			String sales_volume = items.getSales_volume();
			
			if(TextUtils.isEmpty(sales_volume)||Integer.parseInt(sales_volume)<Integer.parseInt(items_num)){
				responseMessage.setCode(ConstantResponse.CODE_ITMES_SALESVOLUME_ERROR);
				responseMessage.setContent(ConstantResponse.CONTENT_ITMES_SALESVOLUME_ERROR);
				return responseMessage;
			}
			else{
				int sv = Integer.parseInt(items.getSales_volume())-Integer.parseInt(items_num);
				temp_items.setSales_volume(((Integer)sv).toString());
			}
			
			//更新商品库存量和销量
			itemsService.updateItemsByItemsId(temp_items);
		
		}
		
		String state = "4"; //订单状态更改为已取消

		//更改订单状态
		Orders orders = new Orders();
		orders.setOrders_id(orders_id);
		orders.setState(state);
		ordersService.updateOrdersRecordByOrdersId(orders);
		
		return responseMessage;
	}
	
	
}
