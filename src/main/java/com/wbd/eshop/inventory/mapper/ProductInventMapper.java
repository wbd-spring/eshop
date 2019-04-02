package com.wbd.eshop.inventory.mapper;

import org.apache.ibatis.annotations.Param;

import com.wbd.eshop.inventory.model.ProductInventory;

/**
 * 产品库存dao
 * 
 * @author jwh
 *
 */
public interface ProductInventMapper {
	/**
	 * 更新库存数量
	 * @param inventoryCnt 商品库存
	 */
	void updateProductInventory(ProductInventory productInventory);
	
	/**
	 * 根据商品id查询商品库存信息
	 * @param productId 商品id
	 * @return 商品库存信息
	 */
	ProductInventory findProductInventory(@Param("productId") Integer productId);
}
