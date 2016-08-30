package com.dev4free.devbuy.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.RecommendMapper;
import com.dev4free.devbuy.po.Recommend;
import com.dev4free.devbuy.service.RecommendService;

public class RecommendServiceImpl implements RecommendService {

	@Autowired
	RecommendMapper recommendMapper;
	
	/**
	 * 查询推荐项目名称、显示图片及图片关联的商品id
	 * @return
	 */
	public ArrayList<Recommend> recommendQuery(){
		
		ArrayList<Recommend> recommend = recommendMapper.recommendQuery();
		
		return recommend;		
	}

	/**
	 * 添加recommend上需要推荐项目名称、显示图片及图片关联的商品id
	 * @param recommend
	 */
	public void insertRecommendDetail(Recommend recommend){
		recommendMapper.insertRecommendDetail(recommend);
	}
	
	/**
	 * 更新recommend上需要推荐项目名称、显示图片及图片关联的商品id
	 * @param recommend
	 */
	public void updateRecommendDetail(Recommend recommend){
		recommendMapper.updateRecommendDetail(recommend);
	}
}
