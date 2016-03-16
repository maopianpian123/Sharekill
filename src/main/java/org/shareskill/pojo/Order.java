/**
 * 2016年1月19日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.pojo;

/**
 * @author hit_lacus@126.com
 */
public class Order {
	

	private int creator;
    
    private int provider;
    
    private int skillId;
    
    private int status;
    
    private String contact;
    
    private String remark;
    
    private double price;
    
    private long createTime;
    
    private long receiveTime;
    
    private long completeTime;

	private int orderId;
    
    public Order(int orderId,int creator,int provider,int skillId,int status,String contact,
    		String remark,double price,long createTime,long receiveTime,long completeTime){
    	this.orderId=orderId;
    	this.creator=creator;
    	this.provider=provider;
    	this.skillId=skillId;
    	this.status=status;
    	this.contact=contact;
    	this.remark=remark;
    	this.price=price;
    	this.createTime=createTime;
    	this.receiveTime=receiveTime;
    	this.completeTime=completeTime;
    }
    

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public int getProvider() {
		return provider;
	}

	public void setProvider(int provider) {
		this.provider = provider;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(long receiveTime) {
		this.receiveTime = receiveTime;
	}

	public long getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(long completeTime) {
		this.completeTime = completeTime;
	}
    
    public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
