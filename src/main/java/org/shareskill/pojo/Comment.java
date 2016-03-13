/**
 * 2016年1月19日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.pojo;

/**
 * @author hit_lacus@126.com
 */
public class Comment {
	
	private User user;
	
	private String content;
	
	private Skill skill;
	
	private long commentTime;
	
	private int score;

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the skill
	 */
	public Skill getSkill() {
		return skill;
	}

	/**
	 * @param skill the skill to set
	 */
	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	/**
	 * @return the commentTime
	 */
	public long getCommentTime() {
		return commentTime;
	}

	/**
	 * @param commentTime the commentTime to set
	 */
	public void setCommentTime(long commentTime) {
		this.commentTime = commentTime;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

}
