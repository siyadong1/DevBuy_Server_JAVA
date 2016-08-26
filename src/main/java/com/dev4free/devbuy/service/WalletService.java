package com.dev4free.devbuy.service;

import com.dev4free.devbuy.po.Wallet;

public interface WalletService {

	/**
	 * 创建钱包用户
	 * @param wallet
	 */
	public void insertWalletBalance(Wallet wallet);
	
	/**
	 * 根据用户id查询用户钱包信息
	 * @param user_id
	 * @return
	 */
	public Wallet findWalletBalance(String user_id);
	
	/**
	 * 根据用户id更新钱包余额
	 * @param wallet
	 */
	public void updateWalletBalance(Wallet wallet);
}
