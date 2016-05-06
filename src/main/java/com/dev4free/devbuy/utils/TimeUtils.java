package com.dev4free.devbuy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author syd
 * @date:2016年5月5日
 * @project_name:devbuy
 * @description:时间工具类
 */
public class TimeUtils {

	
	/**
	 * 获取系统当前时间
	 * @return
	 */
	public static String getNow(){
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return simpleDateFormat.format(new Date());
		
		
	}
	
}
