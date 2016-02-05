/**
 * 2016年1月19日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.enums;

/**
 * @author hit_lacus@126.com
 */
public enum Job {
	WHITE_COLLAR("白领"),STUDNET("学生"),TEACHER("教师"),FREELANCER("自由职业者"),OTHERS("其他");
	
	
	private String name;
	
	private Job(String name){
		this.setName(name);
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

}
