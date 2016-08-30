package com.dev4free.devbuy.po_custom;

import java.util.ArrayList;

import com.dev4free.devbuy.po.Banner;
import com.dev4free.devbuy.po.Classification;
import com.dev4free.devbuy.po.Recommend;


/**
 * APP初始化时的返回对象
 * @author lzw
 *
 */
public class InitialPagesCustom {

	//APP初始化时返回下面三部分内容
	private ArrayList<Banner> banner;
	private ArrayList<Classification> classification;
	private ArrayList<Recommend> recommend;
	
	public ArrayList<Banner> getBanner() {
		return banner;
	}
	public void setBanner(ArrayList<Banner> banner) {
		this.banner = banner;
	}
	public ArrayList<Classification> getClassification() {
		return classification;
	}
	public void setClassification(ArrayList<Classification> classification) {
		this.classification = classification;
	}
	public ArrayList<Recommend> getRecommend() {
		return recommend;
	}
	public void setRecommend(ArrayList<Recommend> recommend) {
		this.recommend = recommend;
	}
	@Override
	public String toString() {
		return "InitialPagesCustom [banner=" + banner + ", classification=" + classification + ", recommend="
				+ recommend + "]";
	}
	
	
	
	
}
