package com.dev4free.devbuy.po;

public class Wallet {

	//Wallet类的属性对于wallet表的列名
	private String wallet_id;
	private String user_id;
	private String balance;
	
	public String getWallet_id() {
		return wallet_id;
	}
	public void setWallet_id(String wallet_id) {
		this.wallet_id = wallet_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Wallet [wallet_id=" + wallet_id + ", user_id=" + user_id + ", balance=" + balance + "]";
	}
	
	
}
