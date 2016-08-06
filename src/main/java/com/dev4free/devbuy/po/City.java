package com.dev4free.devbuy.po;

/**
 * City这张表对象的实体类
 * @author dell
 *
 */
public class City {

	//City类的属性对于City表的列名
	private String id;
	private String initial;
	private String cityName;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", initial=" + initial + ", cityName=" + cityName + "]";
	}
	
}
