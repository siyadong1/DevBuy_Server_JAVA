package com.dev4free.devbuy.po_custom;

import java.util.ArrayList;

import com.dev4free.devbuy.po.Recommend;

public class RecommendCustom {

	private String category;
	
	private ArrayList<Recommend> recommend;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ArrayList<Recommend> getRecommend() {
		return recommend;
	}

	public void setRecommend(ArrayList<Recommend> recommend) {
		this.recommend = recommend;
	}

	@Override
	public String toString() {
		return "RecommendCustom [category=" + category + ", recommend=" + recommend + "]";
	}
	
	
}
