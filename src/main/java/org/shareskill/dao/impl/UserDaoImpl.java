/**
 * 2016年2月4日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.impl;

import org.apache.log4j.Logger;
import org.shareskill.dao.UserDao;
import org.shareskill.dao.mapper.UserRowMapper;
import org.shareskill.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
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
	    return jdbcOperations.queryForObject("select * from User where username = ?", mapper, id);
    }

}








