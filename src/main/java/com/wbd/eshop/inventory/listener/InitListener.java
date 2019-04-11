package com.wbd.eshop.inventory.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wbd.eshop.kafka.KafkaConsumer;
import com.wbd.eshop.spring.SpringContext;

/**
 * 系统初始化监听器
 * 1.通过监听获取上下文，对web上下文进行设置
 * 2.消费者进行监听， 监听生产者发的消息
 * @author jwh
 *
 */
@WebListener //表示为监听器
public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		//通过WebApplicactionContextUtils获取ApplicationContext
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		//设置spring的上下文
		SpringContext.setAc(ac);
		
		
		new Thread(new KafkaConsumer("eshop-cache-message")).start();
		
		System.out.println("initListener.....启动....");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
