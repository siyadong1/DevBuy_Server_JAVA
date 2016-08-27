package com.dev4free.devbuy.po;

public class HomePages {

	private String info_id;
	private String info_name;
	private String info;
	
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@Override
	public String toString() {
		return "HomePages [info_id=" + info_id + ", info_name=" + info_name + ", info=" + info + "]";
	}
	
	
}
