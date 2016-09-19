package com.huitao.dsq.dao;

import java.util.List;

import com.huitao.dsq.bean.ActivityDetail;

public interface ActivityDetailDao {
	 public void addActivityDetail(ActivityDetail activityDetail);
     public void deleteActivityDetail(ActivityDetail activityDetail);
     public void updateActivityDetail(ActivityDetail activityDetail);
     public ActivityDetail selectActivityDetail(long id);
     public List<ActivityDetail> selectActivityDetailList(ActivityDetail activityDetail);
     public void deleteActivityGoodDetailByActDetailId(ActivityDetail activityDetail);
     public List<ActivityDetail> selectActivityDetailListByActId(long actId);
}
