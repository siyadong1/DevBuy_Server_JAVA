package com.dev4free.devbuy.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import com.dev4free.devbuy.po.City;
import com.dev4free.devbuy.po.User;
import com.dev4free.devbuy.service.UserService;
import com.dev4free.devbuy.utils.TextUtils;
import com.dev4free.devbuy.utils.TimeUtils;
import com.dev4free.devbuy.utils.UUIDUtils;


/**
 * 
 * @author syd
 * @date:2016年5月5日
 * @project_name:devbuy
 * @description:UserController这个类对于数据库中user这张表
 */
//@Controller采用注解的方式编写处理器控制器
@Controller
//@RequestMapping窄化请求，规定请求支持的放发。value表示请求的路径，method表示请求的方法，移动端项目仅支持post。测试的时候可以暂时采用get方便调试
@RequestMapping(value="/java/",method={RequestMethod.POST,RequestMethod.GET})
public class UserController {

	
	//LOGGER用于打印日志，一般在调试的时候打印DEBUG级别的日志
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	//@Autowired采用注解的方式注入userService到spring的IOC容器中
	@Autowired
	UserService userService;
	
	
	
	
	/**
	 * ***********************************************************************************
	 */
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	//@RequestMapping定义请求的路径。
	//由于在web.xml中定义了servlet-mapping中<url-pattern>*.action</url-pattern>
	//因此该方法对于的实际访问路径为http://www.dev4free.com/java/findUserByName.action
	@RequestMapping(value="/findUserByName")
	//@ResponseBody表示将ResponseMessage这个java对象转换成Json格式
	//该项目均采用Json格式进行数据交互
	private @ResponseBody ResponseMessage findUserByName (String username) {
		
		
		
		//返回给移动端的数据统一用ResponseMessage。
		ResponseMessage responseMessage = new ResponseMessage();
		
		//对传入的参数进行校验
		if (TextUtils.isEmpty(username)) {
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//查询数据库，进行业务层的操作
		User user = userService.findUserByUsername(username);
		
		//对返回的结果进行校验
		if (user == null) {
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		//数据交互Json嵌套的方式对responseMessage进行数据填充。
		responseMessage.setContent(JSON.toJSON(user));
		
		//将数据返回给移动端
		return responseMessage;
	}
	
	
	
	
	
	
	
	
	/**
	 * ***********************************************************************************
	 */
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register")
	private @ResponseBody ResponseMessage register (User user) {
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//输入参数非空校验
		if (user == null ||  TextUtils.isEmpty(user.getUsername()) || TextUtils.isEmpty(user.getPassword())) {
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		
		String username = user.getUsername();
		
		User userExist= userService.findUserByUsername(username);
		
		if (userExist != null) {
			responseMessage.setCode(ConstantResponse.CODE_USER_EXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_EXISTS);
			return responseMessage;
		}
		
		user.setId(UUIDUtils.getId());
		user.setRegtime(TimeUtils.getNow());
		userService.insertUser(user);
		
		return responseMessage;
	}
	
	
	
	
	
	
	/**
	 * ***********************************************************************************
	 */
	
	/**
	 * 用户登录
	 * @param httpSession
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/login")
	private @ResponseBody ResponseMessage login(HttpSession httpSession,User user){
		ResponseMessage responseMessage = new ResponseMessage();
		
		//输入参数非空校验
		if (user == null || TextUtils.isEmpty(user.getUsername()) || TextUtils.isEmpty(user.getPassword())) {
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		
		//查询用户是否存在及密码是否正确
		User user2 = userService.findUserByUsername(user.getUsername());
		if (user2 == null) {
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}else if (!user.getPassword().equals(user2.getPassword())) {
			responseMessage.setCode(ConstantResponse.CODE_USER_PASSWORD_ERROR);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_PASSWORD_ERROR);
			return responseMessage;
		}
		

		httpSession.setAttribute("id", user2.getId());
		return responseMessage;
		
	}
	
	
	
	
	
	
	/**
	 * ***********************************************************************************
	 */
	/**
	 * 重置登录密码
	 * @param user
	 * @param newpassword
	 * @param confirmpassword
	 * @return
	 */
	@RequestMapping(value = "/forgetpassword")
	private @ResponseBody ResponseMessage forgetpassword(User user,String newpassword,String confirmpassword){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//输入参数非空校验
		if (user == null || TextUtils.isEmpty(user.getUsername()) || 
				TextUtils.isEmpty(newpassword) || TextUtils.isEmpty(confirmpassword)) {
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//新密码和确认密码是否相同
		if (!newpassword.equals(confirmpassword)) {
			responseMessage.setCode(ConstantResponse.CODE_USER_PASSWORD_DISMATCH);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_PASSWORD_DISMATCH);
			return responseMessage;
		}
		
		//判断用户是否存在
		User user2 = userService.findUserByUsername(user.getUsername());
		if (user2 == null) {
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		
		
		//更新用户
		user2.setPassword(newpassword);
		userService.updateUser(user2);
		
		
		
		return responseMessage;
		
		
	}
	
	
	
	
	
	
	/**
	 * ***********************************************************************************
	 */
	
	/**
	 * 修改登录密码
	 * @param user
	 * @param newpassword
	 * @param confirmpassword
	 * @return
	 */
	@RequestMapping(value = "/modifypassword")
	private @ResponseBody ResponseMessage modifypassword(User user,String newpassword,String confirmpassword){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//输入参数非空校验
		if (user == null || TextUtils.isEmpty(user.getUsername()) || TextUtils.isEmpty(user.getPassword()) || 
				TextUtils.isEmpty(newpassword) || TextUtils.isEmpty(confirmpassword)) {
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//新密码和确认密码是否相同
		if (!newpassword.equals(confirmpassword)) {
			responseMessage.setCode(ConstantResponse.CODE_USER_PASSWORD_DISMATCH);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_PASSWORD_DISMATCH);
			return responseMessage;
		}
		
		//判断用户密码输入是否正确
		User user2 = userService.findUserByUsername(user.getUsername());
		if (user2 == null) {
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}else if (!user.getPassword().equals(user2.getPassword())) {
			responseMessage.setCode(ConstantResponse.CODE_USER_PASSWORD_ERROR);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_PASSWORD_ERROR);
			return responseMessage;
		}
		
		//更新用户
		user2.setPassword(newpassword);
		userService.updateUser(user2);
		
		
		
		return responseMessage;
		
		
	}
	

	
	
	/**
	 * ***********************************************************************************
	 */
	
	/**
	 * 修改昵称
	 * @param user
	 * @param newpassword
	 * @param confirmpassword
	 * @return
	 */
	@RequestMapping(value = "/modifynickname")
	private @ResponseBody ResponseMessage modifynickname(User user,String nickname){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//输入参数非空校验
		if (user == null || TextUtils.isEmpty(user.getUsername()) || TextUtils.isEmpty(nickname)) {
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//判断用户是否存在
		User user2 = userService.findUserByUsername(user.getUsername());
		if (user2 == null) {
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		//更新用户
		user2.setNickname(nickname);
		userService.updateUser(user2);
		
		
		
		return responseMessage;
		
		
	}

	

	/**
	 * ***********************************************************************************
	 */
	
	/**
	 * 修改用户性别
	 * @param user
	 * @param gender
	 * @return
	 */
	@RequestMapping(value = "/modifygender")
	private @ResponseBody ResponseMessage modifygender(User user,String gender){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//输入参数非空校验
		if (user == null || TextUtils.isEmpty(user.getUsername()) || TextUtils.isEmpty(gender)) {
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//判断用户是否存在
		User user2 = userService.findUserByUsername(user.getUsername());
		if (user2 == null) {
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		//更新用户
		user2.setGender(gender);
		userService.updateUser(user2);
		
		return responseMessage;
		
		
	}
	
	
	

	
	
	/**
	 * ***********************************************************************************
	 */
	
	/**
	 * 修改用户头像
	 * @param user
	 * @param avatar
	 * @return
	 */
	@RequestMapping(value = "/modifyavatar")
	private @ResponseBody ResponseMessage modifyavatar(User user,MultipartFile avatarpic){
		
		ResponseMessage responseMessage = new ResponseMessage();
		
		//输入参数非空校验
		if (user == null || TextUtils.isEmpty(user.getUsername()) || avatarpic == null) {
			responseMessage.setCode(ConstantResponse.CODE_PARAMETER_EMPTY);
			responseMessage.setContent(ConstantResponse.CONTENT_PARAMETER_EMPTY);
			return responseMessage;
		}
		
		//判断用户是否存在
		User user2 = userService.findUserByUsername(user.getUsername());
		if (user2 == null) {
			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
			return responseMessage;
		}
		
		
		//上传的头像的名称
		String avatarName = avatarpic.getOriginalFilename();
		//存入数据库中头像的名称
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String avatarNewName = user.getUsername() + "-" + simpleDateFormat.format(new Date()) + avatarName.substring(avatarName.lastIndexOf("."));
		//上传头像的路径。这里将图片存储在本地服务器  /usr/local/images/avatar/
		File uploadFile = new File(Constant.IMAGE_ROOT_REAL_PATH + Constant.IMAGE_AVATAR + avatarNewName);
		
		
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		
		try {
			avatarpic.transferTo(uploadFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//更新用户
		//采用Tomcat的虚拟目录将本地"/usr/local/images/“这个路径映射到”http://www.dev4free.com/devbuy/java/images/“
		user2.setAvatar(Constant.IMAGE_ROOT_MAPPING_PATH + Constant.IMAGE_AVATAR + avatarNewName);
		userService.updateUser(user2);
		
		responseMessage.setContent(user2.getAvatar());

		return responseMessage;
		
		
	}
	
	
	
	/**
	 * ***********************************************************************************
	 */
	
	/**
	 * 查询城市名称
	 * @return
	 */
//	@RequestMapping(value = "/modifycityname")
//	private @ResponseBody ResponseMessage findCityName(){
//		ResponseMessage responseMessage = new ResponseMessage();
//		
//		//查询数据库，进行业务层的操作
//		City city = userService.modifycityname();
//
//		//对返回的结果进行校验
//		if (city == null) {
//			responseMessage.setCode(ConstantResponse.CODE_USER_NOEXISTS);
//			responseMessage.setContent(ConstantResponse.CONTENT_USER_NOEXISTS);
//			return responseMessage;
//		}
//		
//		//数据交互Json嵌套的方式对responseMessage进行数据填充。
//		responseMessage.setContent(JSON.toJSON(city));
//		
//		return responseMessage;
//	}
	
	
	
	
	
}
