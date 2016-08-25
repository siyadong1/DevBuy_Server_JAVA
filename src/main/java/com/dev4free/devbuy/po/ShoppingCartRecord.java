package com.dev4free.devbuy.po;

/**
 * shoppingcart_record这张表对象的实体类
 * @author lzw
 *
 */
public class ShoppingCartRecord {

	private String cart_id;
	private String user_id;
	private String items_id;
	private String items_num;
	
	public String getCart_id() {
		return cart_id;
	}
	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
		return "ShoppingCartRecord [cart_id=" + cart_id + ", user_id=" + user_id + ", items_id=" + items_id
				+ ", items_num=" + items_num + "]";
	}
	
}
