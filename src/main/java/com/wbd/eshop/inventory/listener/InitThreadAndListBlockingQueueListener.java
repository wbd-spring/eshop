package com.wbd.eshop.inventory.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wbd.eshop.inventory.thread.RequestProcessorThreadPool;

/**
 * 监听器， 负责初始化工作线程和内存队列
 * 
 * @author jwh
 *
 */
public class InitThreadAndListBlockingQueueListener implements ServletContextListener{

	//初始化方法
	@Override
	public void contextInitialized(ServletContextEvent sce) {
	
		
	//初始化工作线程和内存队列
		
		RequestProcessorThreadPool.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
