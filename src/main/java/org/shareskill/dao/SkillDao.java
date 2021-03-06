/**
 * 2016年2月4日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao;

import java.util.List;

import org.shareskill.pojo.*;
import org.springframework.cache.annotation.Cacheable;
/**
 * @author hit_lacus@126.com
 */
public interface SkillDao {
	
	@Cacheable("skillList")
	
	 Skill getSkillById(int id);

	 List<Skill> getAllSkill();
	
	 List<Skill> getAllSkillbyId(long id);
	 
	 List<Skill> searchSkill(int start,int count,String tag,String keyword);
	
	 public void insert(Skill skill,long username)throws Exception;
	 
	 public void delete(int id)throws Exception ;
	 
	 public void update(Skill skill)throws Exception;
}
