package com.huitao.dsq.service.impl;

import java.util.List;

import com.huitao.dsq.bean.ActivityLog;
import com.huitao.dsq.dao.ActivityLogDao;
import com.huitao.dsq.service.ActivityLogService;

public class ActivityLogServiceImpl implements ActivityLogService{
	private ActivityLogDao activityLogDao;
	
	public void setActivityLogDao(ActivityLogDao activityLogDao) {
		this.activityLogDao = activityLogDao;
	}

	public ActivityLog addActivityLog(ActivityLog activityLog) {
		// TODO Auto-generated method stub
		activityLogDao.addActivityLog(activityLog);
		return activityLog;
	}

	public void deleteActivityLog(ActivityLog activityLog) {
		// TODO Auto-generated method stub
		activityLogDao.deleteActivityLog(activityLog);
	}

	public void updateActivityLog(ActivityLog activityLog) {
		// TODO Auto-generated method stub
		activityLogDao.updateActivityLog(activityLog);
	}

	public ActivityLog selectActivityLog(long logId) {
		// TODO Auto-generated method stub
		ActivityLog activityLog1=activityLogDao.selectActivityLog(logId);
		return activityLog1;
	}

	public List<ActivityLog> selectActivityLogList(ActivityLog activityLog) {
		// TODO Auto-generated method stub
		List<ActivityLog> list=activityLogDao.selectActivityLogList(activityLog);
		return list;
	}

	public Integer findLogCount(ActivityLog activityLog) {
		// TODO Auto-generated method stub
		Integer count=activityLogDao.findLogCount(activityLog);
		return count;
	}

}
