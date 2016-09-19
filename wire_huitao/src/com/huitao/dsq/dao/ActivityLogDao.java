package com.huitao.dsq.dao;
import java.util.List;
import com.huitao.dsq.bean.ActivityLog;

public interface ActivityLogDao {
	public void addActivityLog(ActivityLog activityLog);
    public void deleteActivityLog(ActivityLog activityLog);
    public void updateActivityLog(ActivityLog activityLog);
    public ActivityLog selectActivityLog(long logId);
    public List<ActivityLog> selectActivityLogList(ActivityLog activityLog);
    public Integer findLogCount(ActivityLog activityLog);
}
