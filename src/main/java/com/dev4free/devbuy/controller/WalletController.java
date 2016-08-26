package com.dev4free.devbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev4free.devbuy.constant.ConstantResponse;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.User;
import com.dev4free.devbuy.po.Wallet;
import com.dev4free.devbuy.service.UserService;
import com.dev4free.devbuy.service.WalletService;
import com.dev4free.devbuy.utils.TextUtils;
import com.dev4free.devbuy.utils.UUIDUtils;


/**
 * 钱包模块相关操作
 * @author lzw
 *
 */
@Controller
@RequestMapping(value="/java/",method={RequestMethod.GET,RequestMethod.POST})
public class WalletController {

	@Autowired
	WalletService walletService;
	
	@Autowired
	UserService userService;
	
	/**
	 * 用户钱包充值
	 * @param username
	 * @param balance
	 * @return
	 */
	@RequestMapping(value="/userWalletRecharge")
	public @ResponseBody ResponseMessage userWalletRecharge(String username, String balance){
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(balance)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}

		//根据username查找对应的user_id
		User user = userService.findUserByUsername(username);

		if(user==null){
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		String user_id = user.getUser_id();

		Wallet wallet = walletService.findWalletBalance(user_id);
		
		if(wallet==null){
			//首次充值，先创建钱包
			wallet = new Wallet();
			wallet.setWallet_id(UUIDUtils.getId());
			wallet.setUser_id(user_id);
			wallet.setBalance(balance);
			
			walletService.insertWalletBalance(wallet);
			return responseMessage;
		}
		
		double newBalance =0;
		
		if(TextUtils.isEmpty(wallet.getBalance())){
			newBalance = Double.parseDouble(balance);
		}
		else{
			newBalance = Double.parseDouble(wallet.getBalance())+Double.parseDouble(balance);
		}
		
		wallet.setBalance(((Double)newBalance).toString());
		walletService.updateWalletBalance(wallet);
		
		return responseMessage;
	}
	
	
	
	
	
}
