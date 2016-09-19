package com.huitao.dsq.service;

import java.util.List;

import com.huitao.dsq.bean.ActivityDetail;

public interface ActivityDetailService {
	 public ActivityDetail addActivityDetail(ActivityDetail activityDetail);
     public void deleteActivityDetail(ActivityDetail activityDetail);
     public void updateActivityDetail(ActivityDetail activityDetail);
     public ActivityDetail selectActivityDetail(long id);
     public List<ActivityDetail> selectActivityDetailList(ActivityDetail activityDetail);
     public List<ActivityDetail> selectActivityDetailListByActId(long actId);
}
