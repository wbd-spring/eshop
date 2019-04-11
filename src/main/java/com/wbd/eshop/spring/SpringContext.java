package com.wbd.eshop.spring;

import org.springframework.context.ApplicationContext;

/**
 * spring上下文
 * @author jwh
 *
 */
public class SpringContext {

	private static ApplicationContext ac;

	public static ApplicationContext getAc() {
		return ac;
	}

	public static void setAc(ApplicationContext ac) {
		SpringContext.ac = ac;
	}
	
	
}
