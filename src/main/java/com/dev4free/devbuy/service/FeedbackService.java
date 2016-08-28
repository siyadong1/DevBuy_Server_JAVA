package com.dev4free.devbuy.service;

import com.dev4free.devbuy.po.Feedback;

public interface FeedbackService {

	/**
	 * 查询FeedbackInfo
	 * @param user_id
	 * @return
	 */
	public Feedback findFeedbackByUserId(String user_id);
	
	/**
	 * 更新FeedbackInfo
	 * @param feedback
	 */
	public void updateFeedbackByUserId(Feedback feedback);
	
	/**
	 * 插入FeedbackInfo
	 * @param feedback
	 */
	public void insertFeedbackInfo(Feedback feedback);
}
