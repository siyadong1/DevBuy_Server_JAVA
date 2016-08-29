package com.dev4free.devbuy.constant;

/**
 * 
 * @author syd
 * @date:2016年5月7日
 * @project_name:devbuy
 * @description:定义一些常量
 */
public class Constant {
	
	
	/**
	 * 这是另外一台window2012服务器
	 * ！！！该服务器不可重启，上面再跑正式业务！！！成都锦兆鸿公司官网挂靠在该服务器上面！！！
	 */
	public static final String SERVER2012 = "http://www.cdjzhkj.com/";
	
	/**
	 * 图片实际存放路径
	 */
	public static final String IMAGE_ROOT_REAL_PATH= "/user/local/images/" ;
	
	
	/**
	 * 图片存放路径（服务器端）
	 */
	public static final String IMAGE_ROOT_MAPPING_PATH= "http://www.dev4free.com/devbuy/java/images/";
	
	/**
	 * 存放头像图片
	 */
	public static final String IMAGE_AVATAR= "avatar/";
	
	/**
	 * 存放商品图片
	 */
	public static final String IMAGE_ITEMS = "itemsPicture/";
	
	/**
	 * 定义订单状态
	 */
	public static final String ORDERS_STATE_WAIT_FOR_PAYMENT = "0";   //待支付
	public static final String ORDERS_STATE_WAIT_FOR_SHIPMENT = "1";  //待发货
	public static final String ORDERS_STATE_WAIT_FOR_RECEIVING = "2"; //待收货
	public static final String ORDERS_STATE_COMPLETED = "3";          //已完成
	public static final String ORDERS_STATE_CANCELED = "4";           //已取消

}
