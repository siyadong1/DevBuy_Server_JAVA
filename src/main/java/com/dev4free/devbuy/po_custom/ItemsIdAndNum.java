package com.dev4free.devbuy.po_custom;

/**
 * 提交订单信息时，接收数据用
 * @author lzw
 *
 */
public class ItemsIdAndNum {

	private String items_id;
	private String items_num;
	
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
		return "ItemsIdAndNum [items_id=" + items_id + ", items_num=" + items_num + "]";
	}
	
	
}
