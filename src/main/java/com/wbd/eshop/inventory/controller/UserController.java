package com.wbd.eshop.inventory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbd.eshop.inventory.model.User;
import com.wbd.eshop.inventory.service.UserSevice;

@RestController
public class UserController {
	@Autowired
	private UserSevice  userSevice;
	@RequestMapping("/getUserAll")
	public List<User> getUserAll() {
		return userSevice.findUserInfo();
	}
	
	@RequestMapping("/getCacheUserAll")
	public List<User> getCacheUserAll() {
		return userSevice.getUserInfoByCache();
	}
	
	
	@RequestMapping("/getCacheByKey/{key}")
	public User getCacheByKey(@PathVariable("key") String key) {
		return userSevice.getUserByKey(key);
	}
	
	
}
