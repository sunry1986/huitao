package com.huitao.dsq.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huitao.baseAction.BaseAction;
import com.huitao.dao.factory.DaoFactory;
import com.huitao.dsq.bean.Activity;
import com.huitao.dsq.bean.ActivityLog;
import com.huitao.dsq.bean.UserInfo;
import com.huitao.dsq.service.ActivityLogService;
import com.huitao.dsq.service.ActivityService;
import com.huitao.dsq.service.UserService;
import com.huitao.sys.vo.TaoBaoAccessVo;
import com.huitao.taobao.api.ItemApi;
import com.huitao.taobao.vo.SendFlowParamVo;

public class clientAction extends BaseAction{
	private ActivityLogService activityLogService = (ActivityLogService) DaoFactory
			.getInstance().getService("activityLogService");
	private ActivityService activityService = (ActivityService) DaoFactory
			.getInstance().getService("activityService");
	private UserService userService = (UserService)DaoFactory.getInstance().getService("userService");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @return  map  其中 value  有值表示流量发放成功，message记录错误信息
	 */
	public void shoucang(){
		 Map<String, String>  map=new HashMap<String, String>();
		 String activityId=(String)this.getRequest().getParameter("activityId");
	   	 String buyerNick=(String)this.getRequest().getParameter("buyerNick");
	   	ActivityLog activityLog=new ActivityLog();
	   	if(activityId!=null&&!activityId.equals("")){
	   		activityLog.setActivityId(Long.valueOf(activityId));
		}else{
			map.put("message", "缺少参数activityId");
			map.put("result", "fail");
			 getPrintWriter(map);
		}
	   	if(buyerNick!=null&&!buyerNick.equals("")){
	   		activityLog.setBuyerNick(buyerNick);
		}else{
			map.put("message", "缺少参数buyerNick");
			map.put("result", "fail");
			 getPrintWriter(map);
		}
	   	Map<String, String> map1=liuliangCondition(activityLog);
         String result=	map1.get("result");
         if(result!=null && result=="success"){
        	 
        	//记录流量赠送日志
        	 String sellerNick=map1.get("sellerNick");
        	 ActivityLog activityLognew=new ActivityLog();
        	 activityLognew.setActivityId(Long.valueOf(activityId));
        	 activityLognew.setBuyerNick(buyerNick);
        	 activityLognew.setSellerNick(sellerNick);
        	 activityLognew.setCreatTime(new Date());
        	 activityLognew.setActivityCondition(5);
        	 activityLognew.setLiuLiangcount(Integer.valueOf(map1.get("mCountOfper")));
        	 activityLogService.addActivityLog(activityLognew);
        	 //进行流量赠送
        	 Map<String, Object> sendFlowMap= sendFlow( activityLognew);
        	 if(sendFlowMap!=null && sendFlowMap.get("value")!=null){
        		 map1.put("value", sendFlowMap.get("value").toString());
        	 }else{
        		 map1.put("message", sendFlowMap.get("message").toString());
        	 }
         }
         String souCangCount=map1.get("souCangCount");
         if(souCangCount!=null && Integer.valueOf(souCangCount)==0){
        	 //记录收藏日志
        	 String sellerNick=map1.get("sellerNick");
        	 ActivityLog activityLognew=new ActivityLog();
        	 activityLognew.setActivityId(Long.valueOf(activityId));
        	 activityLognew.setBuyerNick(buyerNick);
        	 activityLognew.setSellerNick(sellerNick);
        	 activityLognew.setCreatTime(new Date());
        	 activityLognew.setActivityCondition(1);
        	 activityLogService.addActivityLog(activityLognew);
         }
         getPrintWriter(map1);
	}  
	/**
     * 查询买家是否符合流量领取条件
     * 
     */
    private  Map<String, String> liuliangCondition(ActivityLog activityLog){
    	 Map<String, String>  map=new HashMap<String, String>();
    	 int mCountOfper= 0;
		 int numberOflimit=  0;
		 int mTotle=  0;
    	//查询活动设置
    	Activity activity = activityService.selectActivity(Long
				.valueOf(activityLog.getActivityId()));
    	if(activity!=null){
    		String conditions=activity.getCoupons();
    		String[] array=conditions.split(",");
    		if(array.length==3){
        		if(array[0]!=null){
        			mCountOfper =Integer.valueOf(array[0]);
        			numberOflimit=Integer.valueOf(array[1]);
        			mTotle=Integer.valueOf(array[2]);
        		}
    		}else{
    			map.put("message", "活动设置不正确，请联系卖家");
    			map.put("result", "fail");
    			//return  map;
    		}
    		
    	}else{
    		map.put("result", "fail");
    		map.put("message", "活动不存在");
			return  map;
    	}
    	int souCangCount=0;//
    	int lingquLiuLiangCount=0;
    	int lingquLiuLiangTotleCount=0;
    	//根据activityId和buyerNick， 查找activityId下buyerNick的所有记录
    	ActivityLog activityLog3=new ActivityLog();
    	activityLog3.setActivityId(activityLog.getActivityId());
    	activityLog3.setBuyerNick(activityLog.getBuyerNick());
    	List<ActivityLog> activitisLogList =activityLogService.selectActivityLogList(activityLog3);
    	for(int i=0;i<activitisLogList.size();i++){
    		ActivityLog activityLog1=activitisLogList.get(i);
    		if(activityLog1.getBuyerNick().equals(activityLog.getBuyerNick())){
    			  if(activityLog1.getActivityCondition()==1){
    				  souCangCount++;
    			  }
    			  if(activityLog1.getActivityCondition()==5){
    				  lingquLiuLiangCount++;
    			  }
    		}
    		
    	}
    	//获取activityId下面Condition为5的总流量数
    	ActivityLog activityLog2=new ActivityLog();
    	activityLog2.setActivityId(activityLog.getActivityId());
    	activityLog2.setActivityCondition(5);
    	lingquLiuLiangTotleCount=activityLogService.findLogCount(activityLog2);
    	if(lingquLiuLiangCount>=numberOflimit){
    		map.put("message", "领取超限，每人最多领取"+numberOflimit+"次");
    		map.put("souCangCount", ""+souCangCount);
    		map.put("sellerNick", activity.getSellerNick());
			map.put("result", "fail");
			return  map;
    	}
    	if(lingquLiuLiangTotleCount>=mTotle){
    		map.put("message", "流量都被领光了，下次再试试吧");
    		map.put("souCangCount", ""+souCangCount);
    		map.put("sellerNick", activity.getSellerNick());
			map.put("result", "fail");
			return  map;
    	}
    	map.put("souCangCount", ""+souCangCount);
    	map.put("result", "success");
    	map.put("sellerNick", activity.getSellerNick());
    	map.put("mCountOfper", ""+mCountOfper);
    	return  map;
    }
    /**
     * 流量发放
     */
    public Map<String, Object> sendFlow(ActivityLog activityLog){
	    	if(activityLog.getActivityId()==null){
	    		return null;
	    	}
    	  SendFlowParamVo param=new SendFlowParamVo();
    	  String buyerNick=activityLog.getBuyerNick();
          String realNick="";
          String serial=activityLog.getActivityId()+"_"+activityLog.getLogId()+"HT";
          String flow=String.valueOf(activityLog.getLiuLiangcount());
          String reason=activityLog.getSellerNick()+"发放的流量";
          String always="true";
          
          if(buyerNick!=null&&!buyerNick.equals("")){
         		param.setBuyerNick(buyerNick);
          }
          if(realNick!=null&&!realNick.equals("")){
       		param.setRealNick(realNick);
          }
          if(serial!=null&&!serial.equals("")){
       		param.setSerial(serial);
          }
          if(flow!=null&&!flow.equals("")){
       		param.setFlow(flow);
          }
          if(reason!=null&&!reason.equals("")){
       		param.setReason(reason);
          }
          if(always!=null&&!always.equals("")){
       		param.setAlways(always);
          }
          //获取卖家授权信息
          UserInfo userInfo = userService.getByNick(activityLog.getSellerNick());
          TaoBaoAccessVo taoBaoAccessVo =new TaoBaoAccessVo();
          taoBaoAccessVo.setAccess_token(userInfo.getToken());
          Map<String, Object> map =ItemApi.getInstance().sendFlow(param, taoBaoAccessVo);
          return map;
    }
    
}
