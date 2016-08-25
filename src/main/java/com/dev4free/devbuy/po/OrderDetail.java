package com.dev4free.devbuy.po;

/**
 * OrderDetail这张表对象的实体类
 * @author lzw
 *
 */
public class OrderDetail{

	//OrderDetail类的属性对于orderDetail表的列名
	private String details_id;
	private String orders_id;
	private String items_id;
	private String items_num;
	
	public String getDetails_id() {
		return details_id;
	}
	public void setDetails_id(String details_id) {
		this.details_id = details_id;
	}
	public String getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(String orders_id) {
		this.orders_id = orders_id;
	}
	public String getItems_id() {
		return items_id;
	}
	public void setItems_id(String items_id) {
		this.items_id = items_id;
	}
	public String getItems_num() {
		return items_num;
	}
	public void setItems_num(String items_num) {
		this.items_num = items_num;
	}
	
	@Override
	public String toString() {
		return "OrderDetail [details_id=" + details_id + ", orders_id=" + orders_id + ", items_id=" + items_id
				+ ", items_num=" + items_num + "]";
	}
	
	
}
