package com.dev4free.devbuy.utils;

/**
 * 字符串处理工具类
 * @author syd
 *
 */
public class TextUtils {

	
	/**
	 * 判断字符串是否为null或者是""
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(String text){
		
		boolean flag = text == null || text.equals("");
		
		return flag;
	}
	
}
