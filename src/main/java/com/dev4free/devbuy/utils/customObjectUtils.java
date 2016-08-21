package com.dev4free.devbuy.utils;

import com.dev4free.devbuy.po_custom.AddressCustom;

/**
 * 对象处理工具类
 * @author lzw
 *
 */
public class customObjectUtils {

	/**
	 * 判断addressCustom的各项属性是否为空或“”
	 * @param addressCustom
	 * @return
	 */
	public static boolean isAddressEmpty(AddressCustom addressCustom){
		
		boolean flag = TextUtils.isEmpty(addressCustom.getUsername())
						||TextUtils.isEmpty(addressCustom.getConsignee_name())
				        ||TextUtils.isEmpty(addressCustom.getPhone_number())
				        ||TextUtils.isEmpty(addressCustom.getProvince())
				        ||TextUtils.isEmpty(addressCustom.getCity())
				        ||TextUtils.isEmpty(addressCustom.getDetail_address());
		
		return flag;
		
	}
}
