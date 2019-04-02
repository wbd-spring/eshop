package com.wbd.eshop.inventory.service;

import com.wbd.eshop.inventory.model.ProductInventory;

/**
 * 商品库存服务
 * @author jwh
 *
 */
public interface ProductInventoryService {

	
	/**
	 * 更新库存服务
	 * @param productInventory
	 */
	void updateProductInventory(ProductInventory productInventory);
	
	/**
	 * 删除redis中的库存信息
	 * @param productInventory
	 */
	void removeProductInventoryCache(ProductInventory productInventory);
	
	/**
	 * 查询库存信息
	 * @param productId
	 * @return
	 */
	ProductInventory findProductInventory(Integer productId);
	
	
	/**
	 * 设置redis中的库存信息
	 * @param productInventory
	 */
	void setProductInventoryCache(ProductInventory productInventory);
	
	
	/**
	 * 在redis查询库存信息
	 * @param productId
	 * @return
	 */
	ProductInventory getProductInventoryCache(Integer productId);
	
}
