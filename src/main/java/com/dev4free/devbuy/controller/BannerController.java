package com.dev4free.devbuy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.Banner;
import com.dev4free.devbuy.service.BannerService;

/**
 * banner初始化
 * @author lzw
 *
 */
@Controller
@RequestMapping(value="/java/",method={RequestMethod.GET,RequestMethod.POST})
public class BannerController {

	
	@Autowired
	BannerService bannerService;
	
	@RequestMapping(value="/bannerInitial")
	public @ResponseBody ResponseMessage bannerInitial(){
		ResponseMessage responseMessage = new ResponseMessage();
		
		//模块一：返回banner显示的图片及关联商品的id
		ArrayList<Banner> banner = bannerService.bannerQuery();

		responseMessage.setContent(JSON.toJSON(banner));
		return responseMessage;
	}
}
