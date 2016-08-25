package com.dev4free.devbuy.po;

/**
 * orders这张表对象的实体类
 * @author lzw
 *
 */
public class Orders {

	//Orders类的属性对于orders表的列名
	private String orders_id;
	private String user_id;
	private String address_id;
	private String create_time;
	private String sum;
	private String state; //订单状态：0 等待付款; 1等待发货; 2等待收货; 3已完成
	
	public String getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(String orders_id) {
		this.orders_id = orders_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAddress_id() {
		return address_id;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Orders [orders_id=" + orders_id + ", user_id=" + user_id + ", address_id=" + address_id
				+ ", create_time=" + create_time + ", sum=" + sum + ", state=" + state + "]";
	}
	
	
}
