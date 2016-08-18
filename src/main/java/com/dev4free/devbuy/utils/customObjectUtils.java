package com.dev4free.devbuy.utils;

import com.dev4free.devbuy.po.Address;

/**
 * 对象处理工具类
 * @author lzw
 *
 */
public class customObjectUtils {

	public static boolean isAddressEmpty(Address address){
		
		boolean flag = TextUtils.isEmpty(address.getUsername())
						||TextUtils.isEmpty(address.getConsignee_name())
				        ||TextUtils.isEmpty(address.getPhone_number())
				        ||TextUtils.isEmpty(address.getProvince())
				        ||TextUtils.isEmpty(address.getCity())
				        ||TextUtils.isEmpty(address.getDetail_address());
		
		return flag;
		
	}
}
