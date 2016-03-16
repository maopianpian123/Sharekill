/**
 * 2016年2月2日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao;
import java.util.List;

import org.shareskill.pojo.Tag;
import org.springframework.cache.annotation.Cacheable;
/**
 * @author hit_lacus@126.com
 */
public interface TagDao {
	@Cacheable("tagList")
	public List<Tag> getAllTag();
    
	public void insert(String name,String creator)throws Exception;
	
	public void delete(String name)throws Exception;
}
