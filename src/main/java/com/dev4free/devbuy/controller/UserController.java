package com.dev4free.devbuy.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dev4free.devbuy.po.User;
import com.dev4free.devbuy.po_custom.UserCustom;
import com.dev4free.devbuy.service.UserService;

@Controller
@RequestMapping(value="/java",method=RequestMethod.POST)
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/findUser")
	public ModelAndView findUser (Integer integer) {
		User user = userService.findUserById(1);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("success");
		return modelAndView;
	}
	
	
	
	
	@RequestMapping(value="/register")
	public void register (User user) {
		userService.insertUser(user);
	}
	
	
}
