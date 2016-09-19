package com.huitao.dsq.bean;

import java.util.Date;

public class ActivityLog implements java.io.Serializable {
	private Long  logId;
	private Long activityId;
    private String buyerNick;
	private Integer activityCondition;
	private Date creatTime;
	private String sellerNick;
	private Integer  liuLiangcount;
	public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public String getBuyerNick() {
		return buyerNick;
	}
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public String getSellerNick() {
		return sellerNick;
	}
	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}
	public Integer getLiuLiangcount() {
		return liuLiangcount;
	}
	public void setLiuLiangcount(Integer liuLiangcount) {
		this.liuLiangcount = liuLiangcount;
	}
	public Integer getActivityCondition() {
		return activityCondition;
	}
	public void setActivityCondition(Integer activityCondition) {
		this.activityCondition = activityCondition;
	}
	
}
