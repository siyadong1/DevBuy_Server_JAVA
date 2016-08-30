package com.dev4free.devbuy.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.BannerMapper;
import com.dev4free.devbuy.po.Banner;
import com.dev4free.devbuy.service.BannerService;

public class BannerServiceImpl implements BannerService {

	@Autowired
	BannerMapper bannerMapper;
	
	
	
	/**
	 * 查询banner上面需要显示的图片
	 * @return
	 */
	public ArrayList<Banner> bannerQuery(){
		
		ArrayList<Banner> banner = bannerMapper.bannerQuery();
		return banner;
	}

	/**
	 * 添加banner上需要显示的图片的详情
	 * @param banner
	 */
	public void insertBannerDetail(Banner banner){
		
		bannerMapper.insertBannerDetail(banner);
	}
	
	/**
	 * 更新banner上需要显示的图片的详情
	 * @param banner
	 */
	public void updateBannerDetail(Banner banner){
		bannerMapper.updateBannerDetail(banner);
	}
}
