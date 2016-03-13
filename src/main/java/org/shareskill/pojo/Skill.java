/**
 * 2016年1月19日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.pojo;

import java.util.ArrayList;
import java.util.List;

import org.shareskill.pojo.record.*;

/**
 * 技能
 * @author hit_lacus@126.com
 */
public class Skill {
	
	/**
	 * @param skillId
	 * @param name
	 * @param description
	 * @param createTime
	 * @param totalTrade
	 * @param successTrade
	 * @param contact
	 * @param price
	 * @param score
	 * @param creator
	 */
    public Skill(int skillId, String name, String description,
            long createTime, int totalComment, int successTrade, String contact,
            double price, double score, User creator) {
	    super();
	    this.skillId = skillId;
	    this.name = name;
	    this.description = description;
	    this.createTime = createTime;
	    this.totalComment = totalComment;
	    this.successTrade = successTrade;
	    this.contact = contact;
	    this.price = price;
	    this.score = score;
	    this.creator = creator;
    }

	private int skillId;//
	
	private String name;//
	
	private String description;//
	
	private long createTime;//
	
	private int totalComment = 0;
	
	private int successTrade = 0;
	
	private String contact;//
	
	private double price;//
	
	private double score;//
	
	private User creator;//
	
	private List<Tag> tags = new ArrayList<>();
	
	private List<SuccessRecord> successes = new ArrayList<>();
	
	private List<ReviewRecord> reviews = new ArrayList<>();
	
	private List<Comment> comments = new ArrayList<>();
	
	/**
	 * 技能修改的历史记录
	 */
	private List<SkillHistory> histories = new ArrayList<>();

	
	
	
	
	/**
	 * @return the skillId
	 */
	public int getSkillId() {
		return skillId;
	}

	/**
	 * @param skillId the skillId to set
	 */
	public void setSkillId(int skillId) {
		this.skillId = skillId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the totalTrade
	 */
	public int getTotalComment() {
		return totalComment;
	}

	/**
	 * @param totalTrade the totalTrade to set
	 */
	public void setTotalComment(int totalTrade) {
		this.totalComment = totalTrade;
	}

	/**
	 * @return the successTrade
	 */
	public int getSuccessTrade() {
		return successTrade;
	}

	/**
	 * @param successTrade the successTrade to set
	 */
	public void setSuccessTrade(int successTrade) {
		this.successTrade = successTrade;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the successes
	 */
	public List<SuccessRecord> getSuccesses() {
		return successes;
	}

	/**
	 * @param successes the successes to set
	 */
	public void setSuccesses(List<SuccessRecord> successes) {
		this.successes = successes;
	}

	/**
	 * @return the reviews
	 */
	public List<ReviewRecord> getReviews() {
		return reviews;
	}

	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(List<ReviewRecord> reviews) {
		this.reviews = reviews;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the histories
	 */
	public List<SkillHistory> getHistories() {
		return histories;
	}

	/**
	 * @param histories the histories to set
	 */
	public void setHistories(List<SkillHistory> histories) {
		this.histories = histories;
	}
	
}





/**
 * 用户关于技能的更新记录
 * @author hit_lacus@126.com
 */
class SkillHistory{
	
	private long historyId;
	
	private String name;
	
	private long updateTime;
	
	private double price;
	
	private String contact;

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
	 * @return the updateTime
	 */
	public long getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the historyId
	 */
	public long getHistoryId() {
		return historyId;
	}

	/**
	 * @param historyId the historyId to set
	 */
	public void setHistoryId(long historyId) {
		this.historyId = historyId;
	}
	
	
	
}
