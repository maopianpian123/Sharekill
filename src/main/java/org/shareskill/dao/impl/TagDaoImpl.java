/**
 * 2016年2月2日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.shareskill.dao.TagDao;
import org.shareskill.dao.mapper.TagRowMapper;
import org.shareskill.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author hit_lacus@126.com
 */
@Repository
public class TagDaoImpl implements TagDao {
	
	
	private static Logger log = Logger.getLogger(TagDaoImpl.class);
	
	RowMapper<Tag> mapper = new TagRowMapper();
	
	@Autowired
	private JdbcOperations jdbcOperations;
	
	
	

	/* (non-Javadoc)
	 * @see org.shareskill.dao.TagDao#getAllTag()
	 */
	@Override
	public List<Tag> getAllTag() {
		List<Tag> tags = jdbcOperations.query("select * from Tag", mapper);
		log.info("Find " + tags.size() + " tags.");
		return tags;
	}

	//插入新的标签
	@Override
	public void insert(String name,String creator)throws Exception{
		 String sql="insert into tag(tagName,creator,createTime) values(?,?,?)";  
		 Date creattime = new Date(System.currentTimeMillis());
		 Object obj[]={name,creator,creattime};  
	        try{
		    this.jdbcOperations.update(sql,obj);
			log.info("tag " + name + " insert success.");
	    	} catch (Exception e) {
	    		e.printStackTrace();
			}
	
	}
	
	@Override
	public void delete(String name)throws Exception{
		 String sql="delete from tag where tagName="+name;  
		  try{
	        this.jdbcOperations.update(sql);  
	        log.info("tag " + name+ " delete success.");
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}
