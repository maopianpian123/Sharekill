/**
 * 2016年2月4日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.shareskill.dao.SkillDao;
import org.shareskill.dao.mapper.SkillRowMapper;
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
	
	public List<Skill> getAllSkill() {
		
		List<Skill> skills = jdbcOperations.query("select * from Skill ", skillRowMapper);
		log.info("Not using cache.Found " + skills.size() + " skills at all.");
		
		
		return skills;
	}


	/* 
	 */
    @Override
    public List<Skill> searchSkill(int start, int count, String tag,String keyword) {
    	
    	String sql = null;
    	
    	//select * from Skill,SkillTag,Tag where Skill.skillId=SkillTag.skillId and Tag.tagName=SkillTag.tag and SkillTag.tag = "旅游" 
    	//and ( skillName like "%一日游%" or skillDesc like "%一日游%") limit 1,2;
    	
    	//select * from Skill where skillName like "%一日游%" or skillDesc like "%一日游%" limit 1,2;
    	if(tag == null){
    		StringBuffer sb = new StringBuffer("SELECT * FROM Skill,SkillTag,Tag where ");
    		sb.append("Skill.skillId = SkillTag.skillId ");
    		sb.append("and Tag.tagName=SkillTag.tag ");
    		sb.append("and SkillTag.tag = \"");
    		sb.append(tag);
    		sb.append("\" ");
    		sb.append("and ( skillName like \"%");
    		sb.append(keyword);
    		sb.append("%\"");
    		sb.append(" or skillDesc like \"%");
    		sb.append(keyword);
    		sb.append(") limit ");
    		sb.append(start);
    		sb.append(",");
    		sb.append(count);
    		sb.append(";");	
    		
    		sql = sb.toString();
    	}else{
    		StringBuffer sb = new StringBuffer("SELECT * FROM Skill where ");
    		//sb.append("Skill.skillId = SkillTag.skillId ");
    		//sb.append("and Tag.tagName=SkillTag.tag ");
    		//sb.append("and SkillTag.tag = \"");
    		//sb.append(tag);
    		//sb.append("\" ");
    		sb.append("skillName like \"%");
    		sb.append(keyword);
    		sb.append("%\"");
    		sb.append(" or skillDesc like \"%");
    		sb.append(keyword);
    		sb.append("%\"");
    		sb.append(" limit ");
    		sb.append(start);
    		sb.append(",");
    		sb.append(count);
    		sb.append(";");
    		
    		sql = sb.toString();
    	}
    	System.out.println("SQL = " + sql);
    	List<Skill> skills = jdbcOperations.query(sql, skillRowMapper);
	    return skills;
    }


	/**
	 * @return the jdbcOperations
	 */
	public JdbcOperations getJdbcOperations() {
		return jdbcOperations;
	}


	/**
	 * @param jdbcOperations the jdbcOperations to set
	 */
	public void setJdbcOperations(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}


	/**
	 * @return the skillRowMapper
	 */
	public RowMapper<Skill> getSkillRowMapper() {
		return skillRowMapper;
	}


	/**
	 * @param skillRowMapper the skillRowMapper to set
	 */
	public void setSkillRowMapper(RowMapper<Skill> skillRowMapper) {
		this.skillRowMapper = skillRowMapper;
	}
	
	

}
