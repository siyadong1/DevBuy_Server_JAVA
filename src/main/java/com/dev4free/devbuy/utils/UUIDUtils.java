package com.dev4free.devbuy.utils;

import java.util.UUID;

/**
 * 
 * @author syd
 * @date:2016年5月5日
 * @project_name:devbuy
 * @description:使用UUID作为用户的ID
 */
public class UUIDUtils {

	
	public static String getId(){
		
		return UUID.randomUUID().toString().replaceAll("-", "");
		
	}
	
}
