package com.wbd.eshop.inventory.dao;

/**
 * 只做基本操作
 * @author jwh
 *
 */
public interface RedisDAO {

	public void set(String key,String value);
	
	public String get(String key);
	
	public void delete(String key);
}
