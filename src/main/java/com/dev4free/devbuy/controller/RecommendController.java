package com.dev4free.devbuy.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.Recommend;
import com.dev4free.devbuy.po_custom.RecommendCustom;
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

		ArrayList<RecommendCustom> recommendCustoms = new ArrayList<RecommendCustom>();
		
		
		//先取出recommend表中有多少种推荐项目名称
		ArrayList<String> rc_category = recommendService.recommendCategoryQuery();
		
		Iterator<String> iterator = rc_category.iterator();
		while(iterator.hasNext()){
			String temp_rc_category = iterator.next();
			
			RecommendCustom temp = new RecommendCustom();
			temp.setCategory(temp_rc_category);
			
			//根据temp_rc_category查询recommend详情
			ArrayList<Recommend> recommend = recommendService.recommendQueryByRcCategory(temp_rc_category);
			temp.setRecommend(recommend);
			recommendCustoms.add(temp);
		}
	
		responseMessage.setContent(JSON.toJSON(recommendCustoms));
		
		return responseMessage;
	}
}
