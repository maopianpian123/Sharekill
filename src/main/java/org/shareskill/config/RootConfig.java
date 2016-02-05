/**
 * 2016年1月18日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author hit_lacus@126.com
 */
@Configuration
@EnableCaching
@ComponentScan(basePackages = { "org.shareskill" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class RootConfig {

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}

	@Profile("test")
	@Bean
	//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public DataSource dataSourceDev() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:schema.sql")
				.addScript("classpath:insert-data.sql")
				.build();
	}
	
	/**
	 * 生产环境下的数据源*/
	//@Profile("production")
	@Bean
	public DataSource dataSourcePro() {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			System.exit(0);
			e.printStackTrace();
		}
		ds.setJdbcUrl("jdbc:mysql://localhost/share");
		ds.setUser("root");
		ds.setPassword("");
		ds.setInitialPoolSize(40);
		ds.setMaxPoolSize(200);
		return ds;
	}
	

	@Bean
	public JdbcTemplate jdbcOperation(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
