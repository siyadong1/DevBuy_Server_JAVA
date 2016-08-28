package com.dev4free.devbuy.service;

import com.dev4free.devbuy.po.ConstantInfo;

public interface ConstantInfoService {

	/**
	 * 查看关于我们的信息
	 * @param info_name
	 * @return
	 */
	public ConstantInfo findConstantInfo(String info_name);
}
