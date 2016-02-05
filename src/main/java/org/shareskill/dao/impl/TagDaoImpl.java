/**
 * 2016年2月2日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.apache.log4j.Logger;
import org.shareskill.dao.TagDao;
import org.shareskill.pojo.Manager;
import org.shareskill.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author hit_lacus@126.com
 */
@Repository
public class TagDaoImpl implements TagDao {
	
	
	private static Logger log = Logger.getLogger(TagDaoImpl.class);
	
	RowMapper<Tag> mapper = new TagRowMapper();
	
	@Autowired
	private JdbcOperations jdbcOperations;
	
	
	

	/* (non-Javadoc)
	 * @see org.shareskill.dao.TagDao#getAllTag()
	 */
	@Override
	public List<Tag> getAllTag() {
		List<Tag> tags = jdbcOperations.query("select * from Tag", mapper);
		log.info("Find " + tags.size() + " tags.");
		return tags;
	}

}
class TagRowMapper implements RowMapper<Tag> {
	public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Tag(
			new Manager(rs.getString("creator")),
			rs.getString("tagName"),
			rs.getTimestamp("createTime").getTime()
		);
	}
}
