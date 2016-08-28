package com.dev4free.devbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dev4free.devbuy.constant.ConstantResponse;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.ConstantInfo;
import com.dev4free.devbuy.po.Feedback;
import com.dev4free.devbuy.po.User;
import com.dev4free.devbuy.service.ConstantInfoService;
import com.dev4free.devbuy.service.FeedbackService;
import com.dev4free.devbuy.service.UserService;
import com.dev4free.devbuy.utils.TextUtils;
import com.dev4free.devbuy.utils.UUIDUtils;

@Controller
@RequestMapping(value="/java/",method={RequestMethod.GET,RequestMethod.POST})
public class HomePagesController {
	
	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ConstantInfoService constantInfoService;
	
	/**
	 * 更新Feedback
	 * @param username
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/updateHomePagesFeedback")
	public @ResponseBody ResponseMessage updateHomePagesInfo(String username,String content){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(content)){
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

		Feedback feedback = feedbackService.findFeedbackByUserId(user_id);
		
		if(feedback==null){
			feedback = new Feedback();
			feedback.setFeedback_id(UUIDUtils.getId());
			feedback.setUser_id(user_id);
			feedback.setContent(content);
			feedbackService.insertFeedbackInfo(feedback);
			return responseMessage;
		}

		feedback.setContent(content);
		feedbackService.updateFeedbackByUserId(feedback);
		
		return responseMessage;
	}
	
	/**
	 * 查询ConstantInfo
	 * @param info_name
	 * @return
	 */
	@RequestMapping(value="/findHomePagesAboutus")
	public @ResponseBody ResponseMessage findHomePagesAboutus(String info_name){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if(TextUtils.isEmpty(info_name)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		ConstantInfo constantInfo = constantInfoService.findConstantInfo(info_name);
		
		responseMessage.setContent(JSON.toJSON(constantInfo));
		
		return responseMessage;
	}
	

}
