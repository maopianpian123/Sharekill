/**
 * 2016年2月4日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.shareskill.dao.SkillDao;

import org.shareskill.pojo.Skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author hit_lacus@126.com
 */
@Repository
public class SkillDaoImpl implements SkillDao {
	
	private static Logger log = Logger.getLogger(SkillDaoImpl.class);
	
	@Autowired
	private JdbcOperations jdbcOperations;
	
	@Autowired
	RowMapper<Skill> skillRowMapper; 

	
	@Override
	@Cacheable("skillList")
	public List<Skill> getAllSkill() {
		
		List<Skill> skills = jdbcOperations.query("select * from Skill ", skillRowMapper);
		log.info("Not using cache.Found " + skills.size() + " skills at all.");
		return skills;
	}
	
	

}
