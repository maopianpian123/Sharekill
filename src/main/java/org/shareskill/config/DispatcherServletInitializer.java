/**
 * 2016年1月18日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *  用于配置DispatcherServlet,具体信息参看文档
 * @author hit_lacus@126.com
 */
public class DispatcherServletInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 配置像数据源,持久化层组件一类的Bean
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class<?>[]{RootConfig.class};
	}

	/**
	 *  配置像控制器,视图解析器一类的Bean	 
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[]{WebConfig.class};
	}

	/**
	 * 指定那些请求会被DispatcherServlet所处理
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
