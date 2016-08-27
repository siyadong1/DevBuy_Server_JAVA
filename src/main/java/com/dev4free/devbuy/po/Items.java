package com.dev4free.devbuy.po;

/**
 * items这张表对象的实体类
 * @author lzw
 *
 */
public class Items {
	
	//Items类的属性对于items表的列名
	private String items_id;
	private String itemsname;
	private String description;
	private String category;
	private String items_lb;
	private String price;
	private String current_price;
	private String image;
	private String inventory;
	private String sales_volume;
	private String area;
	private String express_fee;
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
	public String getExpress_fee() {
		return express_fee;
	}
	public void setExpress_fee(String express_fee) {
		this.express_fee = express_fee;
	}
	@Override
	public String toString() {
		return "Items [items_id=" + items_id + ", itemsname=" + itemsname + ", description=" + description
				+ ", category=" + category + ", items_lb=" + items_lb + ", price=" + price + ", current_price="
				+ current_price + ", image=" + image + ", inventory=" + inventory + ", sales_volume=" + sales_volume
				+ ", area=" + area + ", express_fee=" + express_fee + "]";
	}


}
