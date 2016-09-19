package com.huitao.dsq.dao;

import java.util.List;

import com.huitao.dsq.bean.ActivityGoodDetail;

public interface ActivityGoodDetailDao {
	public void addActivityGoodDetail(ActivityGoodDetail activityGoodDetail);
    public void deleteActivityGoodDetail(ActivityGoodDetail activityGoodDetail);
    public void updateActivityGoodDetail(ActivityGoodDetail activityGoodDetail);
    public ActivityGoodDetail selectActivityGoodDetail(long id);
    public List<ActivityGoodDetail> selectActivityGoodDetailList(ActivityGoodDetail activityGoodDetail);
    public List<ActivityGoodDetail>  selectActivityGoodDetailListByActDetailId(long actDetailId);
}
