package com.huitao.dsq.bean;

import java.util.Date;
import java.util.List;


public class Activity implements java.io.Serializable{
         /**
	 * 
	 */
	private static final long serialVersionUID = 3999305617898542717L;
		private Long actId;
         private String sellerNick;
         private Integer actType;
         private String actName;
         private Date actStart;
         private Date actEnd;
         private String actRemark;
         private Integer couponShow;
         private String couponReceivingConditions;
         private String coupons;
         private String banner;
         private Integer state;
         private String qrcode;
         private List<ActivityDetail> activityDetailList;
         private Date createTime;
         
		public Long getActId() {
			return actId;
		}
		public void setActId(Long actId) {
			this.actId = actId;
		}
		public String getSellerNick() {
			return sellerNick;
		}
		public void setSellerNick(String sellerNick) {
			this.sellerNick = sellerNick;
		}
		public Integer getActType() {
			return actType;
		}
		public void setActType(Integer actType) {
			this.actType = actType;
		}
		public String getActName() {
			return actName;
		}
		public void setActName(String actName) {
			this.actName = actName;
		}
		public Date getActStart() {
			return actStart;
		}
		public void setActStart(Date actStart) {
			this.actStart = actStart;
		}
		public Date getActEnd() {
			return actEnd;
		}
		public void setActEnd(Date actEnd) {
			this.actEnd = actEnd;
		}
		public String getActRemark() {
			return actRemark;
		}
		public void setActRemark(String actRemark) {
			this.actRemark = actRemark;
		}
		public Integer getCouponShow() {
			return couponShow;
		}
		public void setCouponShow(Integer couponShow) {
			this.couponShow = couponShow;
		}
		public String getCouponReceivingConditions() {
			return couponReceivingConditions;
		}
		public void setCouponReceivingConditions(String couponReceivingConditions) {
			this.couponReceivingConditions = couponReceivingConditions;
		}
		public String getCoupons() {
			return coupons;
		}
		public void setCoupons(String coupons) {
			this.coupons = coupons;
		}
		public String getBanner() {
			return banner;
		}
		public void setBanner(String banner) {
			this.banner = banner;
		}
		public Integer getState() {
			return state;
		}
		public void setState(Integer state) {
			this.state = state;
		}
		public List<ActivityDetail> getActivityDetailList() {
			return activityDetailList;
		}
		public void setActivityDetailList(List<ActivityDetail> activityDetailList) {
			this.activityDetailList = activityDetailList;
		}
		public String getQrcode() {
			return qrcode;
		}
		public void setQrcode(String qrcode) {
			this.qrcode = qrcode;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		
		
	
         
}
