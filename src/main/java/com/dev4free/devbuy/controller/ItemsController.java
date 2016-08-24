package com.dev4free.devbuy.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev4free.devbuy.constant.Constant;
import com.dev4free.devbuy.constant.ConstantResponse;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.Items;
import com.dev4free.devbuy.service.ItemsService;
import com.dev4free.devbuy.utils.UUIDUtils;

/**
 * 
 * @author lzw
 * @date:2016年8月21日
 * @project_name:devbuy
 * @description:ItemsController这个类对于数据库中items这张表
 */
@Controller
@RequestMapping(value="/java/",method={RequestMethod.GET,RequestMethod.POST})
public class ItemsController {

	//LOGGER用于打印日志，一般在调试的时候打印DEBUG级别的日志
	private static final Logger LOGGER = Logger.getLogger(CityController.class);
	
	@Autowired
	ItemsService itemsService;
	
	
	/**
	 * 在数据表items中插入商品详情，包括上传商品图片
	 * @param items
	 * @param itemspic
	 * @return
	 */
	@RequestMapping(value="/insertItems")
	public @ResponseBody ResponseMessage insertItems(Items items,MultipartFile itemspic){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		if(items == null || itemspic == null){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		String originalFileName = itemspic.getOriginalFilename();
		
		//上传图片
		if(itemspic!=null&&originalFileName!=null&&originalFileName.length()>0){
			 
			
			//存入数据库的图片名称
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String itemspicNewName = simpleDateFormat.format(new Date())+originalFileName.substring(originalFileName.lastIndexOf("."));
			
			//上传商品图片的路径。这里将图片存储在本地服务器  /user/local/images/itemsPicture/
			File uploadFile = new File(Constant.IMAGE_ROOT_REAL_PATH + Constant.IMAGE_ITEMS+ itemspicNewName);
			
			if (!uploadFile.exists()) {
				uploadFile.mkdirs();
			}
			
			try {
				itemspic.transferTo(uploadFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//设置数据表中显示的图片名称(url)
			items.setImage(Constant.IMAGE_ROOT_MAPPING_PATH + Constant.IMAGE_ITEMS + itemspicNewName);
		}
		
		items.setItems_id(UUIDUtils.getId());
		itemsService.insertItems(items);
		
		return responseMessage;
		
	}
	
	
	
	
}
