/**
 * 2016年2月4日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.shareskill.dao.UserDao;
import org.shareskill.dao.mapper.UserRowMapper;
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
	
	@Autowired
	RowMapper<User> userRowMapper; 

	/**
	 * @return the jdbcOperations
	 */
	public JdbcOperations getJdbcOperations() {
		return jdbcOperations;
	}


	/**
	 * @param jdbcOperations the jdbcOperations to set
	 */
	public void setJdbcOperations(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}


	
	public RowMapper<User> getUserRowMapper() {
		return userRowMapper;
	}


	
	public void setUserRowMapper(RowMapper<User> userRowMapper) {
		this.userRowMapper = userRowMapper;
	}
	//根据手机号查询用户信息    
    @Override
    public User getUserById(long id) {
	    return jdbcOperations.queryForObject("select * from user where username = ?", mapper, id);
    }
    
    // 获得所有用户信息
    @Override
	public List<User> getAllUser() {
		List<User> users = jdbcOperations.query("select * from user ", userRowMapper);
		log.info("Not using cache.Found " + users.size() + " users at all.");
		return users;
	}

//    注册新的用户
    @Override 
	 public void register(long username,String getPwd_MD5)throws Exception{
		 String sql="insert into user(username,enrollTime,pwdDigest,rank) values(?,?,?,?)";  
		 Date enrollTime = new Date(System.currentTimeMillis());
	        Object obj[]={username,enrollTime,getPwd_MD5,3.0};  
	        try{
		    this.jdbcOperations.update(sql,obj);
			log.info("user " + username + " register success.");
	    	} catch (Exception e) {
	    		e.printStackTrace();
			}
}
    
//    新用户第一次进入登陆需要完善的信息
    @Override 
	 public void afterlogin(boolean sex,String nickname)throws Exception{
	        Object obj[]={sex,nickname}; 
	        String sql="insert into user(sex,nickname) values(?,?)";  
	        try{
			    this.jdbcOperations.update(sql,obj);
		    	} catch (Exception e) {
		    		e.printStackTrace();
				}
    
}
    
//    更新完善用户信息
    @Override 
	 public void update(User user)throws Exception{
	        String sql="update skill set sex=?,realName=?,nickName=?,job=?,location=?,selfDesc=?where username=?";   
	        Object obj[]={user.isSex(),user.getRealName(),user.getNickName(),user.getJob(),user.getLocation(),user.getSelfDescription(),user.getCellphone()}; 
	        try{
			    this.jdbcOperations.update(sql,obj);
		    	} catch (Exception e) {
		    		e.printStackTrace();
				}
    	
    }
}








