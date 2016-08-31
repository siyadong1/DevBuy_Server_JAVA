package com.dev4free.devbuy.mapper;

import java.util.ArrayList;

import com.dev4free.devbuy.po.Recommend;

public interface RecommendMapper {

	/**
	 * 查询推荐项目名称、显示图片及图片关联的商品id
	 * @return
	 */
	public ArrayList<Recommend> recommendQueryByRcCategory(String rc_category);
	
	/**
	 * 添加recommend上需要推荐项目名称、显示图片及图片关联的商品id
	 * @param recommend
	 */
	public void insertRecommendDetail(Recommend recommend);
	
	/**
	 * 更新recommend上需要推荐项目名称、显示图片及图片关联的商品id
	 * @param recommend
	 */
	public void updateRecommendDetail(Recommend recommend);
	
	/**
	 * 查询recommend表中所有的推荐项目名称
	 * @return
	 */
	public ArrayList<String> recommendCategoryQuery();
}
