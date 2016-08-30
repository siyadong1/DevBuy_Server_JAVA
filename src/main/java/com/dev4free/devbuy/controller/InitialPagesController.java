package com.dev4free.devbuy.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.dev4free.devbuy.constant.Constant;
import com.dev4free.devbuy.constant.ConstantResponse;
import com.dev4free.devbuy.entity.ResponseMessage;
import com.dev4free.devbuy.po.Banner;
import com.dev4free.devbuy.po.Classification;
import com.dev4free.devbuy.po.Items;
import com.dev4free.devbuy.po.Recommend;
import com.dev4free.devbuy.po_custom.InitialPagesCustom;
import com.dev4free.devbuy.service.BannerService;
import com.dev4free.devbuy.service.ClassificationService;
import com.dev4free.devbuy.service.ItemsService;
import com.dev4free.devbuy.service.RecommendService;
import com.dev4free.devbuy.utils.TextUtils;
import com.dev4free.devbuy.utils.UUIDUtils;

@Controller
@RequestMapping(value="/java/",method={RequestMethod.GET,RequestMethod.POST})
public class InitialPagesController {

	@Autowired
	BannerService bannerService;
	
	@Autowired
	ClassificationService classificationService;
	
	@Autowired
	RecommendService recommendService;
	
	@Autowired
	ItemsService itemsService;
	
	/**
	 * APP初始化模块
	 * @return
	 */
	@RequestMapping(value="/initialPages")
	public @ResponseBody ResponseMessage initialPages(){

		ResponseMessage responseMessage = new ResponseMessage();
		
		InitialPagesCustom initialPagesCustom= new InitialPagesCustom(); //返回对象
		
		//模块一：返回banner显示的图片及关联商品的id
		
		ArrayList<Banner> banner = bannerService.bannerQuery();
		
		
		//模块二：返回导航分类图片及关联商品类别
		
		ArrayList<Classification> classification = classificationService.classificationQuery();
		
		
		//模块三：返回推荐项目名称、显示图片及图片关联的商品id
		
		ArrayList<Recommend> recommend = recommendService.recommendQuery();
		
		initialPagesCustom.setBanner(banner);
		initialPagesCustom.setClassification(classification);
		initialPagesCustom.setRecommend(recommend);
		
		responseMessage.setContent(JSON.toJSON(initialPagesCustom));
		
		return responseMessage;
	}
	
	
	@RequestMapping(value="/queryItemsDetailByCategory")
	public @ResponseBody ResponseMessage queryItemsDetailByCategory(String category){

		ResponseMessage responseMessage = new ResponseMessage();
		
		if(TextUtils.isEmpty(category)){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//根据商品类别查询商品详情
		ArrayList<Items> items = itemsService.findItemsByCategory(category);
		
		responseMessage.setContent(JSON.toJSON(items));
	
		return responseMessage;
	}	
	
	/**
	 * 更新或插入banner图片信息
	 * @param banner
	 * @param bannerpic
	 * @return
	 */
	@RequestMapping(value="/updateBannerInfo")
	public @ResponseBody ResponseMessage updateBannerInfo(Banner banner,MultipartFile bannerpic){

		ResponseMessage responseMessage = new ResponseMessage();
		
		if(banner==null){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		if(TextUtils.isEmpty(banner.getBn_id())){
			//执行插入操作
			if(bannerpic.getSize()==0){
				responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
				responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
				return responseMessage;
			}
			String originalFileName = bannerpic.getOriginalFilename();
			
			//上传图片
			if(originalFileName!=null&&originalFileName.length()>0){
				 
				//存入数据库的图片名称
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				String bannerpicNewName = simpleDateFormat.format(new Date())+originalFileName.substring(originalFileName.lastIndexOf("."));
				
				//上传商品图片的路径。这里将图片存储在本地服务器  /usr/local/images/bannerPicture/
				File uploadFile = new File(Constant.IMAGE_ROOT_REAL_PATH + Constant.IMAGE_BANNER + bannerpicNewName);
				
				if (!uploadFile.exists()) {
					uploadFile.mkdirs();
				}
				
				try {
					bannerpic.transferTo(uploadFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//设置数据表中显示的图片名称(url)
				banner.setBn_url(Constant.IMAGE_ROOT_MAPPING_PATH + Constant.IMAGE_BANNER + bannerpicNewName);;
			}
			banner.setBn_id(UUIDUtils.getId());
			bannerService.insertBannerDetail(banner);

		}
		else{
			//更新操作,可能不会更新图片
			if(bannerpic.getSize()!=0){
				String originalFileName = bannerpic.getOriginalFilename();
				
				//上传图片
				if(originalFileName!=null&&originalFileName.length()>0){
					 
					//存入数据库的图片名称
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					String bannerpicNewName = simpleDateFormat.format(new Date())+originalFileName.substring(originalFileName.lastIndexOf("."));
					
					//上传商品图片的路径。这里将图片存储在本地服务器  /usr/local/images/bannerPicture/
					File uploadFile = new File(Constant.IMAGE_ROOT_REAL_PATH + Constant.IMAGE_BANNER + bannerpicNewName);
					
					if (!uploadFile.exists()) {
						uploadFile.mkdirs();
					}
					
					try {
						bannerpic.transferTo(uploadFile);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//设置数据表中显示的图片名称(url)
					banner.setBn_url(Constant.IMAGE_ROOT_MAPPING_PATH + Constant.IMAGE_BANNER + bannerpicNewName);;
				}
			}
			bannerService.updateBannerDetail(banner);
			
		}
		
		return responseMessage;
	}
	
	
	/**
	 * 更新或插入classification图片信息
	 * @param classification
	 * @param cfpic
	 * @return
	 */
	@RequestMapping(value="/updateClassificationInfo")
	public @ResponseBody ResponseMessage updateClassificationInfo(Classification classification,MultipartFile cfpic){

		ResponseMessage responseMessage = new ResponseMessage();
		
		if(classification==null){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		if(TextUtils.isEmpty(classification.getCf_id())){
			//执行插入操作
			if(cfpic.getSize()==0){
				responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
				responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
				return responseMessage;
			}
			String originalFileName = cfpic.getOriginalFilename();
			
			//上传图片
			if(originalFileName!=null&&originalFileName.length()>0){
				 
				//存入数据库的图片名称
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				String cfpicNewName = simpleDateFormat.format(new Date())+originalFileName.substring(originalFileName.lastIndexOf("."));
				
				//上传商品图片的路径。这里将图片存储在本地服务器  /usr/local/images/classificationPicture/
				File uploadFile = new File(Constant.IMAGE_ROOT_REAL_PATH + Constant.IMAGE_CLASSIFICATION + cfpicNewName);
				
				if (!uploadFile.exists()) {
					uploadFile.mkdirs();
				}
				
				try {
					cfpic.transferTo(uploadFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//设置数据表中显示的图片名称(url)
				classification.setCf_url(Constant.IMAGE_ROOT_MAPPING_PATH + Constant.IMAGE_CLASSIFICATION + cfpicNewName);
			}
			classification.setCf_id(UUIDUtils.getId());
			classificationService.insertClassificationDetail(classification);
		}
		else{
			//更新操作,可能不会更新图片
			if(cfpic.getSize()!=0){
				String originalFileName = cfpic.getOriginalFilename();
				
				//上传图片
				if(originalFileName!=null&&originalFileName.length()>0){
					 
					//存入数据库的图片名称
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					String cfpicNewName = simpleDateFormat.format(new Date())+originalFileName.substring(originalFileName.lastIndexOf("."));
					
					//上传商品图片的路径。这里将图片存储在本地服务器  /usr/local/images/classificationPicture/
					File uploadFile = new File(Constant.IMAGE_ROOT_REAL_PATH + Constant.IMAGE_CLASSIFICATION + cfpicNewName);
					
					if (!uploadFile.exists()) {
						uploadFile.mkdirs();
					}
					
					try {
						cfpic.transferTo(uploadFile);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//设置数据表中显示的图片名称(url)
					classification.setCf_url(Constant.IMAGE_ROOT_MAPPING_PATH + Constant.IMAGE_CLASSIFICATION + cfpicNewName);
				}
			}
			classificationService.updateClassificationDetail(classification);
		}
		
		return responseMessage;
	}
	
	/**
	 * 更新或插入recommend图片信息
	 * @param recommend
	 * @param rcpic
	 * @return
	 */
	@RequestMapping(value="/updateRecommendInfo")
	public @ResponseBody ResponseMessage updateRecommendInfo(Recommend recommend,MultipartFile rcpic){

		ResponseMessage responseMessage = new ResponseMessage();
		
		if(recommend==null){
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		if(TextUtils.isEmpty(recommend.getRc_id())){
			//执行插入操作
			if(rcpic.getSize()==0){
				responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
				responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
				return responseMessage;
			}
			String originalFileName = rcpic.getOriginalFilename();
			
			//上传图片
			if(originalFileName!=null&&originalFileName.length()>0){
				 
				//存入数据库的图片名称
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				String rcpicNewName = simpleDateFormat.format(new Date())+originalFileName.substring(originalFileName.lastIndexOf("."));
				
				//上传商品图片的路径。这里将图片存储在本地服务器  /usr/local/images/recommendPicture/
				File uploadFile = new File(Constant.IMAGE_ROOT_REAL_PATH + Constant.IMAGE_RECOMMEND+ rcpicNewName);
				
				if (!uploadFile.exists()) {
					uploadFile.mkdirs();
				}
				
				try {
					rcpic.transferTo(uploadFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//设置数据表中显示的图片名称(url)
				recommend.setRc_url(Constant.IMAGE_ROOT_MAPPING_PATH + Constant.IMAGE_RECOMMEND + rcpicNewName);;
			}
			recommend.setRc_id(UUIDUtils.getId());
			recommendService.insertRecommendDetail(recommend);
		}
		else{
			//更新操作,可能不会更新图片
			if(rcpic.getSize()!=0){
				String originalFileName = rcpic.getOriginalFilename();
				
				//上传图片
				if(originalFileName!=null&&originalFileName.length()>0){
					 
					//存入数据库的图片名称
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					String rcpicNewName = simpleDateFormat.format(new Date())+originalFileName.substring(originalFileName.lastIndexOf("."));
					
					//上传商品图片的路径。这里将图片存储在本地服务器  /usr/local/images/recommendPicture/
					File uploadFile = new File(Constant.IMAGE_ROOT_REAL_PATH + Constant.IMAGE_RECOMMEND + rcpicNewName);
					
					if (!uploadFile.exists()) {
						uploadFile.mkdirs();
					}
					
					try {
						rcpic.transferTo(uploadFile);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//设置数据表中显示的图片名称(url)
					recommend.setRc_url(Constant.IMAGE_ROOT_MAPPING_PATH + Constant.IMAGE_RECOMMEND + rcpicNewName);
				}
			}
			recommendService.updateRecommendDetail(recommend);
		}
		
		return responseMessage;
	}
}
