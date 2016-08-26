package com.dev4free.devbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dev4free.devbuy.constant.ConstantResponse;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.HomePages;
import com.dev4free.devbuy.service.HomePagesService;
import com.dev4free.devbuy.utils.TextUtils;
import com.dev4free.devbuy.utils.UUIDUtils;

@Controller
@RequestMapping(value="/java/",method={RequestMethod.GET,RequestMethod.POST})
public class HomePagesController {
	
	@Autowired
	HomePagesService homePagesService;
	
	
	/**
	 * 更新HomePagesInfo
	 * @param info_name
	 * @param info
	 * @return
	 */
	@RequestMapping(value="/updateHomePagesInfo")
	public @ResponseBody ResponseMessage updateHomePagesInfo(String info_name,String info){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(info_name)||TextUtils.isEmpty(info)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		HomePages homePages = homePagesService.findHomePagesInfoByInfoName(info_name);
		
		if(homePages==null){
			homePages = new HomePages();
			homePages.setInfo_id(UUIDUtils.getId());
			homePages.setInfo_name(info_name);
			homePages.setInfo(info);
			homePagesService.insertHomePagesInfo(homePages);
			return responseMessage;
		}
		
		homePages.setInfo(info);
		homePagesService.insertHomePagesInfo(homePages);
		
		return responseMessage;
	}
	
	/**
	 * 查询HomePagesInfo
	 * @param info_name
	 * @return
	 */
	@RequestMapping(value="/findHomePagesInfo")
	public @ResponseBody ResponseMessage findHomePagesInfo(String info_name){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(info_name)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		HomePages homePages = homePagesService.findHomePagesInfoByInfoName(info_name);
		
		responseMessage.setContent(JSON.toJSON(homePages));
		
		return responseMessage;
	}
	

}
