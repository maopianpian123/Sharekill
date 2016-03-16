/**
 * 2016年2月4日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.shareskill.pojo.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author hit_lacus@126.com
 */
@Component
public class OrderRowMapper implements RowMapper<Order> {
	
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Order(
			rs.getInt("orderId"),
			rs.getInt("creator"),
			rs.getInt("provider"),
			rs.getInt("skillId"),
			rs.getInt("status"),
			rs.getString("contact"),
			rs.getString("remark"),
			new Double(rs.getString("price")),
			(long)(rs.getInt("commentTimes")),
			(long)(rs.getInt("receiveTime")),
			(long)(rs.getInt("completeTime"))
		);
	}
}
