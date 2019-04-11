package com.wbd.eshop.cache.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wbd.eshop.cache.service.ProductEhCacheService;
import com.wbd.eshop.inventory.model.ProductInfo;
@Service("productEhCacheService")
public class ProductEhCacheServiceImpl implements ProductEhCacheService {

	public static final String CACHE_NAME = "local";
	
	@Override
	@CachePut(value=CACHE_NAME,key="'key_'+#productInfo.getId()")
	public ProductInfo saveLocalCache(ProductInfo productInfo) {
	
		return productInfo;
	}

	@Override
	@Cacheable(value=CACHE_NAME,key="'key_'+#id")
	public ProductInfo getLocalCache(Long id) {
		return null;
	}

}
