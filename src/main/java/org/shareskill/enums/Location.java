/**
 * 2016年1月19日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.enums;

/**
 * @author hit_lacus@126.com
 */
public enum Location {
	SHAN_DONG("山东"),SHANG_HAI("上海"),BEIJING("北京"),OTHERS("其他");
	
	
	
	
	
	
	private String where;
	
	private Location(String where){
		this.setWhere(where);
	}

	/**
	 * @return the where
	 */
    public String getWhere() {
	    return where;
    }

	/**
	 * @param where the where to set
	 */
    public void setWhere(String where) {
	    this.where = where;
    }
	
	

}
