package com.huitao.dsq.service.impl;

import java.util.List;

import com.huitao.dsq.bean.ActivityGoodDetail;
import com.huitao.dsq.dao.ActivityGoodDetailDao;
import com.huitao.dsq.service.ActivityGoodDetailService;

public class ActivityGoodDetailServiceImpl implements ActivityGoodDetailService{
	private ActivityGoodDetailDao activityGoodDetailDao;
	
	

	public void setActivityGoodDetailDao(ActivityGoodDetailDao activityGoodDetailDao) {
		this.activityGoodDetailDao = activityGoodDetailDao;
	}

	public ActivityGoodDetail addActivityGoodDetail(ActivityGoodDetail activityGoodDetail) {
		// TODO Auto-generated method stub
		activityGoodDetailDao.addActivityGoodDetail(activityGoodDetail);
		return activityGoodDetail;
	}

	public void deleteActivityGoodDetail(ActivityGoodDetail activityGoodDetail) {
		// TODO Auto-generated method stub
		activityGoodDetailDao.deleteActivityGoodDetail(activityGoodDetail);
	}

	public void updateActivityGoodDetail(ActivityGoodDetail activityGoodDetail) {
		// TODO Auto-generated method stub
		activityGoodDetailDao.updateActivityGoodDetail(activityGoodDetail);
	}

	public ActivityGoodDetail selectActivityGoodDetail(long id) {
		// TODO Auto-generated method stub
		ActivityGoodDetail activityGoodDetail1=activityGoodDetailDao.selectActivityGoodDetail(id);
		return activityGoodDetail1;
	}

	public List<ActivityGoodDetail> selectActivityGoodDetailList(ActivityGoodDetail activityGoodDetail) {
		// TODO Auto-generated method stub
		List<ActivityGoodDetail> list=activityGoodDetailDao.selectActivityGoodDetailList(activityGoodDetail);
		return list;
	}

	public List<ActivityGoodDetail> selectActivityGoodDetailListByActDetailId(
			long actDetailId) {
		// TODO Auto-generated method stub
		List<ActivityGoodDetail> list=activityGoodDetailDao.selectActivityGoodDetailListByActDetailId(actDetailId);
		return list;
	}


	

}
