package com.wbd.eshop.inventory.service;

import java.util.List;

import com.wbd.eshop.inventory.model.User;

/**
 * 用户业务接口
 * @author jwh
 *
 */
public interface UserSevice {
	/**
	 * 获取用户信息
	 * @return
	 */
	public List<User> findUserInfo();
	
	/**
	 * 从redis中获取用户信息
	 * @return
	 */
	public List<User> getUserInfoByCache();
	
	/**
	 * 通过key获取value
	 * @param key
	 * @return
	 */
	public User getUserByKey(String key);
}
