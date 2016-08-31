package com.dev4free.devbuy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dev4free.devbuy.constant.Constant;
import com.dev4free.devbuy.constant.ConstantResponse;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.Classification;
import com.dev4free.devbuy.po.Items;
import com.dev4free.devbuy.service.ClassificationService;
import com.dev4free.devbuy.service.ItemsService;
import com.dev4free.devbuy.utils.TextUtils;

/**
 * classification初始化
 * @author dell
 *
 */
@Controller
@RequestMapping(value="/java/",method={RequestMethod.GET,RequestMethod.POST})
public class ClassificationController {

	
	@Autowired
	ClassificationService classificationService;
	
	@Autowired
	ItemsService itemsService;
	
	
	@RequestMapping(value="/classificationInitial")
	public @ResponseBody ResponseMessage classificationInitial(){
		ResponseMessage responseMessage = new ResponseMessage();
		
		//模块二：返回导航分类图片及关联商品类别
		ArrayList<Classification> classification = classificationService.classificationQuery();
		responseMessage.setContent(JSON.toJSON(classification));
		
		return responseMessage;
	}
	
	
	/**
	 * 根据传入的商品类别查询商品详情
	 * @param category
	 * @return
	 */
	@RequestMapping(value="/queryItemsDetailByCategory")
	public @ResponseBody ResponseMessage queryItemsDetailByCategory(String category){

		ResponseMessage responseMessage = new ResponseMessage();
		
		if(TextUtils.isEmpty(category)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		if(category.equals(Constant.ITEMS_CATEGORY_ALL)){
			ArrayList<Items> items = itemsService.findItems();
			responseMessage.setContent(JSON.toJSON(items));
			return responseMessage;
		}
		
		//根据商品类别查询商品详情
		ArrayList<Items> items = itemsService.findItemsByCategory(category);
		
		responseMessage.setContent(JSON.toJSON(items));
	
		return responseMessage;
	}
}
