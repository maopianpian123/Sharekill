/**
 * 2016年3月1日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.authentication;

/**
 * @author hit_lacus@126.com
 */
public class UserDetail {
	
	/**
	 * 手机*/
	private String mobile;
	
	/**
	 * ISV独立编号
	 */
	private String isvAccountId;
	
	/**
	 * 云账号编号
	 */
	private String openAccountId;
	
	/**
	 * 即时消息账号
	 */
	private String imAccount;
	
	/**
	 * 云推送账号
	 */
	private String pushAccount;

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the isvAccountId
	 */
	public String getIsvAccountId() {
		return isvAccountId;
	}

	/**
	 * @param isvAccountId the isvAccountId to set
	 */
	public void setIsvAccountId(String isvAccountId) {
		this.isvAccountId = isvAccountId;
	}

	/**
	 * @return the openAccountId
	 */
	public String getOpenAccountId() {
		return openAccountId;
	}

	/**
	 * @param openAccountId the openAccountId to set
	 */
	public void setOpenAccountId(String openAccountId) {
		this.openAccountId = openAccountId;
	}

	/**
	 * @return the imAccount
	 */
	public String getImAccount() {
		return imAccount;
	}

	/**
	 * @param imAccount the imAccount to set
	 */
	public void setImAccount(String imAccount) {
		this.imAccount = imAccount;
	}

	/**
	 * @return the pushAccount
	 */
	public String getPushAccount() {
		return pushAccount;
	}

	/**
	 * @param pushAccount the pushAccount to set
	 */
	public void setPushAccount(String pushAccount) {
		this.pushAccount = pushAccount;
	}

}
