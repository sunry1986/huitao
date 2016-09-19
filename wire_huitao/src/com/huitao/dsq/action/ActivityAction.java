package com.huitao.dsq.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tl.commons.util.DateUtility;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.huitao.baseAction.BaseAction;
import com.huitao.dao.factory.DaoFactory;
import com.huitao.dsq.bean.Activity;
import com.huitao.dsq.bean.ActivityDetail;
import com.huitao.dsq.bean.ActivityGoodDetail;
import com.huitao.dsq.service.ActivityDetailService;
import com.huitao.dsq.service.ActivityGoodDetailService;
import com.huitao.dsq.service.ActivityService;
import com.huitao.sys.commons.Constants4System;
import com.huitao.taobao.api.ItemApi;
import com.huitao.taobao.vo.QrcodeVo;
import com.taobao.api.domain.QrcodeDO;

public class ActivityAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 909532866594020110L;
	private ActivityService activityService = (ActivityService) DaoFactory
			.getInstance().getService("activityService");
	private ActivityDetailService activityDetailService = (ActivityDetailService) DaoFactory
			.getInstance().getService("activityDetailService");
	private ActivityGoodDetailService activityGoodDetailService = (ActivityGoodDetailService) DaoFactory
			.getInstance().getService("activityGoodDetailService");


	/**
	 * 
	 * @return
	 */
	public void findActivitiesList() {
		String data = this.getRequest().getParameter("data");
		Map<String, Object> map = new HashMap<String, Object>();
//		if (data != null) {
		Activity activity = new Activity();
		if(data!=null){
			JSONObject obj = JSONObject.fromObject(data);
			if(obj.containsKey("activity")&&!obj.getString("activity").equals("")){
				JSONObject obj_activity = JSONObject.fromObject(obj
						.getString("activity"));
				if(obj_activity.containsKey("state")&&!obj_activity.getString("state").equals("")){
					activity.setState(Integer.valueOf(obj_activity.getString("state")));
				}
			}
			
		}
			/*
			 * 便于测试以后放开 String sellerNick=
			 * getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
			 * activity.setSellerNick(sellerNick);
			 */
			String sellerNick=
					  getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
					 activity.setSellerNick(sellerNick);
			//activity.setSellerNick(obj_activity.getString("sellerNick"));
			
			List<Activity> activitisList = activityService
					.selectActivityList(activity);
			map.put("activitisList", activitisList);
		/*} else {
			map.put("result", "fail");
			map.put("message", "参数错误");
		}*/

		getPrintWriter(map);
	}

	/**
	 * 
	 * @return
	 */
	public void findActivityById() {
		String actId = this.getRequest().getParameter("actId");
		Map<String, Object> map = new HashMap<String, Object>();
		if (actId != null) {
			Activity activity = activityService.selectActivity(Long
					.valueOf(actId));
			
			if(activity!=null){
				List<ActivityDetail> activityDetaillist=activityDetailService.selectActivityDetailListByActId(Long
						.valueOf(actId));
				activity.setActivityDetailList(activityDetaillist);
				
				for(int i=0;i<activityDetaillist.size();i++){
					
					ActivityDetail activityDetail=activityDetaillist.get(i);
					
					List<ActivityGoodDetail> activityGoodDetailList=activityGoodDetailService.selectActivityGoodDetailListByActDetailId(activityDetail.getActDetailId());
					activityDetail.setActivityGoodDetailList(activityGoodDetailList);
				}
			}
			
			
			
			map.put("activity", activity);
		} else {
			map.put("result", "fail");
			map.put("message", "参数错误");
		}

		getPrintWriter(map);
	}

	/**
	 * 
	 * @return
	 */
	public void operationActivity() {
		String data = this.getRequest().getParameter("data");
		Map<String, Object> map = new HashMap<String, Object>();
		if (data != null) {
			JSONObject obj = JSONObject.fromObject(data);
			JSONObject obj_activity = JSONObject.fromObject(obj
					.getString("activity"));
			// 处理主表
			String useroperation = "";
			if(obj_activity.containsKey("useroperation")&&!obj_activity.getString("useroperation").equals("")){
				useroperation=obj_activity.getString("useroperation");
			}
			if (useroperation.equals("delete")) {
				  if(obj_activity.containsKey("actId")&&!obj_activity.getString("actId").equals("")){
					  this.activityDelete(obj_activity);
					   map.put("result", "success");
					   map.put("message", "删除成功");
				   }else{
					   map.put("message", "参数actId错误");
				   }
				   
			} else if (useroperation.equals("add")||useroperation.equals("update")) {
				   Activity activity = this.activityAddOrUpdate(obj_activity);
				   activityDetailOperation( obj_activity,activity);
				   map.put("activity", activity);
			}else{
				
				if(obj_activity.containsKey("actId")&&!obj_activity.getString("actId").equals("")){
					   activityDetailOperation( obj_activity,null);
					   map.put("result", "success");
					   map.put("message", "操作成功");
					 }else{
						   map.put("message", "参数actId错误");
					 }
			}
			

			getPrintWriter(map);
		} else {
			map.put("result", "fail");
			getPrintWriter(map);
		}

	}


	
	
	
	/**
	 * 活动子表的综合处理
	 * @param obj_activity
	 */
	public void activityDetailOperation(JSONObject obj_activity,Activity activity){
		if(activity==null||activity.getActId()==null){
			//活动主表不存在时直接返回，不做处理
			if (!obj_activity.containsKey("actId")
					|| obj_activity.getString("actId").equals("")){
				
				return;
			}
			activity = new Activity();
			activity.setActId(Long.valueOf(obj_activity.getString("actId")));
			/*
			 * 便于测试以后放开 
			 */
			String sellerNick=getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
			activity.setSellerNick(sellerNick);
			 /*if (obj_activity.containsKey("sellerNick")
					  && !obj_activity.getString("sellerNick").equals("")) {
				 activity.setSellerNick(obj_activity.getString("sellerNick"));
			 }*/
			
		}
		
		// 处理子表
	    if (obj_activity.containsKey("activityDetailList")
			  && !obj_activity.getString("activityDetailList").equals("")) {
			JSONArray obj_activityDetailList = JSONArray.fromObject(obj_activity.getString("activityDetailList"));
			List<ActivityDetail> activityDetailList=new ArrayList<ActivityDetail>();
					for (int i = 0; i < obj_activityDetailList.size(); i++) {
							JSONObject obj_activityDetail = JSONObject
									.fromObject(obj_activityDetailList.get(i));
							String activityDetail_useroperation = "";
							if (obj_activityDetail.containsKey("useroperation")&& !obj_activityDetail.getString("useroperation").equals("")) {
								activityDetail_useroperation = obj_activityDetail
										.getString("useroperation");
							 }
							if (activityDetail_useroperation.equals("delete")) {
								 deleteActivityDetail(obj_activityDetail,
										 activity);
								
							} else if (activityDetail_useroperation.equals("update")||activityDetail_useroperation.equals("add")) {
								ActivityDetail activityDetail=activityDetailSaveOrUpdate( obj_activityDetail,
										 activity);
								activityGoodDetailOperation( obj_activityDetail,activityDetail);
								activityDetailList.add(activityDetail);
							}else{
								if(obj_activityDetail.containsKey("actDetailId")&&!obj_activityDetail.getString("actDetailId").equals("")){
									activityGoodDetailOperation( obj_activityDetail,null);
								}
							}
							
							
					}
					activity.setActivityDetailList(activityDetailList);

		}
		
	}
	//商品表的综合处理
	public void activityGoodDetailOperation(JSONObject obj_activityDetail,ActivityDetail activityDetail ){
		if(activityDetail==null||activityDetail.getActDetailId()==null){
			//商品的关联主表不存在时不做处理
			if (!obj_activityDetail.containsKey("actDetailId")
					|| obj_activityDetail.getString("actDetailId").equals("")){
				return;
			}
			 activityDetail = new ActivityDetail();
			activityDetail.setActDetailId(Long.valueOf(obj_activityDetail.getString("actDetailId")));
			
		}
		// 处理商品表
				if (obj_activityDetail.getString("activityGoodDetailList") != null
						&& !obj_activityDetail.getString("activityGoodDetailList")
								.equals("")) {
					JSONArray obj_activityGoodDetailList = JSONArray
							.fromObject(obj_activityDetail
									.getString("activityGoodDetailList"));
					List<ActivityGoodDetail> activityGoodDetailList=new ArrayList<ActivityGoodDetail>();
					for (int j = 0; j < obj_activityGoodDetailList.size(); j++) {
						JSONObject obj_activityGoodDetail = JSONObject
								.fromObject(obj_activityGoodDetailList.get(j));
						//当传递胡参数useroperation 不存在或为null时，不做任何处理
						if(obj_activityGoodDetail.containsKey("useroperation")&&!obj_activityGoodDetail.getString("useroperation").equals("")){
						
							String activityGoodDetail_useroperation = obj_activityGoodDetail
									.getString("useroperation");
							
							if (activityGoodDetail_useroperation
									.equals("delete")) {
								deleteActivityGoodDetail( obj_activityGoodDetail,
										 activityDetail);

							} else if (activityGoodDetail_useroperation
									.equals("update")||activityGoodDetail_useroperation
									.equals("add")) {
								ActivityGoodDetail activityGoodDetail=	activityGoodDetailSaveOrUpdate( obj_activityGoodDetail,
										 activityDetail);
								activityGoodDetailList.add(activityGoodDetail);
								
							}
						
						}
						
					}
					activityDetail.setActivityGoodDetailList(activityGoodDetailList);
					
				}
	}
	
   /**
    * 
    * @param obj_activity
    * @return
    */
	public Activity activityAddOrUpdate(JSONObject obj_activity){
		
		Activity activity = new Activity();
		if(obj_activity.containsKey("actName")&&!obj_activity.getString("actName").equals("")){
			 activity.setActName(obj_activity.getString("actName"));
		}
		/*
		 *从session中获取登录用户的信息
		 */
		String sellerNick=getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
		activity.setSellerNick(sellerNick);
		/*if(obj_activity.containsKey("sellerNick")&&!obj_activity.getString("sellerNick").equals("")){
			activity.setSellerNick(obj_activity.getString("sellerNick"));
		}*/
		
		try {
			if(obj_activity.containsKey("actStart")&&!obj_activity.getString("actStart").equals("")){
				activity.setActStart(DateUtility.string2Date(
						obj_activity.getString("actStart"), "yyyy-MM-dd hh:mm:ss"));
			}
            if(obj_activity.containsKey("actEnd")&&!obj_activity.getString("actEnd").equals("")){
            	activity.setActEnd(DateUtility.string2Date(
    					obj_activity.getString("actEnd"), "yyyy-MM-dd hh:mm:ss"));
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(obj_activity.containsKey("actType")&&!obj_activity.getString("actType").equals("")){
		    activity.setActType(Integer.valueOf(obj_activity.getString("actType")));
		}
		if(obj_activity.containsKey("actRemark")&&!obj_activity.getString("actRemark").equals("")){
			activity.setActRemark(obj_activity.getString("actRemark"));
		}
		if(obj_activity.containsKey("couponShow")&&!obj_activity.getString("couponShow").equals("")){
			activity.setCouponShow(Integer.valueOf(obj_activity
					.getString("couponShow")));
		}
		if(obj_activity.containsKey("couponReceivingConditions")&&!obj_activity.getString("couponReceivingConditions").equals("")){
			activity.setCouponReceivingConditions(obj_activity
					.getString("couponReceivingConditions"));
		}
		if(obj_activity.containsKey("coupons")&&!obj_activity.getString("coupons").equals("")){
			activity.setCoupons(obj_activity.getString("coupons"));
		}
		if(obj_activity.containsKey("banner")&&!obj_activity.getString("banner").equals("")){
			activity.setBanner(obj_activity.getString("banner"));
		}
		if(obj_activity.containsKey("state")&&!obj_activity.getString("state").equals("")){
			activity.setState(Integer.valueOf(obj_activity.getString("state")));
		}
		if(obj_activity.containsKey("qrcode")&&!obj_activity.getString("qrcode").equals("")){
			activity.setQrcode(obj_activity.getString("qrcode"));
		}
		if(obj_activity.containsKey("actId")&&!obj_activity.getString("actId").equals("")){
			activity.setActId(Long.valueOf(obj_activity.getString("actId")));
			activityService.updateActivity(activity);
			
		}else{
			activity.setCreateTime(new Date());
			//新建的活动默认关闭
			activity.setState(2);
			activityService.addActivity(activity);
			if (activity.getActId() == null) {
				return null;
			}
			//自动保存二维码
			QrcodeVo param=new QrcodeVo();
			String content=Constants4System.URL+"/index.html?actId="+activity.getActId();
			String name=sellerNick+"惠淘活动二维码"+activity.getActId();
			param.setContent(content);
			param.setName(name);
			QrcodeDO qrcodeDO =ItemApi.getInstance().createQrcode(param,  getCurrMember().getTaoBaoAccessVo());
			String qrcode=qrcodeDO.getQrcodeUrl();
			Activity activity1 = new Activity();
			activity1.setActId(activity.getActId());
			activity1.setQrcode(qrcode);
			activity1.setSellerNick(activity.getSellerNick());
			activityService.updateActivity(activity1);
			activity.setQrcode(qrcode);
		}
		
		return activity;
	}
	
	public void activityDelete(JSONObject obj_activity) {
		if(!obj_activity.containsKey("actId")||obj_activity.getString("actId").equals("")){
			return;
		}
		Activity activity = new Activity();
		activity.setActId(Long.valueOf(obj_activity.getString("actId")));
		/*
		 * 便于测试以后放开 String sellerNick=
		 * getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
		 * activity.setSellerNick(sellerNick);
		 */
		String sellerNick=getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
		activity.setSellerNick(sellerNick);
		//activity.setSellerNick(obj_activity.getString("sellerNick"));
		// 删除活动包括子表
		activityService.deleteActivity(activity);
	}
	/**
	 * 活动子表的保存或更新
	 * @param obj_activityDetail
	 * @param activity
	 */
	public ActivityDetail activityDetailSaveOrUpdate(JSONObject obj_activityDetail,
			Activity activity) {
		
		ActivityDetail activityDetail = new ActivityDetail();
		activityDetail.setActId(activity.getActId());
		try {
			if(obj_activityDetail.containsKey("startTime")&&!obj_activityDetail.getString("startTime").equals("")){
				activityDetail.setStartTime(DateUtility.string2Date(
						obj_activityDetail.getString("startTime"),
						"yyyy-MM-dd hh:mm:ss"));
			}
			if(obj_activityDetail.containsKey("endTime")&&!obj_activityDetail.getString("endTime").equals("")){
				activityDetail.setEndTime(DateUtility.string2Date(
						obj_activityDetail.getString("endTime"),
						"yyyy-MM-dd hh:mm:ss"));
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(obj_activityDetail.containsKey("actDetailId")&&!obj_activityDetail.getString("actDetailId").equals("")){
			activityDetail.setActDetailId(Long.valueOf(obj_activityDetail.getString("actDetailId")));
			activityDetailService.updateActivityDetail(activityDetail);
		}else{
			
			activityDetailService.addActivityDetail(activityDetail);
			if (activityDetail.getActDetailId() == null) {
				return null;
			}
		}
		return activityDetail;
		
		
	}
	/**
	 * 删除活动子表
	 * @param obj_activityDetail
	 * @param activity
	 */
	public void deleteActivityDetail(JSONObject obj_activityDetail,Activity activity) {
		ActivityDetail activityDetail = new ActivityDetail();
		activityDetail.setActId(activity.getActId());
		if(obj_activityDetail.containsKey("actDetailId")&&!obj_activityDetail.getString("actDetailId").equals("")){
			activityDetail.setActDetailId(Long.valueOf(obj_activityDetail.getString("actDetailId")));
			activityDetailService.deleteActivityDetail(activityDetail);
		}
	}
	
	
	/**
	 * 商品表的更新或添加
	 * @param obj_activityGoodDetail
	 * @param activityDetail
	 */
	public ActivityGoodDetail activityGoodDetailSaveOrUpdate(JSONObject obj_activityGoodDetail,
			ActivityDetail activityDetail){
		
		ActivityGoodDetail activityGoodDetail = new ActivityGoodDetail();
		activityGoodDetail.setActDetailId(activityDetail.getActDetailId());
		if(obj_activityGoodDetail.containsKey("activityPrice")&&!obj_activityGoodDetail.getString("activityPrice").equals("")){
			activityGoodDetail.setActivityPrice(Double
					.valueOf(obj_activityGoodDetail.getString("activityPrice")));
		}
		if(obj_activityGoodDetail.containsKey("currentNum")&&!obj_activityGoodDetail.getString("currentNum").equals("")){
			activityGoodDetail.setCurrentNum(Integer.valueOf(obj_activityGoodDetail
					.getString("currentNum")));
		}
		if(obj_activityGoodDetail.containsKey("detailUrl")&&!obj_activityGoodDetail.getString("detailUrl").equals("")){
			activityGoodDetail.setDetailUrl(obj_activityGoodDetail
					.getString("detailUrl"));
		}
		if(obj_activityGoodDetail.containsKey("isSaleOut")&&!obj_activityGoodDetail.getString("isSaleOut").equals("")){
			activityGoodDetail.setIsSaleOut(Integer.valueOf(obj_activityGoodDetail
					.getString("isSaleOut")));
		}
		if(obj_activityGoodDetail.containsKey("oldNum")&&!obj_activityGoodDetail.getString("oldNum").equals("")){
			activityGoodDetail.setOldNum(Integer.valueOf(obj_activityGoodDetail
					.getString("oldNum")));
		}
		if(obj_activityGoodDetail.containsKey("overWay")&&!obj_activityGoodDetail.getString("overWay").equals("")){
			activityGoodDetail.setOverWay(Integer.valueOf(obj_activityGoodDetail
					.getString("overWay")));
		}
		if(obj_activityGoodDetail.containsKey("pic_url")&&!obj_activityGoodDetail.getString("pic_url").equals("")){
			activityGoodDetail.setPic_url(obj_activityGoodDetail
					.getString("pic_url"));
		}
		if(obj_activityGoodDetail.containsKey("price")&&!obj_activityGoodDetail.getString("price").equals("")){
			activityGoodDetail.setPrice(Double.valueOf(obj_activityGoodDetail
					.getString("price")));
		}
		if(obj_activityGoodDetail.containsKey("title")&&!obj_activityGoodDetail.getString("title").equals("")){
			activityGoodDetail.setTitle(obj_activityGoodDetail.getString("title"));
		}
		if(obj_activityGoodDetail.containsKey("type")&&!obj_activityGoodDetail.getString("type").equals("")){
			activityGoodDetail.setType(Integer.valueOf(obj_activityGoodDetail
					.getString("type")));
		}
		if(obj_activityGoodDetail.containsKey("numiid")&&!obj_activityGoodDetail.getString("numiid").equals("")){
			activityGoodDetail.setNumiid(Long
					.valueOf(obj_activityGoodDetail.getString("numiid")));
		}
		if(obj_activityGoodDetail.containsKey("id")&&!obj_activityGoodDetail.getString("id").equals("")){
			activityGoodDetail.setId(Long.valueOf(obj_activityGoodDetail.getString("id")));
			activityGoodDetailService.updateActivityGoodDetail(activityGoodDetail);
		}else{
			activityGoodDetailService.addActivityGoodDetail(activityGoodDetail);
		}
		return activityGoodDetail;
	}
	/**
	 * 删除商品表
	 * @param obj_activityGoodDetail
	 */
	public void deleteActivityGoodDetail(JSONObject obj_activityGoodDetail,ActivityDetail activityDetail) {
		ActivityGoodDetail activityGoodDetail = new ActivityGoodDetail();
		activityGoodDetail.setActDetailId(activityDetail.getActDetailId());
		if(obj_activityGoodDetail.containsKey("id")&&!obj_activityGoodDetail.getString("id").equals("")){
			activityGoodDetail.setId(Long.valueOf(obj_activityGoodDetail.getString("id")));
			activityGoodDetailService.deleteActivityGoodDetail(activityGoodDetail);
		}
	}
	


}
