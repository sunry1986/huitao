package com.huitao.dsq.service;

import java.util.List;

import com.huitao.dsq.bean.Activity;

public interface ActivityService {
     public Activity addActivity(Activity activity);
     public void deleteActivity(Activity activity);
     public void updateActivity(Activity activity);
     public Activity selectActivity(long id);
     public List<Activity> selectActivityList(Activity activity);
}
