/**
 * 2016年2月4日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.shareskill.dao.UserDao;
import org.shareskill.pojo.Skill;
import org.shareskill.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author hit_lacus@126.com
 */
@Component
public class SkillRowMapper implements RowMapper<Skill> {
	
	@Autowired
	private UserDao userDao;

	public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
		long id = rs.getLong("publisher");
		System.out.println(" *** " + userDao);
		User user = userDao.getUserById(id);
		
		return new Skill(
				rs.getInt("skillId"),
				rs.getString("skillName"),
				rs.getString("skillDesc"),
				rs.getTimestamp("createTime").getTime(),
				rs.getInt("commentTimes"),
				rs.getInt("successTimes"),
				rs.getString("contact"),
				new Double(rs.getString("price")),
				new Double(rs.getString("score")),
				user
		);
	}
}
