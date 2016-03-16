/**
 * 2016年2月4日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.shareskill.dao.*;
import org.shareskill.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author hit_lacus@126.com
 */
@Component
public class CommentRowMapper implements RowMapper<Comment> {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private SkillDao skillDao;
	
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		long id = (long)rs.getInt("reviewer");
		User user = userDao.getUserById(id);
		int skillId=rs.getInt("skillId");
		Skill skill=skillDao.getSkillById(skillId);
		return new Comment(
				user,
				rs.getString("content"),
				skill,
				rs.getTimestamp("createTime").getTime(),
				(int)rs.getDouble("score")
		);
	}
}
