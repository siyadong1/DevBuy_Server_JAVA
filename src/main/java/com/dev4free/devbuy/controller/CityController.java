package com.dev4free.devbuy.controller;


import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dev4free.devbuy.constant.ConstantResponse;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.City;
import com.dev4free.devbuy.service.CityService;


/**
 * 
 * @author lzw
 * @date:2016年8月8日
 * @project_name:devbuy
 * @description:CityController这个类对于数据库中city这张表
 */
//@Controller采用注解的方式编写处理器控制器
@Controller
//@RequestMapping窄化请求，规定请求支持的放发。value表示请求的路径，method表示请求的方法，移动端项目仅支持post。测试的时候可以暂时采用get方便调试
@RequestMapping(value="/java/",method={RequestMethod.POST,RequestMethod.GET})
public class CityController {

	
	//LOGGER用于打印日志，一般在调试的时候打印DEBUG级别的日志
	private static final Logger LOGGER = Logger.getLogger(CityController.class);
	
	//@Autowired采用注解的方式注入userService到spring的IOC容器中
	@Autowired
	CityService cityService;
	
	/**
	 * 查询城市名称
	 * @return
	 */
	@RequestMapping(value = "/modifycityname")
	private @ResponseBody ResponseMessage modifycityname(){
		ResponseMessage responseMessage = new ResponseMessage();
		
		
		//查询数据库，进行业务层的操作
		ArrayList<City> city = cityService.modifycityname();

		//对返回的结果进行校验
		if (city == null) {
			responseMessage.setCode(ConstantResponse.CODE_CITY_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_CITY_NOEXISTS);
			return responseMessage;
		}
		
		//数据交互Json嵌套的方式对responseMessage进行数据填充。
		responseMessage.setContent(JSON.toJSON(city));
		
		return responseMessage;
	}
	
	
	
	
	
}
