package com.dev4free.devbuy.po;

public class Address {
	
	//Address类的属性对于address表的列名
	private String id;
	private String username;
	private String consignee_name;
	private String phone_number;
	private String province;
	private String city;
	private String detail_address;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getConsignee_name() {
		return consignee_name;
	}
	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", username=" + username + ", consignee_name=" + consignee_name + ", phone_number="
				+ phone_number + ", province=" + province + ", city=" + city + ", detail_address=" + detail_address
				+ "]";
	}
	
	
}
