package com.wbd.eshop.inventory.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wbd.eshop.inventory.dao.RedisDAO;

import redis.clients.jedis.JedisCluster;

@Repository("redisDAO")
public class RedisDAOImpl implements RedisDAO {
	
	@Autowired
	private JedisCluster jedisCluster;
	

	@Override
	public void set(String key, String value) {

		jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public void delete(String key) {
		jedisCluster.del(key);
		
	}

}
