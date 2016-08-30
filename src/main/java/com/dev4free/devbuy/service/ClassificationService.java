package com.dev4free.devbuy.service;

import java.util.ArrayList;

import com.dev4free.devbuy.po.Classification;

public interface ClassificationService {

	/**
	 * 查询导航分类图片
	 * @return
	 */
	public ArrayList<Classification> classificationQuery();
	
	
	/**
	 * 添加classification上需要显示的图片的详情
	 * @param classification
	 */
	public void insertClassificationDetail(Classification classification);
	
	/**
	 * 更新classification上需要显示的图片的详情
	 * @param classification
	 */
	public void updateClassificationDetail(Classification classification);
}
