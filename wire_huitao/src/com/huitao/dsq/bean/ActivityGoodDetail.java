package com.huitao.dsq.bean;

public class ActivityGoodDetail implements java.io.Serializable {
	private Long Id;
	private Long  actDetailId;
     private String  pic_url;
	private Integer oldNum;
	private Integer currentNum;
	private String  title;
	private String  detailUrl;
	private double  price;
	private double  activityPrice;
	private Integer type;
	private Integer isSaleOut;
	private Integer overWay;
	private Long  numiid;
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public Long getActDetailId() {
		return actDetailId;
	}

	public void setActDetailId(Long actDetailId) {
		this.actDetailId = actDetailId;
	}

	public Integer getOldNum() {
		return oldNum;
	}

	public void setOldNum(Integer oldNum) {
		this.oldNum = oldNum;
	}

	public Integer getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(double activityPrice) {
		this.activityPrice = activityPrice;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsSaleOut() {
		return isSaleOut;
	}

	public void setIsSaleOut(Integer isSaleOut) {
		this.isSaleOut = isSaleOut;
	}

	public Integer getOverWay() {
		return overWay;
	}

	public void setOverWay(Integer overWay) {
		this.overWay = overWay;
	}

	public Long getNumiid() {
		return numiid;
	}

	public void setNumiid(Long numiid) {
		this.numiid = numiid;
	}
     
}
