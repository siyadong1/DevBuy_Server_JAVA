package com.dev4free.devbuy.po;


/**
 * User这张表对象的实体类
 * @author syd
 *
 */
public class User {
	
	//User类的属性对于User表的列名
	private String user_id;
	private String username;
	private String password;
	private String avatar;
	private String nickname;
	private String regtime;
	private String gender;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", avatar=" + avatar
				+ ", nickname=" + nickname + ", regtime=" + regtime + ", gender=" + gender + "]";
	}
	
	
	
	
}
