package com.dev4free.devbuy.serviceImp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev4free.devbuy.mapper.CityMapper;
import com.dev4free.devbuy.po.City;
import com.dev4free.devbuy.service.CityService;

public class CityServiceImpl implements CityService{

	@Autowired
	CityMapper cityMapper;
	
	/**
	 * 查询城市名称
	 * 
	 */
	public ArrayList<City> modifycityname() {
		
		ArrayList<City> city = cityMapper.modifycityname();
		
		return city;
	}


}
