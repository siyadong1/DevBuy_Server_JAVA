package com.dev4free.devbuy.po;

public class Address {
	
	//Address类的属性对于address表的列名
	private String address_id;
	private String user_id;
	private String consignee_name;
	private String phone_number;
	private String province;
	private String city;
	private String detail_address;
	private String default_address;
	
	
	public String getAddress_id() {
		return address_id;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public String getDefault_address() {
		return default_address;
	}
	public void setDefault_address(String default_address) {
		this.default_address = default_address;
	}
	
	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", user_id=" + user_id + ", consignee_name=" + consignee_name
				+ ", phone_number=" + phone_number + ", province=" + province + ", city=" + city + ", detail_address="
				+ detail_address + ", default_address=" + default_address + "]";
	}
	

	


}
