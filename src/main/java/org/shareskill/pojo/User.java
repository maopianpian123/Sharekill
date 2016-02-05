/**
 * 2016年1月19日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.pojo;

import java.util.ArrayList;
import java.util.List;

import org.shareskill.enums.Job;
import org.shareskill.enums.Location;
import org.shareskill.pojo.record.*;



/**
 * @author hit_lacus@126.com
 */
public class User {
	
	/**
	 * @param sex
	 * @param cellphone
	 * @param realName
	 * @param nickName
	 * @param enrollTime
	 * @param job
	 * @param location
	 * @param pwd_MD5
	 * @param selfDescription
	 * @param rank
	 * @param score
	 */
    public User(boolean sex, long cellphone, String realName, String nickName,
            long enrollTime, String job, String location, String pwd_MD5,
            String selfDescription, double rank) {
	    super();
	    this.sex = sex;
	    this.cellphone = cellphone;
	    this.realName = realName;
	    this.nickName = nickName;
	    this.enrollTime = enrollTime;
	    this.job = job;
	    this.location = location;
	    this.pwd_MD5 = pwd_MD5;
	    this.selfDescription = selfDescription;
	    this.rank = rank;
    }

	private boolean sex;
	
	private long cellphone;
	
	private String realName;
	
	private String nickName;
	
	
	private long enrollTime;
	
	private String job;
	
	private String location;
	
	private String pwd_MD5;//
	
	private String selfDescription;
	
	private double rank;
	
	
	
	private List<Order> orders = new ArrayList<>();
	
	private List<Skill> skills = new ArrayList<>();
	
	private List<AppealRecord> appeals = new ArrayList<>();

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
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the enrollTime
	 */
	public long getEnrollTime() {
		return enrollTime;
	}

	/**
	 * @param enrollTime the enrollTime to set
	 */
	public void setEnrollTime(long enrollTime) {
		this.enrollTime = enrollTime;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the pwd_MD5
	 */
	public String getPwd_MD5() {
		return pwd_MD5;
	}

	/**
	 * @param pwd_MD5 the pwd_MD5 to set
	 */
	public void setPwd_MD5(String pwd_MD5) {
		this.pwd_MD5 = pwd_MD5;
	}

	/**
	 * @return the selfDescription
	 */
	public String getSelfDescription() {
		return selfDescription;
	}

	/**
	 * @param selfDescription the selfDescription to set
	 */
	public void setSelfDescription(String selfDescription) {
		this.selfDescription = selfDescription;
	}

	/**
	 * @return the rank
	 */
	public double getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(double rank) {
		this.rank = rank;
	}



	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * @return the skills
	 */
	public List<Skill> getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	/**
	 * @return the appeals
	 */
	public List<AppealRecord> getAppeals() {
		return appeals;
	}

	/**
	 * @param appeals the appeals to set
	 */
	public void setAppeals(List<AppealRecord> appeals) {
		this.appeals = appeals;
	}

	/**
	 * @return the sex
	 */
	public boolean isSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	
	
	
	

}
