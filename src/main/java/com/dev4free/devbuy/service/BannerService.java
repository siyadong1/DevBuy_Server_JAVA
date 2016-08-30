package com.dev4free.devbuy.service;

import java.util.ArrayList;

import com.dev4free.devbuy.po.Banner;

public interface BannerService {

	/**
	 * 查询banner上面需要显示的图片
	 * @return
	 */
	public ArrayList<Banner> bannerQuery();
	
	/**
	 * 添加banner上需要显示的图片的详情
	 * @param banner
	 */
	public void insertBannerDetail(Banner banner);
	
	/**
	 * 更新banner上需要显示的图片的详情
	 * @param banner
	 */
	public void updateBannerDetail(Banner banner);
}
