package com.dev4free.devbuy.po;


/**
 * classification这张表对应的实体类
 * @author lzw
 *
 */
public class Classification {

	//Classification类的属性对于classification表的列名
	private String cf_id;
	private String cf_name;
	private String cf_url;
	private String category;
	public String getCf_id() {
		return cf_id;
	}
	public void setCf_id(String cf_id) {
		this.cf_id = cf_id;
	}
	public String getCf_name() {
		return cf_name;
	}
	public void setCf_name(String cf_name) {
		this.cf_name = cf_name;
	}
	public String getCf_url() {
		return cf_url;
	}
	public void setCf_url(String cf_url) {
		this.cf_url = cf_url;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Classification [cf_id=" + cf_id + ", cf_name=" + cf_name + ", cf_url=" + cf_url + ", category="
				+ category + "]";
	}

	
}
