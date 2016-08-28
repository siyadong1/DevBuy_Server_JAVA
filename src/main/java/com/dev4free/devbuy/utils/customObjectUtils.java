package com.dev4free.devbuy.utils;

import com.dev4free.devbuy.po.Address;
import com.dev4free.devbuy.po_custom.OrdersCustom;

/**
 * 对象处理工具类
 * @author lzw
 *
 */
public class customObjectUtils {

	/**
	 * 判断addressCustom的各项属性是否为空或""
	 * @param addressCustom
	 * @return
	 */
	public static boolean isAddressEmpty(Address address){
		
		boolean flag = TextUtils.isEmpty(address.getConsignee_name())
				        ||TextUtils.isEmpty(address.getPhone_number())
				        ||TextUtils.isEmpty(address.getProvince())
				        ||TextUtils.isEmpty(address.getCity())
				        ||TextUtils.isEmpty(address.getDetail_address())
				        ||TextUtils.isEmpty(address.getDefault_address());
		
		return flag;	
	}
	
	/**
	 * 判断ordersCustoms中的几个必须项是否为空或""
	 * @param ordersCustoms
	 * @return
	 */
	public static boolean isOrdersCustomEmpty(OrdersCustom ordersCustoms){
		
		boolean flag = TextUtils.isEmpty(ordersCustoms.getUser_id())
				||TextUtils.isEmpty(ordersCustoms.getState())
		        ||TextUtils.isEmpty(ordersCustoms.getSum());
		
		return flag;
	}
	
	/**
	 * 判断订单是否可以取消（state的值）
	 * @param ordersCustoms
	 * @return
	 */
	public static boolean isOrdersCancelable(OrdersCustom ordersCustoms){
		
		boolean flag = false;
		String s = "";
		if(!TextUtils.isEmpty(ordersCustoms.getState())){
			s = ordersCustoms.getState();
			if(s.equals("0")||s.equals("1")||s.equals("2")||s.equals("3")){
				flag = true; //处于0-3状态的订单都可以进行取消
			}
		}
		
		return flag;
	}
	
	/**
	 * 根据订单状态判断订单是否支付
	 * @param state
	 * @return
	 */
	public static boolean isOrdersPaid(String state){
		
		boolean flag = false;
		if(state.equals("1")||state.equals("2")||state.equals("3")){
			flag = true;
		}
		return flag;
	}
}
