/**
 * 2016年1月19日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.pojo;

/**
 * @author hit_lacus@126.com
 */
public class Manager {
	
	public Manager(String idno){
		this.identifyNo = idno;
	}
	
	private long cellphone;
	
	private String realName;
	
	private String identifyNo;
	
	private long createTime;
	
	private int authority;

	/**
	 * @return the cellphone
	 */
	public long getCellphone() {
		return cellphone;
	}

	/**
	 * @param cellphone the cellphone to set
	 */
	public void setCellphone(long cellphone) {
		this.cellphone = cellphone;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the identifyNo
	 */
	public String getIdentifyNo() {
		return identifyNo;
	}

	/**
	 * @param identifyNo the identifyNo to set
	 */
	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
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

	/**
	 * @return the authority
	 */
	public int getAuthority() {
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(int authority) {
		this.authority = authority;
	}

}
