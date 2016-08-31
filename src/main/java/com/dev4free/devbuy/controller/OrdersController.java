package com.dev4free.devbuy.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dev4free.devbuy.constant.Constant;
import com.dev4free.devbuy.constant.ConstantResponse;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.Address;
import com.dev4free.devbuy.po.Items;
import com.dev4free.devbuy.po.OrderDetail;
import com.dev4free.devbuy.po.Orders;
import com.dev4free.devbuy.po.User;
import com.dev4free.devbuy.po.Wallet;
import com.dev4free.devbuy.po_custom.ItemsCustom;
import com.dev4free.devbuy.po_custom.ItemsIdAndNum;
import com.dev4free.devbuy.po_custom.OrderDetailCustom;
import com.dev4free.devbuy.po_custom.OrdersCustom;
import com.dev4free.devbuy.service.AddressService;
import com.dev4free.devbuy.service.ItemsService;
import com.dev4free.devbuy.service.OrderDetailService;
import com.dev4free.devbuy.service.OrdersService;
import com.dev4free.devbuy.service.UserService;
import com.dev4free.devbuy.service.WalletService;
import com.dev4free.devbuy.utils.TextUtils;
import com.dev4free.devbuy.utils.TimeUtils;
import com.dev4free.devbuy.utils.UUIDUtils;
import com.dev4free.devbuy.utils.customObjectUtils;

/**
 * 订单模块相关操作
 * @author lzw
 *
 */
@Controller
@RequestMapping(value="/java/",method={RequestMethod.GET,RequestMethod.POST})
public class OrdersController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	ItemsService itemsService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	OrdersService ordersService;
	
	@Autowired
	WalletService walletService;
	
	/**
	 * 提交订单
	 * @param username
	 * @param address_id
	 * @param itemsIdAndNum
	 * @return
	 */
	@RequestMapping(value="/submitOrders")
	public @ResponseBody ResponseMessage submitOrders(String username, String address_id, String itemsIdAndNum){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(address_id)|| TextUtils.isEmpty(itemsIdAndNum)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}

		//从传入的json字符串中获取ItemsAndNum属性项
		List<ItemsIdAndNum> temp_itemsAndNum = new ArrayList<ItemsIdAndNum>();
		temp_itemsAndNum = JSONObject.parseArray(itemsIdAndNum, ItemsIdAndNum.class);
		
		//根据username查找对应的user_id
		User user = userService.findUserByUsername(username);
		
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
		
		String orders_id = UUIDUtils.getId(); //先生成订单号orders_id,orderdetail表中的外键
		double sum = 0; //订单总金额
		String state = Constant.ORDERS_STATE_WAIT_FOR_PAYMENT;
		
		Iterator<ItemsIdAndNum> iterator = temp_itemsAndNum.iterator();
		
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
				
				if(inventory.equals(Constant.ORDERS_STATE_WAIT_FOR_PAYMENT)||inventory==null||Integer.parseInt(inventory)<Integer.parseInt(items_num)){
					responseMessage.setCode(ConstantResponse.CODE_ITMES_INVENTORY_ERROR);
					responseMessage.setContent(ConstantResponse.CONTENT_ITMES_INVENTORY_ERROR);
					return responseMessage;
				}
			}
			
			double num = Double.parseDouble(items_num);
			double current_price = Double.parseDouble(items.getCurrent_price());
			
			sum = sum + num*current_price; //将所有商品的单价*数量相加，得到订单总金额
			
		}

		//当保证所有商品库存量都足够可以下订单时，再创建订单详情表记录，更新items表中商品的库存量和销量
		iterator = temp_itemsAndNum.iterator();
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
		
		
		//提交订单成功后，删除购物车属于该订单的商品
		
		
		responseMessage.setContent(JSON.toJSON(orders_id));
		
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
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(state)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		if(!customObjectUtils.isOrdersStateRight(state)){
			responseMessage.setCode(ConstantResponse.CODE_ORDERS_STATE_ERROR);
			responseMessage.setContent(ConstantResponse.CONTENT_ORDERS_STATE_ERROR);
			return responseMessage;
		}
		
		//根据username查找对应的user_id
		User user = userService.findUserByUsername(username);
		
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		String user_id = user.getUser_id();

		//根据user_id和state查询订单
		Orders orders = new Orders();
		orders.setUser_id(user_id);
		if(state.equals(Constant.ORDERS_STATE_ALL)){
			orders.setState(null);
		}
		else{
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
	public @ResponseBody ResponseMessage cancelOrdersByOrdersId(String username,String orders_id){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(orders_id)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//根据username查找对应的user_id
		User user = userService.findUserByUsername(username);
		
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		String user_id = user.getUser_id();
		
		//根据orders_id查询订单
		OrdersCustom ordersCustoms = ordersService.findOrdersByOrdersId(orders_id);

		if(ordersCustoms==null||customObjectUtils.isOrdersCustomEmpty(ordersCustoms)||!ordersCustoms.getUser_id().equals(user_id)){
			responseMessage.setCode(ConstantResponse.CODE_ORDERS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_ORDERS_NOEXISTS);
			return responseMessage;
		}
		
		//判断订单能否取消
		if(!customObjectUtils.isOrdersCancelable(ordersCustoms)){
			//不能取消说明订单状态不符合
			responseMessage.setCode(ConstantResponse.CODE_ORDERS_STATE_ERROR);
			responseMessage.setContent(ConstantResponse.CONTENT_ORDERS_STATE_ERROR);
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
		
		//判断订单是否已支付，已支付需要退款
		if(customObjectUtils.isOrdersPaid(ordersCustoms.getState())){
			String sum = ordersCustoms.getSum();
			
			//根据user_id取用户钱包信息
			Wallet wallet = walletService.findWalletBalance(user_id);
			
			if(wallet==null||TextUtils.isEmpty(wallet.getBalance())){
				wallet = new Wallet();
				wallet.setWallet_id(UUIDUtils.getId());
				wallet.setUser_id(user_id);
				wallet.setBalance(sum);
				walletService.insertWalletBalance(wallet);
			}
			//退款操作
			double newBalance =Double.parseDouble(wallet.getBalance())+Double.parseDouble(sum);
			wallet.setBalance(((Double)newBalance).toString());
			walletService.updateWalletBalance(wallet);
		}
		
		String state = Constant.ORDERS_STATE_CANCELED; //订单状态更改为已取消

		//更改订单状态
		Orders orders = new Orders();
		orders.setOrders_id(orders_id);
		orders.setState(state);
		ordersService.updateOrdersRecordByOrdersId(orders);
		
		return responseMessage;
	}
	
	
	/**
	 * 根据订单id进行付款
	 * @param orders_id
	 * @return
	 */
	@RequestMapping(value="/payForOrdersByOrdersId")
	public @ResponseBody ResponseMessage payForOrdersByOrdersId(String username,String orders_id){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(orders_id)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//根据username查找对应的user_id
		User user = userService.findUserByUsername(username);
		
		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		String user_id = user.getUser_id();
		
		
		//根据orders_id查询订单
		OrdersCustom ordersCustoms = ordersService.findOrdersByOrdersId(orders_id);

		if(ordersCustoms==null||!ordersCustoms.getUser_id().equals(user_id)){
			responseMessage.setCode(ConstantResponse.CODE_ORDERS_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_ORDERS_NOEXISTS);
			return responseMessage;
		}
		
		//根据当前订单的状态进行处理
		if(ordersCustoms.getState().equals(Constant.ORDERS_STATE_WAIT_FOR_SHIPMENT)
				||ordersCustoms.getState().equals(Constant.ORDERS_STATE_WAIT_FOR_SHIPMENT)
				||ordersCustoms.getState().equals(Constant.ORDERS_STATE_COMPLETED)){
			responseMessage.setCode(ConstantResponse.CODE_ORDERS_PAYMENT_COMPLETED);
			responseMessage.setContent(ConstantResponse.CONTENT_ORDERS_PAYMENT_COMPLETED);
			return responseMessage;
		}
		else if(ordersCustoms.getState().equals(Constant.ORDERS_STATE_CANCELED)){
			responseMessage.setCode(ConstantResponse.CODE_ORDERS_CANCELED);
			responseMessage.setContent(ConstantResponse.CONTENT_ORDERS_CANCELED);
			return responseMessage;
		}
		
		//订单待支付
		if(ordersCustoms.getState().equals(Constant.ORDERS_STATE_WAIT_FOR_PAYMENT)){
			String sum = ordersCustoms.getSum();
			if(TextUtils.isEmpty(sum)){
				responseMessage.setCode(ConstantResponse.CODE_ORDERS_INFO_ERROR);
				responseMessage.setContent(ConstantResponse.CONTENT_ORDERS_INFO_ERROR);
				return responseMessage;
			}
			
			Wallet wallet = walletService.findWalletBalance(user_id);
			
			double temp_sum = Double.parseDouble(sum); //待支付金额
			double temp_balance = Double.parseDouble(wallet.getBalance()); //钱包余额
			
			if(temp_sum>temp_balance){
				responseMessage.setCode(ConstantResponse.CODE_WALLET_ERROR);
				responseMessage.setContent(ConstantResponse.CONTENT_WALLET_ERROR);
				return responseMessage;
			}
			
			temp_balance = temp_balance-temp_sum;
			wallet.setBalance(((Double)temp_balance).toString());
			walletService.updateWalletBalance(wallet);
			
			//支付完成，更新订单状态
			String state = Constant.ORDERS_STATE_WAIT_FOR_SHIPMENT; //待发货
			Orders orders = new Orders();
			orders.setOrders_id(orders_id);
			orders.setState(state);
			
			ordersService.updateOrdersRecordByOrdersId(orders);
			return responseMessage;
		}
		
		responseMessage.setCode(ConstantResponse.CODE_ORDERS_STATE_ERROR);
		responseMessage.setContent(ConstantResponse.CONTENT_ORDERS_STATE_ERROR);
		return responseMessage;
	}
	
}
