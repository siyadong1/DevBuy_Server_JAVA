package com.dev4free.devbuy.po_custom;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;

/**
 * 提交订单时传入的商品信息
 * @author lzw
 *
 */
public class ItemsCustom {

	private ArrayList<ItemsIdAndNum> itemsIdAndNum;

	public ArrayList<ItemsIdAndNum> getItemsIdAndNum() {
		return itemsIdAndNum;
	}

	public void setItemsIdAndNum(ArrayList<ItemsIdAndNum> itemsIdAndNum) {
		this.itemsIdAndNum = itemsIdAndNum;
	}

	@Override
	public String toString() {
		return "ItemsCustom [itemsIdAndNum=" + itemsIdAndNum + "]";
	}
	
}
