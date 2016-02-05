/**
 * 2016年2月4日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.shareskill.dao.UserDao;
import org.shareskill.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author hit_lacus@126.com
 */
@Repository
public class UserDaoImpl implements UserDao{
	
	private static Logger log = Logger.getLogger(UserDaoImpl.class);
	
	UserRowMapper mapper = new UserRowMapper();
	
	@Autowired
	private JdbcOperations jdbcOperations;

    @Override
    public User getUserById(long id) {
	    return jdbcOperations.queryForObject("select * from User where cellphone = ?", mapper, id);
    }

}

/***
 * 
 * @author hit_lacus@126.com
 */
class UserRowMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return new User(
				rs.getBoolean("sex"),
				rs.getLong("cellphone"),
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








