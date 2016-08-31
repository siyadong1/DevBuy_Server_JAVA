package com.dev4free.devbuy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.Recommend;
import com.dev4free.devbuy.service.RecommendService;

/**
 * recommend初始化
 * @author dell
 *
 */
@Controller
@RequestMapping(value="/java/",method={RequestMethod.GET,RequestMethod.POST})
public class RecommendController {

	@Autowired
	RecommendService recommendService;
	
	
	@RequestMapping(value="/recmmondInitial")
	public @ResponseBody ResponseMessage recmmondInitial(){
		ResponseMessage responseMessage = new ResponseMessage();

		//模块三：返回推荐项目名称、显示图片及图片关联的商品id
		ArrayList<Recommend> recommend = recommendService.recommendQuery();		
		responseMessage.setContent(JSON.toJSON(recommend));
		
		return responseMessage;
	}
}
