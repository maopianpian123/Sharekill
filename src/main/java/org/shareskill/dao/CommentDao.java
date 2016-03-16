package org.shareskill.dao;

import java.util.List;

import org.shareskill.pojo.Comment;
import org.springframework.cache.annotation.Cacheable;
public interface CommentDao {
	@Cacheable("commentList")
	public List<Comment> getAllCommentbySkill(int skillId);
	
	public void insert(long userId,int skillId,String content,double score)throws Exception;
		
	public void delete(int id)throws Exception;

}
