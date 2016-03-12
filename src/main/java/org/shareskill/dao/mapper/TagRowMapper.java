/**
 * 2016年3月5日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.shareskill.pojo.Manager;
import org.shareskill.pojo.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author hit_lacus@126.com
 */
@Component
public class TagRowMapper implements RowMapper<Tag> {
	public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Tag(
			new Manager(rs.getString("creator")),
			rs.getString("tagName"),
			rs.getTimestamp("createTime").getTime()
		);
	}
}
