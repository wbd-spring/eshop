package com.wbd.eshop.cache.service;

import com.wbd.eshop.inventory.model.ProductInfo;

public interface ProductEhCacheService {

	
	/**
	 * 将商品信息 保存到本地缓存Ehcache中
	 * 
	 */
	 ProductInfo saveLocalCache(ProductInfo productInfo);
	 
	 
	 /**
	  * 从本地缓存获取 商品信息
	  * @param id
	  * @return
	  */
	 ProductInfo getLocalCache(Long id);
}
