package com.dev4free.devbuy.po;

public class ConstantInfo {

	//AboutUs类对应数据库中的aboutus表
	private String info_id;
	private String info_name;
	private String info_content;
	
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	public String getInfo_name() {
		return info_name;
	}
	public void setInfo_name(String info_name) {
		this.info_name = info_name;
	}
	public String getInfo_content() {
		return info_content;
	}
	public void setInfo_content(String info_content) {
		this.info_content = info_content;
	}
	@Override
	public String toString() {
		return "AboutUs [info_id=" + info_id + ", info_name=" + info_name + ", info_content=" + info_content + "]";
	}
	
	
}
