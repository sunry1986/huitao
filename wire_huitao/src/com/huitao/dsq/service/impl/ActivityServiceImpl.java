package com.huitao.dsq.service.impl;

import java.util.List;

import com.huitao.dsq.bean.Activity;
import com.huitao.dsq.dao.ActivityDao;
import com.huitao.dsq.service.ActivityService;

public class ActivityServiceImpl  implements ActivityService{
	private ActivityDao activityDao;

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public Activity addActivity(Activity activity) {
		// TODO Auto-generated method stub
		activityDao.addActivity(activity);
		return activity;
	}

	public void deleteActivity(Activity activity) {
		// TODO Auto-generated method stub
		activityDao.deleteActivityGoodDetailByActId(activity);
		activityDao.deleteActivityDetailByActId(activity);
		activityDao.deleteActivity(activity);
	}
	public void updateActivity(Activity activity) {
		// TODO Auto-generated method stub
		activityDao.updateActivity(activity);
	}

	public Activity selectActivity(long id) {
		// TODO Auto-generated method stub
		Activity activity1=activityDao.selectActivity(id);
		return activity1;
	}

	public List<Activity> selectActivityList(Activity activity) {
		// TODO Auto-generated method stub
		List<Activity> list=activityDao.selectActivityList(activity);
		return list;
	}

	
}
