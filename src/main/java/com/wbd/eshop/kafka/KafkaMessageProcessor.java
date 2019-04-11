package com.wbd.eshop.kafka;

import com.alibaba.fastjson.JSONObject;
import com.wbd.eshop.cache.service.ProductEhCacheService;
import com.wbd.eshop.inventory.model.ProductInfo;
import com.wbd.eshop.spring.SpringContext;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class KafkaMessageProcessor implements Runnable {


	@SuppressWarnings("rawtypes")
	private KafkaStream ks;

	private ProductEhCacheService ps;

	public KafkaMessageProcessor(KafkaStream ks) {

		this.ks = ks;

		//在启动的时候， 监听器也启动，在监听器中通过ServletContextEvent获取servletContext
		//然后通过webapplicationContextUtils.getWebApplicationContext(servletContext)获取到ApplicationContext
		//
		this.ps = (ProductEhCacheService) SpringContext.getAc().getBean("productEhCacheService");
	}

	@Override
	public void run() {
		
		ConsumerIterator<byte[], byte[]> it = ks.iterator();
		  while (it.hasNext()) {
			  
			  System.out.println("处理信息启动.....");
	        	String message = new String(it.next().message());
	        	
	        	// 首先将message转换成json对象
	        	JSONObject messageJSONObject = JSONObject.parseObject(message);
	        	
	        	// 从这里提取出消息对应的服务的标识
	        	String serviceId = messageJSONObject.getString("serviceId");  
	        	
	        	// 如果是商品信息服务
	        	if("productInfoService".equals(serviceId)) {
	        		processProductInfoChangeMessage(messageJSONObject);
	        	} else if("shopInfoService".equals(serviceId)) {
	        		processShopInfoChangeMessage(messageJSONObject);  
	        	}
	        }

	}

	private void processShopInfoChangeMessage(JSONObject messageJSONObject) {
		
	}

	private void processProductInfoChangeMessage(JSONObject messageJSONObject) {
		String productInfoJSON = "{\"id\": 1, \"name\": \"iphone7手机\", \"price\": 5599, \"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1}";
		ProductInfo productInfo = JSONObject.parseObject(productInfoJSON, ProductInfo.class);
		
		ps.saveLocalCache(productInfo);
		
		System.out.println(ps.getLocalCache(1l).getName()+"----手机名称本地缓存");
	}

}
