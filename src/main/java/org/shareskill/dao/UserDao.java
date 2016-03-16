/**
 * 2016年2月4日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao;

import java.util.List;

import org.shareskill.pojo.User;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author hit_lacus@126.com
 */
public interface UserDao {
	@Cacheable("userList")
	
	User getUserById(long id);
	
	public List<User> getAllUser();
	
	public void register(long username,String getPwd_MD5)throws Exception;
    
	public void afterlogin(boolean sex,String nickname)throws Exception;
	 
    public void update(User user)throws Exception;
	 
}
