package com.dev4free.devbuy.mapper;

import com.dev4free.devbuy.po.Feedback;

public interface FeedbackMapper {

	/**
	 * 根据user_id查询FeedbackInfo
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
