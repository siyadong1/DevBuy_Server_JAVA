package com.dev4free.devbuy.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.ClassificationMapper;
import com.dev4free.devbuy.po.Classification;
import com.dev4free.devbuy.service.ClassificationService;

public class ClassificationServiceImpl implements ClassificationService {

	@Autowired
	ClassificationMapper classificationMapper;
	
	/**
	 * 查询导航分类图片
	 * @return
	 */
	public ArrayList<Classification> classificationQuery(){
		
		ArrayList<Classification> classification = classificationMapper.classificationQuery();
		
		return classification;
	}

	/**
	 * 添加classification上需要显示的图片的详情
	 * @param classification
	 */
	public void insertClassificationDetail(Classification classification){
		classificationMapper.insertClassificationDetail(classification);
	}
	
	/**
	 * 更新classification上需要显示的图片的详情
	 * @param classification
	 */
	public void updateClassificationDetail(Classification classification){
		classificationMapper.updateClassificationDetail(classification);
	}
	
}
