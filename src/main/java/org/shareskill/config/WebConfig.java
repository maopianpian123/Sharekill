/**
 * 2016年1月18日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author hit_lacus@126.com
 */


@Configuration
@EnableWebMvc
@ComponentScan("org.shareskill")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
    public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
      ContentNegotiatingViewResolver cnvr =
          new ContentNegotiatingViewResolver();
      cnvr.setContentNegotiationManager(cnm);
      return cnvr;
    }

	@Override
	public void configureDefaultServletHandling(
	        DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureContentNegotiation(
	        ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}

}
