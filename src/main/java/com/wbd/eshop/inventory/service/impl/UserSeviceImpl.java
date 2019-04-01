package com.wbd.eshop.inventory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wbd.eshop.inventory.dao.RedisDAO;
import com.wbd.eshop.inventory.mapper.UserMapper;
import com.wbd.eshop.inventory.model.User;
import com.wbd.eshop.inventory.service.UserSevice;

@Service("userSevice")
public class UserSeviceImpl implements UserSevice {

	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisDAO redisDAO;

	@Override
	public List<User> findUserInfo() {
		return userMapper.findUserInfo();
	}

	@Override
	public List<User> getUserInfoByCache() {
	
		//模拟，从数据库中获取所有数据，然后存入缓存
		List<User> list = findUserInfo();

		String json = com.alibaba.fastjson.JSONArray.toJSONString(list);

		redisDAO.set("user:list", json);
		
		//从缓存中获取列表数据，返回list
		
		String cacheJson =  redisDAO.get("user:list");
		
		@SuppressWarnings("unchecked")
		List<User> cacheList = (List<User>) JSONObject.parse(cacheJson);
		
		return cacheList;
	}

	@Override
	public User getUserByKey(String key) {
		String json = redisDAO.get(key);
		User user = (User) JSONObject.parseObject(json, User.class);
		
		return user;
	}

}
