package com.wbd.eshop.cache.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wbd.eshop.cache.service.ProductEhCacheService;
import com.wbd.eshop.inventory.model.ProductInfo;

@RestController
public class ProductionController {

	@Resource
	private ProductEhCacheService productEhCacheService;
	
	@RequestMapping("/testPutCache")
	public String testPutCache(ProductInfo productInfo) {
		productEhCacheService.saveLocalCache(productInfo);
		return "success";
	}
	
	@RequestMapping("/testGetCache")
	public ProductInfo testGetCache(Long id) {
		return productEhCacheService.getLocalCache(id);
	}
}
