package com.dev4free.devbuy.mapper;

import com.dev4free.devbuy.po.ConstantInfo;

public interface ConstantInfoMapper {

	/**
	 * 查看关于我们的信息
	 * @param info_name
	 * @return
	 */
	public ConstantInfo findConstantInfo(String info_name);
}
