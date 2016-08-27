package com.dev4free.devbuy.constant;

/**
 * 定义返回信息中的相关常量
 * @author syd
 *
 */
public class ConstantResponse {

	public static final String CODE_PARAMETER_EMPTY = "0000001";
	public static final String CONTENT_PARAMETER_EMPTY = "输入参数为空！";
	
	
	public static final String CODE_USER_EXISTS = "0000002";
	public static final String CONTENT_USER_EXISTS = "该商户已经注册！";
	
	
	public static final String CODE_USER_NOEXISTS = "0000003";
	public static final String CONTENT_USER_NOEXISTS = "该商户不存在！";
	
	
	public static final String CODE_USER_PASSWORD_ERROR = "0000004";
	public static final String CONTENT_USER_PASSWORD_ERROR = "密码错误！";
	
	
	public static final String CODE_USER_PASSWORD_DISMATCH = "0000005";
	public static final String CONTENT_USER_PASSWORD_DISMATCH = "新密码和确认密码不一致";
	
	
	public static final String CODE_CITY_NOEXISTS = "0000006";
	public static final String CONTENT_CITY_NOEXISTS = "城市名称列表获取失败";
	
	
	public static final String CODE_SHIPPINGADDRESS_NOEXISTS = "0000007";
	public static final String CONTENT_SHIPPINGADDRESS_NOEXISTS = "收货地址不存在";
	
	
	public static final String CODE_SHIPPINGADDRESS_EMPTY = "0000008";
	public static final String CONTENT_SHIPPINGADDRESS_EMPTY = "收货地址不完整";
	
	
	public static final String CODE_ITEMS_NOEXISTS = "0000009";
	public static final String CONTENT_ITEMS_NOEXISTS = "商品不存在";
	
	
	public static final String CODE_SHOPPINGCARTRECORD_NOEXISTS = "0000010";
	public static final String CONTENT_SHOPPINGCARTRECORD_NOEXISTS = "该记录不存在";
	
	
	public static final String CODE_ITMES_INVENTORY_ERROR = "0000011";
	public static final String CONTENT_ITMES_INVENTORY_ERROR = "商品库存量不足";
	
	
	public static final String CODE_ITMES_SALESVOLUME_ERROR = "0000012";
	public static final String CONTENT_ITMES_SALESVOLUME_ERROR = "商品销量错误";
	
	
	public static final String CODE_ORDERS_NOEXISTS = "0000013";
	public static final String CONTENT_ORDERS_NOEXISTS = "订单不存在";
	
	
	public static final String CODE_ORDERS_PAYMENT_COMPLETED = "0000014";
	public static final String CONTENT_ORDERS_PAYMENT_COMPLETED = "订单已支付";
	
	
	public static final String CODE_ORDERS_CANCELED = "0000015";
	public static final String CONTENT_ORDERS_CANCELED = "订单已取消";
	
	
	public static final String CODE_ORDERS_INFO_ERROR = "0000016";
	public static final String CONTENT_ORDERS_INFO_ERROR = "订单信息错误";
	
	
	public static final String CODE_WALLET_ERROR = "0000017";
	public static final String CONTENT_WALLET_ERROR = "钱包余额不足";
	
	
	public static final String CODE_ORDERS_STATE_ERROR = "0000018";
	public static final String CONTENT_ORDERS_STATE_ERROR = "订单状态错误";
	
	
	public static final String CODE_ITEMS_PICTURE_ERROR = "0000019";
	public static final String CONTENT_ITEMS_PICTURE_ERROR = "商品图片命名错误";
	
	
	
}
