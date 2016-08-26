package com.dev4free.devbuy.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.WalletMapper;
import com.dev4free.devbuy.po.Wallet;
import com.dev4free.devbuy.service.WalletService;

public class WalletServiceImpl implements WalletService {

	@Autowired
	WalletMapper walletMapper;
	
	/**
	 * 创建钱包用户
	 * @param wallet
	 */
	public void insertWalletBalance(Wallet wallet){
		
		walletMapper.insertWalletBalance(wallet);
	}
	
	/**
	 * 根据用户id查询用户钱包信息
	 * @param user_id
	 * @return
	 */
	public Wallet findWalletBalance(String user_id) {

		Wallet wallet = walletMapper.findWalletBalance(user_id);
		return wallet;
	}
	
	
	/**
	 * 根据用户id更新钱包余额
	 * @param wallet
	 */
	public void updateWalletBalance(Wallet wallet){
		walletMapper.updateWalletBalance(wallet);
	}

}
