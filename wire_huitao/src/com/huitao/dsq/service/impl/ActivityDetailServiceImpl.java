package com.huitao.dsq.service.impl;

import java.util.List;
import com.huitao.dsq.bean.ActivityDetail;
import com.huitao.dsq.dao.ActivityDetailDao;
import com.huitao.dsq.service.ActivityDetailService;

public class ActivityDetailServiceImpl implements ActivityDetailService{
	private ActivityDetailDao activityDetailDao;
	
	public void setActivityDetailDao(ActivityDetailDao activityDetailDao) {
		this.activityDetailDao = activityDetailDao;
	}

	public ActivityDetail addActivityDetail(ActivityDetail activityDetail) {
		// TODO Auto-generated method stub
		activityDetailDao.addActivityDetail(activityDetail);
		return activityDetail;
	}

	public void deleteActivityDetail(ActivityDetail activityDetail) {
		// TODO Auto-generated method stub
		activityDetailDao.deleteActivityGoodDetailByActDetailId(activityDetail);
		activityDetailDao.deleteActivityDetail(activityDetail);
	}

	public void updateActivityDetail(ActivityDetail activityDetail) {
		// TODO Auto-generated method stub
		activityDetailDao.updateActivityDetail(activityDetail);
	}

	public ActivityDetail selectActivityDetail(long id) {
		// TODO Auto-generated method stub
		ActivityDetail activityDetail1=activityDetailDao.selectActivityDetail(id);
		return activityDetail1;
	}

	public List<ActivityDetail> selectActivityDetailList(ActivityDetail activityDetail) {
		// TODO Auto-generated method stub
		List<ActivityDetail> list=activityDetailDao.selectActivityDetailList(activityDetail);
		return list;
	}

	public List<ActivityDetail> selectActivityDetailListByActId(long actId) {
		// TODO Auto-generated method stub
		List<ActivityDetail> list= activityDetailDao.selectActivityDetailListByActId(actId);
		return list;
	}


}
