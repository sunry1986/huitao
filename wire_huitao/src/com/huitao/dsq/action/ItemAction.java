package com.huitao.dsq.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.huitao.baseAction.BaseAction;
import com.huitao.dao.factory.DaoFactory;
import com.huitao.dsq.bean.Activity;
import com.huitao.dsq.service.ActivityService;
import com.huitao.sys.utils.TaobaoUtils;
import com.huitao.taobao.api.ItemApi;
import com.huitao.taobao.vo.FeedWeiTaoVo;
import com.huitao.taobao.vo.GoodsDownPara;
import com.huitao.taobao.vo.QrcodeVo;
import com.huitao.taobao.vo.RegisterActivityVo;
import com.huitao.taobao.vo.SendFlowParamVo;

public class ItemAction extends BaseAction{
	
	/**
	 *
	 */
	private static final long serialVersionUID = -2645512227678902871L;
	private ActivityService activityService = (ActivityService) DaoFactory
			.getInstance().getService("activityService");
    public void searchItemOnsale(){
    	GoodsDownPara  goodsDownPara=new GoodsDownPara();
    	String orderBy=(String)this.getRequest().getParameter("orderBy");
		String pageSize= (String)this.getRequest().getParameter("pageSize");
		String pageNo= (String)this.getRequest().getParameter("pageNo");
//		String sellerCids=(String)this.getRequest().getParameter("sellerCids");
		String q=(String)this.getRequest().getParameter("q");
		String fields=(String)this.getRequest().getParameter("fields");
		if(orderBy!=null&&!orderBy.equals("")){
			goodsDownPara.setOrderBy(orderBy);
		}
		if(pageSize!=null&&!pageSize.equals("")){
			goodsDownPara.setPageSize(Long.valueOf(pageSize));
		}
		if(pageNo!=null&&!pageNo.equals("")){
			goodsDownPara.setPageNo(Long.valueOf(pageNo));
		}
		if(q!=null&&!q.equals("")){
			goodsDownPara.setQ(q);
		}
		if(fields!=null&&!fields.equals("")){
			goodsDownPara.setFields(fields);
		}
    	 Map<String, Object> map=ItemApi.getInstance().downOnsaleItems("searchItemOnsale", goodsDownPara, getCurrMember().getTaoBaoAccessVo());
     	 getPrintWriter(map);
    }
    public void searchItemInventory(){
    	GoodsDownPara  goodsDownPara=new GoodsDownPara();
    	String orderBy=(String)this.getRequest().getParameter("orderBy");
		String pageSize= (String)this.getRequest().getParameter("pageSize");
		String pageNo= (String)this.getRequest().getParameter("pageNo");
//		String sellerCids=(String)this.getRequest().getParameter("sellerCids");
		String q=(String)this.getRequest().getParameter("q");
		String fields=(String)this.getRequest().getParameter("fields");
		if(orderBy!=null&&!orderBy.equals("")){
			goodsDownPara.setOrderBy(orderBy);
		}
		if(pageSize!=null&&!pageSize.equals("")){
			goodsDownPara.setPageSize(Long.valueOf(pageSize));
		}
		if(pageNo!=null&&!pageNo.equals("")){
			goodsDownPara.setPageNo(Long.valueOf(pageNo));
		}
		if(q!=null&&!q.equals("")){
			goodsDownPara.setQ(q);
		}
		if(fields!=null&&!fields.equals("")){
			goodsDownPara.setFields(fields);
		}
    	 Map<String, Object> map=ItemApi.getInstance().downInventoryItems("searchItemInventory", goodsDownPara, getCurrMember().getTaoBaoAccessVo());
     	 getPrintWriter(map);
    }
    public void createQrcode(){
    	QrcodeVo param=new QrcodeVo();
    	String type=(String)this.getRequest().getParameter("type");
    	String content=(String)this.getRequest().getParameter("content");
    	String name=(String)this.getRequest().getParameter("name");
    	String style=(String)this.getRequest().getParameter("style");
    	String size=(String)this.getRequest().getParameter("size");
    	if(type!=null&&!type.equals("")){
    		param.setType(type);
    	}
		if(content!=null&&!content.equals("")){
			param.setContent(content)	;	
		 }
		if(name!=null&&!name.equals("")){
			param.setName(name);
		}
		if(style!=null&&!style.equals("")){
			param.setStyle(style);
		}
		if(size!=null&&!size.equals("")){
			param.setSize(Long.valueOf(size));
		}
		Map<String, Object> map =null;//ItemApi.getInstance().createQrcode(param,  getCurrMember().getTaoBaoAccessVo());
		getPrintWriter(map);
    }
    /**
     * 注册活动
     */
    public void registerActivity(){
    	RegisterActivityVo  param=new RegisterActivityVo();
    	 String entryUrl=(String)this.getRequest().getParameter("entryUrl");
    	 String bizId=(String)this.getRequest().getParameter("bizId");
    	 String description=(String)this.getRequest().getParameter("description");
    	 String name=(String)this.getRequest().getParameter("name");
    	 String picture=(String)this.getRequest().getParameter("picture");
    	 String hasValidTime=(String)this.getRequest().getParameter("hasValidTime");
    	 String endTime=(String)this.getRequest().getParameter("endTime");
    	 String startTime=(String)this.getRequest().getParameter("startTime");
    	 String actId=(String)this.getRequest().getParameter("actId");//活动id 用于更新活动状态
    	 if(actId!=null&&!actId.equals("")){
     		
     	}else{
     		 Map<String, Object> map = new HashMap<String, Object>();
      		 map.put("message", "活动id传参错误");
      		 getPrintWriter(map);
     	}
    	 if(entryUrl!=null&&!entryUrl.equals("")){
     		param.setEntryUrl(entryUrl);
     	}
    	 if(bizId!=null&&!bizId.equals("")){
     		param.setBizId(bizId);
     	}
    	 if(description!=null&&!description.equals("")){
     		param.setDescription(description);
     	}
    	 if(name!=null&&!name.equals("")){
     		param.setName(name);
     	}
    	 if(picture!=null&&!picture.equals("")){
     		param.setPicture(picture);
     	}
    	 if(hasValidTime!=null&&!hasValidTime.equals("")){
     		param.setHasValidTime(Boolean.valueOf(hasValidTime));
     	}
    	 if(endTime!=null&&!endTime.equals("")){
     		param.setEndTime(endTime);
     	}
    	 if(startTime!=null&&!startTime.equals("")){
     		param.setStartTime(startTime);
     	}
    	 Map<String, Object> map =ItemApi.getInstance().registerActivity(param,  getCurrMember().getTaoBaoAccessVo());
    	 if(map!=null && map.get("register_success")!=null){
    		 String register_success=String.valueOf(map.get("register_success")) ;
    		 if(register_success.equals("true")){
    			 //活动注册成功，更新活动表state
    			 Activity activity = new Activity();
    			 activity.setActId(Long.valueOf(actId));
    			 activity.setState(2);
    			 String sellerNick=getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
   				 activity.setSellerNick(sellerNick);
    			 activityService.updateActivity(activity);
    		 }
    	 }
    	 getPrintWriter(map);
    }
    /**
     * 关闭活动
     */
    public void unRegisterActivity(){
    	
    	String  bizId=(String)this.getRequest().getParameter("bizId");
    	String actId=(String)this.getRequest().getParameter("actId");//活动id 用于更新活动状态
    	if(actId!=null&&!actId.equals("")){
    		
    	}else{
    		 Map<String, Object> map = new HashMap<String, Object>();
     		 map.put("message", "活动id传参错误");
     		 getPrintWriter(map);
    	}
    	if(bizId!=null&&!bizId.equals("")){
    		 Map<String, Object> map =ItemApi.getInstance().unRegisterActivity(bizId, getCurrMember().getTaoBaoAccessVo());
    		 if(map!=null && map.get("register_success")!=null){
        		 String register_success=String.valueOf(map.get("register_success")) ;
        		 if(register_success.equals("true")){
        			 //活动注册成功，更新活动表state
        			 Activity activity = new Activity();
        			 activity.setActId(Long.valueOf(actId));
        			 activity.setState(1);
        			 String sellerNick=getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
       				 activity.setSellerNick(sellerNick);
        			 activityService.updateActivity(activity);
        		 }
        	 }
    		 getPrintWriter(map);
     	}else{
     		 Map<String, Object> map = new HashMap<String, Object>();
     		 map.put("message", "bizId传参错误");
     		 getPrintWriter(map);
     	}
    	
    }
    /**
     * 推广淘小铺isv 活动到微淘feed
     */
    public void feedWeiTao(){
    	FeedWeiTaoVo param=new FeedWeiTaoVo();
    	 String coverPath=(String)this.getRequest().getParameter("coverPath");
    	 String detailUrl=(String)this.getRequest().getParameter("detailUrl");
    	 String title=(String)this.getRequest().getParameter("title");
    	 String summary=(String)this.getRequest().getParameter("summary");
    	 String startTime=(String)this.getRequest().getParameter("startTime");
    	 String endTime=(String)this.getRequest().getParameter("endTime");
    	 String bizId=(String)this.getRequest().getParameter("bizId");
    	 if(coverPath!=null&&!coverPath.equals("")){
      		param.setCoverPath(coverPath);
      	}
    	 if(detailUrl!=null&&!detailUrl.equals("")){
       		param.setDetailUrl(detailUrl);
       	}
    	 if(title!=null&&!title.equals("")){
       		param.setTitle(title);
       	}
    	if(summary!=null&&!summary.equals("")){
       		param.setSummary(summary);
       	}
    	if(startTime!=null&&!startTime.equals("")){
       		
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
            Date dateStart;
	   		try {
	   			dateStart = sdf.parse(startTime);
	   			param.setStartTime(dateStart.getTime());
	   		} catch (ParseException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}
       	}
    	if(endTime!=null&&!endTime.equals("")){
       		
       		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
            Date dateEnd;
	   		try {
	   			dateEnd = sdf.parse(endTime);
	   			param.setEndTime(dateEnd.getTime());
	   		} catch (ParseException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}
       	}
    	if(bizId!=null&&!bizId.equals("")){
       		param.setBizId(Long.valueOf(bizId));
       	}
    	 Map<String, Object> map =ItemApi.getInstance().feedWeiTao(param, getCurrMember().getTaoBaoAccessVo());
    	 getPrintWriter(map);
    }
    /**
     * 流量平台用户签约情况查询
     */
    public void walletSign(){
    	String userNick=(String)this.getRequest().getParameter("userNick");
    	if(userNick!=null&&!userNick.equals("")){
   		   Map<String, Object> map =ItemApi.getInstance().walletSign(userNick, getCurrMember().getTaoBaoAccessVo());
   		   getPrintWriter(map);
    	}else{
    		 Map<String, Object> map = new HashMap<String, Object>();
    		 map.put("message", "userNick传参错误");
    		 getPrintWriter(map);
    	}
    }
    /**
     * 流量发放
     */
    public void sendFlow(){
    	  SendFlowParamVo param=new SendFlowParamVo();
    	  String buyerNick=(String)this.getRequest().getParameter("buyerNick");
          String realNick=(String)this.getRequest().getParameter("realNick");
          String serial=(String)this.getRequest().getParameter("serial");
          String flow=(String)this.getRequest().getParameter("flow");
          String reason=(String)this.getRequest().getParameter("reason");
          String always=(String)this.getRequest().getParameter("always");
          
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
          Map<String, Object> map =ItemApi.getInstance().sendFlow(param, getCurrMember().getTaoBaoAccessVo());
          getPrintWriter(map);
    }
    /**
     *  流量发放(用户id)
     */
    public void FlowPublishByUserId(){
    	
    	
    	 SendFlowParamVo param=new SendFlowParamVo();
         String serial=(String)this.getRequest().getParameter("serial");
         String flow=(String)this.getRequest().getParameter("flow");
         String reason=(String)this.getRequest().getParameter("reason");
         String always=(String)this.getRequest().getParameter("always");
         String userId=(String)this.getRequest().getParameter("userId");
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
         if(userId!=null&&!userId.equals("")){
      		param.setUserId(userId);
         }
         
         Map<String, Object> map =ItemApi.getInstance().FlowPublishByUserId(param, getCurrMember().getTaoBaoAccessVo());
         getPrintWriter(map);
   }
   
    
}
