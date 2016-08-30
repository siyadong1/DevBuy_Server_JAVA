package com.dev4free.devbuy.po;

/**
 * recommend这张表对应的实体类
 * @author dell
 *
 */
public class Recommend {

	//Recommend类的属性对于recommend表的列名
	private String rc_id;
	private String rc_category;
	private String rc_name;
	private String rc_url;
	private String rc_islarge;
	private String items_id;
	public String getRc_id() {
		return rc_id;
	}
	public void setRc_id(String rc_id) {
		this.rc_id = rc_id;
	}
	public String getRc_category() {
		return rc_category;
	}
	public void setRc_category(String rc_category) {
		this.rc_category = rc_category;
	}
	public String getRc_name() {
		return rc_name;
	}
	public void setRc_name(String rc_name) {
		this.rc_name = rc_name;
	}
	public String getRc_url() {
		return rc_url;
	}
	public void setRc_url(String rc_url) {
		this.rc_url = rc_url;
	}
	public String getRc_islarge() {
		return rc_islarge;
	}
	public void setRc_islarge(String rc_islarge) {
		this.rc_islarge = rc_islarge;
	}
	public String getItems_id() {
		return items_id;
	}
	public void setItems_id(String items_id) {
		this.items_id = items_id;
	}
	@Override
	public String toString() {
		return "Recommend [rc_id=" + rc_id + ", rc_category=" + rc_category + ", rc_name=" + rc_name + ", rc_url="
				+ rc_url + ", rc_islarge=" + rc_islarge + ", items_id=" + items_id + "]";
	}
	
	

	
}
