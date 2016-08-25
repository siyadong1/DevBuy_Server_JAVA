package com.dev4free.devbuy.po_custom;

/**
 * 获取购物车信息时返回的数据对象
 * @author lzw
 *
 */
public class UserShoppingCart {

	private String itemsname;
	private String description;
	private String items_price;
	private String image;
	private String items_num;
	
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
	public String getItems_price() {
		return items_price;
	}
	public void setItems_price(String items_price) {
		this.items_price = items_price;
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
		return "UserShoppingCart [itemsname=" + itemsname + ", description=" + description + ", items_price="
				+ items_price + ", image=" + image + ", items_num=" + items_num + "]";
	}
	
}
