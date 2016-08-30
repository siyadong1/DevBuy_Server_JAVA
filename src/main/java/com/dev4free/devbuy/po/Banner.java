package com.dev4free.devbuy.po;


/**
 * banner这张表对应的实体类
 * @author lzw
 *
 */
public class Banner {

	//Banner类的属性对于banner表的列名
	private String bn_id;
	private String bn_name;
	private String bn_url;
	private String items_id;
	
	public String getBn_id() {
		return bn_id;
	}
	public void setBn_id(String bn_id) {
		this.bn_id = bn_id;
	}
	public String getBn_name() {
		return bn_name;
	}
	public void setBn_name(String bn_name) {
		this.bn_name = bn_name;
	}
	public String getBn_url() {
		return bn_url;
	}
	public void setBn_url(String bn_url) {
		this.bn_url = bn_url;
	}
	public String getItems_id() {
		return items_id;
	}
	public void setItems_id(String items_id) {
		this.items_id = items_id;
	}
	@Override
	public String toString() {
		return "Banner [bn_id=" + bn_id + ", bn_name=" + bn_name + ", bn_url=" + bn_url + ", items_id=" + items_id
				+ "]";
	}

	
	
}
