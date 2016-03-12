/**
 * 2016年3月5日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.shareskill.pojo.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author hit_lacus@126.com
 */
@Component
public class UserRowMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return new User(
				rs.getBoolean("sex"),
				rs.getLong("username"),
				rs.getString("realName"),
				rs.getString("nickName"),
				rs.getTimestamp("enrollTime").getTime(),
				rs.getString("location"),
				rs.getString("job"),
				null,//密码
				rs.getString("selfDesc"),
				new Double(rs.getString("rank"))		
		);
	}
}
