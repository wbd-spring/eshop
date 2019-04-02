package com.wbd.eshop.inventory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wbd.eshop.inventory.dao.RedisDAO;
import com.wbd.eshop.inventory.mapper.ProductInventMapper;
import com.wbd.eshop.inventory.model.ProductInventory;
import com.wbd.eshop.inventory.service.ProductInventoryService;
@Service("productInventoryService")
public class ProductInventoryServiceImpl implements ProductInventoryService {

	@Autowired
	private RedisDAO redisDAO;
	
	@Autowired
	private ProductInventMapper productInventMapper;
	
	
	@Override
	public void updateProductInventory(ProductInventory productInventory) {

		productInventMapper.updateProductInventory(productInventory);
		System.out.println("===========日志===========: 已修改数据库中的库存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());
	}

	@Override
	public void removeProductInventoryCache(ProductInventory productInventory) {
		String key = "product:inventory:" + productInventory.getProductId();
		redisDAO.delete(key);
		System.out.println("===========日志===========: 已删除redis中的缓存，key=" + key); 
	}

	@Override
	public ProductInventory findProductInventory(Integer productId) {
		return productInventMapper.findProductInventory(productId);
	}

	@Override
	public void setProductInventoryCache(ProductInventory productInventory) {

		String key = "product:inventory:" + productInventory.getProductId();
		redisDAO.set(key, String.valueOf(productInventory.getInventoryCnt()));
		System.out.println("===========日志===========: 已更新商品库存的缓存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt() + ", key=" + key);  
	}

	@Override
	public ProductInventory getProductInventoryCache(Integer productId) {
	Long inventoryCnt = 0L;
		String key = "product:inventory:" + productId;
		String result = redisDAO.get(key);
		
		if(result != null && !"".equals(result)) {
			try {
				inventoryCnt = Long.valueOf(result);
				return new ProductInventory(productId, inventoryCnt);
			} catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		
		return null;
	}

}
