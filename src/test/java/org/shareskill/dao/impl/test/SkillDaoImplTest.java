/**
 * 2016年3月5日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.impl.test;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shareskill.config.RootConfig;
import org.shareskill.dao.SkillDao;
import org.shareskill.dao.impl.SkillDaoImpl;
import org.shareskill.dao.mapper.SkillRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author hit_lacus@126.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class})
@ActiveProfiles("production")
public class SkillDaoImplTest {
	
	@Autowired
    private DataSource dataSource;
	
	@Test
	public void test(){
		
		
		SkillDaoImpl dao = new SkillDaoImpl();
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
    	dao.setJdbcOperations(jdbc);
    	dao.setSkillRowMapper(new SkillRowMapper());
		
		
		List list1 = dao.searchSkill(0, 2, "旅游", "一日游");
		assertEquals(1,list1.size());
		
	}

}
