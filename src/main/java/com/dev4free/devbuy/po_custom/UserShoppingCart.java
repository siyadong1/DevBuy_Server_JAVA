package com.dev4free.devbuy.po_custom;

/**
 * 获取购物车信息时返回的数据对象
 * @author lzw
 *
 */
public class UserShoppingCart {

	private String cart_id;
	private String itemsname;
	private String description;
	private String price;
	private String current_price;
	private String image;
	private String items_num;
	public String getCart_id() {
		return cart_id;
	}
	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}
	public String getItemsname() {
		return itemsname;
	}
	public void setItemsname(String itemsname) {
		this.itemsname = itemsname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(String current_price) {
		this.current_price = current_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getItems_num() {
		return items_num;
	}
	public void setItems_num(String items_num) {
		this.items_num = items_num;
	}
	@Override
	public String toString() {
		return "UserShoppingCart [cart_id=" + cart_id + ", itemsname=" + itemsname + ", description=" + description
				+ ", price=" + price + ", current_price=" + current_price + ", image=" + image + ", items_num="
				+ items_num + "]";
	}



}
