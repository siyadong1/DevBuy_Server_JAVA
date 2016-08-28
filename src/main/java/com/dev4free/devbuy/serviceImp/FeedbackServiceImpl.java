package com.dev4free.devbuy.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.FeedbackMapper;
import com.dev4free.devbuy.po.Feedback;
import com.dev4free.devbuy.service.FeedbackService;


public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackMapper feedbackMapper;
	
	
	/**
	 * 查询FeedbackInfo
	 * @param user_id
	 * @return
	 */
	public Feedback findFeedbackByUserId(String user_id){
		Feedback feedback = feedbackMapper.findFeedbackByUserId(user_id);
		return feedback;
	}
	
	/**
	 * 更新FeedbackInfo
	 * @param feedback
	 */
	public void updateFeedbackByUserId(Feedback feedback){
		feedbackMapper.updateFeedbackByUserId(feedback);
	}
	
	/**
	 * 插入FeedbackInfo
	 * @param feedback
	 */
	public void insertFeedbackInfo(Feedback feedback){
		feedbackMapper.insertFeedbackInfo(feedback);
	}

}
