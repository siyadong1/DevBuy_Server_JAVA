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
	public static final String IMAGE_ROOT_REAL_PATH= "/usr/local/images/" ;
	
	
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
	 * 存放banner图片
	 */
	public static final String IMAGE_BANNER = "bannerPicture/";
	
	/**
	 * 存放classification图片
	 */
	public static final String IMAGE_CLASSIFICATION = "classificationPicture/";
	
	/**
	 * 存放recommend图片
	 */
	public static final String IMAGE_RECOMMEND = "recommendPicture/";
	
	/**
	 * 定义订单状态
	 */
	public static final String ORDERS_STATE_WAIT_FOR_PAYMENT = "0";   //待支付
	public static final String ORDERS_STATE_WAIT_FOR_SHIPMENT = "1";  //待发货
	public static final String ORDERS_STATE_WAIT_FOR_RECEIVING = "2"; //待收货
	public static final String ORDERS_STATE_COMPLETED = "3";          //已完成
	public static final String ORDERS_STATE_CANCELED = "4";           //已取消
	public static final String ORDERS_STATE_ALL ="5";                //全部订单

	/**
	 * 定义ConstantInfo常量名称
	 */
	public static final String CONSTANT_INFO_NAME_ABOUTUS = "关于我们";
	
	
	/**
	 * 定义推荐项目图片大小
	 */
	public static final String RECOMMEND_PICTURE_SIZE_LARGE = "0";
	public static final String RECMMEND_PICTURE_SIZE_SMALL = "1";
}
