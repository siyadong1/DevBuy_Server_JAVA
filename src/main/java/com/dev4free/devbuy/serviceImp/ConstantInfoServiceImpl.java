package com.dev4free.devbuy.serviceImp;


import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.ConstantInfoMapper;
import com.dev4free.devbuy.po.ConstantInfo;
import com.dev4free.devbuy.service.ConstantInfoService;

public class ConstantInfoServiceImpl implements ConstantInfoService {

	@Autowired
	ConstantInfoMapper constantInfoMapper;
	
	/**
	 * 查看关于我们的信息
	 * @param info_name
	 * @return
	 */
	public ConstantInfo findConstantInfo(String info_name){
		
		ConstantInfo constantInfo = constantInfoMapper.findConstantInfo(info_name);
		return constantInfo;
	}

}
