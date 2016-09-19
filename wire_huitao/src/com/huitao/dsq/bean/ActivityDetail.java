package com.huitao.dsq.bean;

import java.util.Date;
import java.util.List;

public class ActivityDetail implements java.io.Serializable{
	 private Long actDetailId;
	 private Long actId;
	 private Date startTime;
     private Date endTime;
 	 private List<ActivityGoodDetail> activityGoodDetailList;
	public Long getActDetailId() {
		return actDetailId;
	}
	public void setActDetailId(Long actDetailId) {
		this.actDetailId = actDetailId;
	}
	public Long getActId() {
		return actId;
	}
	public void setActId(Long actId) {
		this.actId = actId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public List<ActivityGoodDetail> getActivityGoodDetailList() {
		return activityGoodDetailList;
	}
	public void setActivityGoodDetailList(
			List<ActivityGoodDetail> activityGoodDetailList) {
		this.activityGoodDetailList = activityGoodDetailList;
	}
	
}
