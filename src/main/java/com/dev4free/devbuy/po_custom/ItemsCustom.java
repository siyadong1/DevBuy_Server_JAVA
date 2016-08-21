package com.dev4free.devbuy.po_custom;

import org.springframework.web.multipart.MultipartFile;

public class ItemsCustom {
	
	//Items类的属性对于items表的列名
	private String items_id;
	private String itemsname;
	private String description;
	private String category;
	private String items_lb;
	private String price;
	private MultipartFile itemspic;
	private String inventory;
	private String sales_volume;
	private String area;
	
	
	public String getItems_id() {
		return items_id;
	}
	public void setItems_id(String items_id) {
		this.items_id = items_id;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItems_lb() {
		return items_lb;
	}
	public void setItems_lb(String items_lb) {
		this.items_lb = items_lb;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public MultipartFile getPicture() {
		return itemspic;
	}
	public void setPicture(MultipartFile itemspic) {
		this.itemspic = itemspic;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public String getSales_volume() {
		return sales_volume;
	}
	public void setSales_volume(String sales_volume) {
		this.sales_volume = sales_volume;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	@Override
	public String toString() {
		return "ItemsCustom [items_id=" + items_id + ", itemsname=" + itemsname + ", description=" + description
				+ ", category=" + category + ", items_lb=" + items_lb + ", price=" + price + ", picture=" + itemspic
				+ ", inventory=" + inventory + ", sales_volume=" + sales_volume + ", area=" + area + "]";
	}

}
