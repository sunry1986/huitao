package com.huitao.dsq.dao;

import java.util.List;

import com.huitao.dsq.bean.Activity;

public interface ActivityDao {
	 public void addActivity(Activity activity);
     public void deleteActivity(Activity activity);
     public void deleteActivityDetailByActId(Activity activity);
     public void deleteActivityGoodDetailByActId(Activity activity);
     public void updateActivity(Activity activity);
     public Activity selectActivity(long id);
     public List<Activity> selectActivityList(Activity activity);
}
