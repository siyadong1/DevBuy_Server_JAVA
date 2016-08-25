package com.dev4free.devbuy.po_custom;


import com.dev4free.devbuy.po.Items;
import com.dev4free.devbuy.po.OrderDetail;

public class OrderDetailCustom extends OrderDetail{

//	//OrderDetail类的属性对于orderDetail表的列名
//	private String details_id;
//	private String orders_id;
//	private String items_id;
//	private String items_num;
	
	private Items items;

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderDetailCustom [items=" + items + "]";
	}  
}
