package com.wbd.eshop;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.wbd.eshop.inventory.listener.InitThreadAndListBlockingQueueListener;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@SpringBootApplication // spring boot 启动程序
@EnableAutoConfiguration // 开启自动注入
@ComponentScan // 开启自动扫描包
@MapperScan("com.wbd.eshop.inventory.mapper") // 扫描mybatis
public class Application {

	// 读取数据源配置文件， 获取dataSource,
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return new DataSource();
	}

	// 获取SqlsessioFactory,指定读取mybatis文件

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		// 设置数据源
		ssfb.setDataSource(dataSource());
		// 指定mybaits配置文件路径
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		ssfb.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));

		return ssfb.getObject();

	}

	// 事物管理器
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public GenericObjectPoolConfig genericObjectPoolConfig() {

		GenericObjectPoolConfig gop = new GenericObjectPoolConfig();
		gop.setMaxIdle(100);
		gop.setMaxTotal(1000);
		gop.setMinIdle(8);
		gop.setMaxWaitMillis(-1);
		return gop;

	}

	@Bean
	public JedisCluster jedisClusterFactory() {
		Set<HostAndPort> set = new HashSet<HostAndPort>();
		set.add(new HostAndPort("192.168.1.81", 6379));
		set.add(new HostAndPort("192.168.1.82", 6379));
		set.add(new HostAndPort("192.168.1.83", 6379));
		set.add(new HostAndPort("192.168.1.84", 6379));
		set.add(new HostAndPort("192.168.1.85", 6379));
		set.add(new HostAndPort("192.168.1.86", 6380));
		JedisCluster jedisCluster = new JedisCluster(set, 1000, 100, 10, "123456", "cl", genericObjectPoolConfig());
		return jedisCluster;
	}

	// 注册监听器，初始化工作线程和内存队列

	@Bean
	public ServletListenerRegistrationBean servletListenerRegistrationBean() {

		ServletListenerRegistrationBean sb = new ServletListenerRegistrationBean();

		sb.setListener(new InitThreadAndListBlockingQueueListener());
		
		return sb;

	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
