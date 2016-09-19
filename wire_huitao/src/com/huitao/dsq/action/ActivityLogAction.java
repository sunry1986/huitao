package com.huitao.dsq.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huitao.baseAction.BaseAction;
import com.huitao.dao.factory.DaoFactory;
import com.huitao.dsq.bean.ActivityLog;
import com.huitao.dsq.service.ActivityLogService;

public class ActivityLogAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2802230265182336917L;
	private ActivityLogService activityLogService = (ActivityLogService) DaoFactory
			.getInstance().getService("activityLogService");
	/**
	 * 查找log日志
	 * @return
	 */
    public void findActivityLog(){
    	 String activityId=(String)this.getRequest().getParameter("activityId");
    	 String buyerNick=(String)this.getRequest().getParameter("buyerNick");
    	 String activityCondition=(String)this.getRequest().getParameter("activityCondition");
    	 String sellerNick=(String)this.getRequest().getParameter("sellerNick");
    	 Map<String, Object> map = new HashMap<String, Object>();
    	ActivityLog activityLog1=new ActivityLog();
    	if(activityId!=null&&!activityId.equals("")){
    		activityLog1.setActivityId(Long.valueOf(activityId));
		}
    	if(buyerNick!=null&&!buyerNick.equals("")){
    		activityLog1.setBuyerNick(buyerNick);
		}
    	if(activityCondition!=null&&!activityCondition.equals("")){
    		activityLog1.setActivityCondition(Integer.valueOf(activityCondition));
		}
    	/*
		 * 便于测试以后放开 String sellerNick=
		 * getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
		 * activity.setSellerNick(sellerNick);
		 */
    	if(sellerNick!=null&&!sellerNick.equals("")){
    		activityLog1.setSellerNick(sellerNick);
		}
    	List<ActivityLog> activitisLogList =activityLogService.selectActivityLogList(activityLog1);
    	map.put("activitisList", activitisLogList);
    	getPrintWriter(map);
    }
    public void addActivityLog(){
    	 String activityId=(String)this.getRequest().getParameter("activityId");
	   	 String buyerNick=(String)this.getRequest().getParameter("buyerNick");
	   	 String activityCondition=(String)this.getRequest().getParameter("activityCondition");
	   	 String sellerNick=(String)this.getRequest().getParameter("sellerNick");
	   	 //判断活动是否保存过
	   	 Map<String, Object> map = new HashMap<String, Object>();
	   	ActivityLog activityLog1=new ActivityLog();
	   	if(activityId!=null&&!activityId.equals("")){
	   		activityLog1.setActivityId(Long.valueOf(activityId));
			}
	   	if(buyerNick!=null&&!buyerNick.equals("")){
	   		activityLog1.setBuyerNick(buyerNick);
			}
	   	if(activityCondition!=null&&!activityCondition.equals("")){
	   		activityLog1.setActivityCondition(Integer.valueOf(activityCondition));
			}
	   	/*
			 * 便于测试以后放开 String sellerNick=
			 * getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
			 * activity.setSellerNick(sellerNick);
			 */
	   	if(sellerNick!=null&&!sellerNick.equals("")){
	   		activityLog1.setSellerNick(sellerNick);
		}
	   	activityLog1.setCreatTime(new Date());
	   	activityLogService.addActivityLog(activityLog1);
	   	map.put("activityLog", activityLog1);
	   	getPrintWriter(map);
    }
    
    
    
    
}
