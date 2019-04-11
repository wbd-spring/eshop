package com.wbd.eshop.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * ehcache缓存配置管理类
 * @author jwh
 *
 */
@Configuration // 开启配置， 表示一个spring*.xml文件， 会自动注入到spring.xml(application)文件中
@EnableCaching  //开启缓存
public class CacheConfiguration {
	
	
	/**
	 * 缓存factory
	 * @return
	 */
	@Bean
	public EhCacheManagerFactoryBean  ehcacheManagerFactoryBean() {
		
		EhCacheManagerFactoryBean emfb = new EhCacheManagerFactoryBean();
		emfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		emfb.setShared(true);
		return emfb;
		
	}
	
	/**
	 * 缓存管理， 让spring来管理
	 * @param ehCacheManagerFactoryBean
	 * @return
	 */
	@Bean
	public EhCacheCacheManager ehCacheManager(EhCacheManagerFactoryBean ehCacheManagerFactoryBean) {
	
		return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject());
	}

}
