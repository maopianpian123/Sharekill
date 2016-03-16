package org.shareskill.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.shareskill.dao.CommentDao;
import org.shareskill.dao.mapper.CommentRowMapper;
import org.shareskill.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class CommmetDaoImpl implements CommentDao{
private static Logger log = Logger.getLogger(CommmetDaoImpl.class);
	
    CommentRowMapper mapper = new CommentRowMapper();
	
	@Autowired
	private JdbcOperations jdbcOperations;
	
	@Override
	//查询指定技能的所有评论
	public List<Comment> getAllCommentbySkill(int skillId){
		List<Comment> comments = jdbcOperations.query("select * from comment where skillId=?",new Object[]{skillId}, mapper);
		log.info("Find " + comments.size() + " comments.");
		return comments;
	}
	
	//插入新的评论
	@Override
		public void insert(long userId,int skillId,String content,double score)throws Exception{
			 String sql="insert into comment(reviewer,skillId,content,creattime,score) values(?,?,?,?,?)";  
			 Date creattime = new Date(System.currentTimeMillis());
			 Object obj[]={userId,skillId,content,creattime,score};  
		        try{
			    this.jdbcOperations.update(sql,obj);
				log.info(userId+ " insert comment success.");
		    	} catch (Exception e) {
		    		e.printStackTrace();
				}
		
		}
		
	@Override
		public void delete(int id)throws Exception{
			 String sql="delete from comment where commentId="+id;  
			  try{
		        this.jdbcOperations.update(sql);  
		        log.info("comment " + id+ " delete success.");
			  } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
}
