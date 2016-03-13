/**
 * 2016年2月4日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao;

import org.shareskill.pojo.User;

/**
 * @author hit_lacus@126.com
 */
public interface UserDao {
	
	User getUserById(long id);

}
