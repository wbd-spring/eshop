package com.wbd.eshop.inventory.thread;

/**
 * 具体的请求接口
 * @author jwh
 *
 */
public interface Request {
	void process();
	Integer getProductId();
	boolean isForceRefresh();
}
