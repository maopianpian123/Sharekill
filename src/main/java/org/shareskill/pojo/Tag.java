/**
 * 2016年1月19日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.pojo;

/**
 * @author hit_lacus@126.com
 */
public class Tag {
	
	private Manager creator;
	
	private String name;
	
	private long createTime;
	
	
	/**
	 * @param creator
	 * @param name
	 * @param createTime
	 */
    public Tag(Manager creator, String name, long createTime) {
	    super();
	    this.creator = creator;
	    this.name = name;
	    this.createTime = createTime;
    }
	

	/**
	 * @return the creator
	 */
	public Manager getCreator() {
		return creator;
	}

	

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(Manager creator) {
		this.creator = creator;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the createTime
	 */
	public long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

}
