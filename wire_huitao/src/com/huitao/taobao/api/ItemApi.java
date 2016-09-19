package com.huitao.taobao.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.huitao.sys.commons.Constants4System;
import com.huitao.sys.vo.TaoBaoAccessVo;
import com.huitao.taobao.vo.FeedWeiTaoVo;
import com.huitao.taobao.vo.GoodsDownPara;
import com.huitao.taobao.vo.QrcodeVo;
import com.huitao.taobao.vo.RegisterActivityVo;
import com.huitao.taobao.vo.SendFlowParamVo;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.AllsparkResult;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.QrcodeDO;
import com.taobao.api.domain.Shop;
import com.taobao.api.domain.User;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.AlibabaAliqinFlowPublishRequest;
import com.taobao.api.request.AlibabaAliqinFlowWalletSendFlowRequest;
import com.taobao.api.request.AlibabaAliqinFlowWalletSignRequest;
import com.taobao.api.request.AlibabaInteractActivityRegisterRequest;
import com.taobao.api.request.AlibabaInteractActivityUnregisterRequest;
import com.taobao.api.request.ItemsInventoryGetRequest;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.request.MaQrcodeCommonCreateRequest;
import com.taobao.api.request.ShopGetRequest;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.request.WeitaoFeedSynchronizeRequest;
import com.taobao.api.response.AlibabaAliqinFlowPublishResponse;
import com.taobao.api.response.AlibabaAliqinFlowWalletSendFlowResponse;
import com.taobao.api.response.AlibabaAliqinFlowWalletSignResponse;
import com.taobao.api.response.AlibabaInteractActivityRegisterResponse;
import com.taobao.api.response.AlibabaInteractActivityUnregisterResponse;
import com.taobao.api.response.ItemsInventoryGetResponse;
import com.taobao.api.response.ItemsOnsaleGetResponse;
import com.taobao.api.response.MaQrcodeCommonCreateResponse;
import com.taobao.api.response.ShopGetResponse;
import com.taobao.api.response.UserSellerGetResponse;
import com.taobao.api.response.WeitaoFeedSynchronizeResponse;
public class ItemApi {
	private static ItemApi instance;
	private Log log = LogFactory.getLog(getClass()); 
	private ItemApi() {
		;
	}
	public static ItemApi getInstance(){
		if(instance==null){
			instance=new ItemApi();
		}
		return instance;
	}
	/**
	 * 获取出售中商品
	 * @param method
	 * @param param
	 * @param keyVO
	 * @return
	 */
	public Map<String, Object> downOnsaleItems(String method, GoodsDownPara param, TaoBaoAccessVo keyVO) {
		Map<String, Object>  map=new HashMap<String, Object>();
		TaobaoClient client = new DefaultTaobaoClient(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		ItemsOnsaleGetRequest request = new ItemsOnsaleGetRequest();
		request.setFields(param.getFields());
		if(param.getPageSize()<=0) {
			request.setPageSize(10l);	
		} else {
			request.setPageSize(param.getPageSize());
		}
		if(param.getQ() != null) {//商品title
			request.setQ(param.getQ());
		}
		if(param.getSellerCids() != null && !param.getSellerCids().equals("0")) {
			request.setSellerCids(param.getSellerCids());
		}
		if(param.getOrderBy() != null) {
			request.setOrderBy(param.getOrderBy());
		}
		if(param.getPageNo()<=0) {
			request.setPageNo(1L);	
		} else {
			request.setPageNo(param.getPageNo());
		}
			try {
				ItemsOnsaleGetResponse response = client.execute(request, keyVO.getAccess_token());
				if(response.getErrorCode()!=null) {
					if(response.getErrorCode().equals("27") || response.getSubCode().equals("session-not-exist") ||  response.getSubCode().equals("session-expired")){ //如果session无效退出
						log.error("session无效");
					}
				}
				List<Item> tempList = response.getItems();
				map.put("total_results", response.getTotalResults());
				map.put("items", tempList);
			} catch (ApiException e) {
				log.error("downOnsaleItems()异常::"+e+";method:"+method);
				//e.printStackTrace();
			}
	     
		return map; 
	}
	/**
	 * 获取出售中商品
	 * @param method
	 * @param param
	 * @param keyVO
	 * @return
	 */
	public Map<String, Object> downInventoryItems(String method, GoodsDownPara param, TaoBaoAccessVo keyVO) {
		Map<String, Object>  map=new HashMap<String, Object>();
		TaobaoClient client = new DefaultTaobaoClient(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		ItemsInventoryGetRequest request = new ItemsInventoryGetRequest();
		request.setFields(param.getFields());
		if(param.getPageSize()<=0) {
			request.setPageSize(10l);	
		} else {
			request.setPageSize(param.getPageSize());
		}
		if(param.getQ() != null) {//商品title
			request.setQ(param.getQ());
		}
		if(param.getSellerCids() != null && !param.getSellerCids().equals("0")) {
			request.setSellerCids(param.getSellerCids());
		}
		if(param.getOrderBy() != null) {
			request.setOrderBy(param.getOrderBy());
		}
		if(param.getPageNo()<=0) {
			request.setPageNo(1L);	
		} else {
			request.setPageNo(param.getPageNo());
		}
			try {
				ItemsInventoryGetResponse  response = client.execute(request, keyVO.getAccess_token());
				if(response.getErrorCode()!=null) {
					if(response.getErrorCode().equals("27") || response.getSubCode().equals("session-not-exist") ||  response.getSubCode().equals("session-expired")){ //如果session无效退出
						log.error("session无效");
					}
				}
				List<Item> tempList = response.getItems();
				map.put("total_results", response.getTotalResults());
				map.put("items", tempList);
			} catch (ApiException e) {
				log.error("downOnsaleItems()异常::"+e+";method:"+method);
				//e.printStackTrace();
			}
	     
		return map; 
	}
	/**
	 *二维码获取 taobao.ma.qrcode.common.create
	 * @param param
	 * @param keyVO
	 * @return
	 */
	public QrcodeDO createQrcode( QrcodeVo param, TaoBaoAccessVo keyVO) {
		Map<String, Object>  map=new HashMap<String, Object>();
		//TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest","23300784","5c8550387637aa3ebabd1dad910023ac");//(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		TaobaoClient client = new DefaultTaobaoClient(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		MaQrcodeCommonCreateRequest req = new MaQrcodeCommonCreateRequest();
		if(param.getType()!=null && !param.getType().equals("")){
			req.setType(param.getType());
		}
		if(param.getContent()!=null && !param.getContent().equals("")){
			req.setContent(param.getContent());
		}
		if(param.getSize()>0){
			req.setSize(param.getSize());
		}
		if(param.getStyle()!=null && !param.getStyle().equals("")){
			req.setStyle(param.getStyle());
		}
		if(param.getName()!=null && !param.getName().equals("")){
			req.setName(param.getName());
		}
		QrcodeDO qrcodeDO=null;
		try {
			MaQrcodeCommonCreateResponse rsp = client.execute(req, keyVO.getAccess_token());
			if(rsp.getErrorCode()!=null) {
				if(rsp.getMsg()!=null){
					map.put("message",rsp);
				}else{
					map.put("message",rsp.getErrorCode());
				}
			}else{
				List<QrcodeDO> tempList=rsp.getModules(); 
				 //map.put("QrcodeDO", tempList.get(0));
				 qrcodeDO=tempList.get(0);
				 qrcodeDO.getShortUrl();
			}
			
			
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return qrcodeDO; 
	}
	/**
	 * 注册活动alibaba.interact.activity.register
	 * @param param
	 * @param keyVO
	 * @return
	 */
	public Map<String, Object> registerActivity (RegisterActivityVo param, TaoBaoAccessVo keyVO) {
		Map<String, Object>  map=new HashMap<String, Object>();
		TaobaoClient client = new DefaultTaobaoClient(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		AlibabaInteractActivityRegisterRequest req = new AlibabaInteractActivityRegisterRequest();
		if(param.getEntryUrl()!=null && !param.getEntryUrl().equals("")){
			req.setEntryUrl(param.getEntryUrl());
		}
		if(param.getBizId()!=null && !param.getBizId().equals("")){
			req.setBizId(param.getBizId());
		}
		if(param.getDescription()!=null && !param.getDescription().equals("")){
			req.setDescription(param.getDescription());
		}
		if(param.getEndTime()!=null && !param.getEndTime().equals("")){
			req.setEndTime(StringUtils.parseDateTime(param.getEndTime().toString()));
		}
		if(param.getName()!=null && !param.getName().equals("")){
			req.setName(param.getName());
		}
		if(param.getPicture()!=null && !param.getPicture().equals("")){
			req.setPicture(param.getPicture());
		}
		if(param.getStartTime()!=null && !param.getStartTime().equals("")){
			req.setStartTime(StringUtils.parseDateTime(param.getStartTime()));
		}
		if(param.getName()!=null && !param.getName().equals("")){
			req.setName(param.getName());
		}
			req.setHasValidTime(param.isHasValidTime());
		AlibabaInteractActivityRegisterResponse rsp;
		try {
			rsp = client.execute(req, keyVO.getAccess_token());
			if(rsp.getErrorCode()!=null) {
				if(rsp.getMsg()!=null){
					map.put("message",rsp);
				}else{
					map.put("message",rsp.getErrorCode());
				}
				
				
			}else{
                AllsparkResult allsparkResult=rsp.getRegisterSucessInfo(); 
				map.put("allspartResult", allsparkResult);
				map.put("register_success", allsparkResult.getSuccess());
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map; 
	}
	/**
	 * 关闭活动alibaba.interact.activity.unregister
	 * @param bizId
	 * @param keyVO
	 * @return
	 */
	public Map<String, Object> unRegisterActivity (String bizId, TaoBaoAccessVo keyVO) {
		Map<String, Object>  map=new HashMap<String, Object>();
		TaobaoClient client = new DefaultTaobaoClient(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		AlibabaInteractActivityUnregisterRequest req = new AlibabaInteractActivityUnregisterRequest();
		if(bizId!=null && !bizId.equals("")){
			req.setBizId(bizId);
		}
		AlibabaInteractActivityUnregisterResponse rsp;
		try {
			rsp = client.execute(req, keyVO.getAccess_token());
			if(rsp.getErrorCode()!=null) {
				if(rsp.getMsg()!=null){
					map.put("message",rsp);
				}else{
					map.put("message",rsp.getErrorCode());
				}
			}else{
				AllsparkResult allsparkResult= rsp.getUnregisterResult();
				map.put("allspartResult", allsparkResult);
				map.put("register_success", allsparkResult.getSuccess());
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map; 
	}
	/**
	 * 推广淘小铺isv 活动到微淘feed  taobao.weitao.feed.synchronize
	 * @param param
	 * @param keyVO
	 * @return
	 */
	public Map<String, Object> feedWeiTao ( FeedWeiTaoVo param,TaoBaoAccessVo keyVO) {
		Map<String, Object>  map=new HashMap<String, Object>();
		TaobaoClient client = new DefaultTaobaoClient(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		WeitaoFeedSynchronizeRequest req = new WeitaoFeedSynchronizeRequest();
		if(param.getCoverPath()!=null && !param.getCoverPath().equals("")){
			req.setCoverPath(param.getCoverPath());
		}
		if(param.getDetailUrl()!=null && !param.getDetailUrl().equals("")){
			req.setDetailUrl(param.getDetailUrl());
		}
		if(param.getSummary()!=null && !param.getSummary().equals("")){
			req.setSummary(param.getSummary());
		}
		if(param.getTitle()!=null && !param.getTitle().equals("")){
			req.setTitle(param.getTitle());
		}
		if(param.getBizId()!=null){
			req.setBizId(param.getBizId());
		}
		if(param.getEndTime()!=null){
			req.setEndTime(param.getEndTime());
		}
		if(param.getStartTime()!=null){
			req.setStartTime(param.getStartTime());
		}
		
		WeitaoFeedSynchronizeResponse  rsp;
		try {
			rsp = client.execute(req, keyVO.getAccess_token());
			System.out.println(rsp.getBody());
			if(rsp.getErrorCode()!=null) {
				if(rsp.getMsg()!=null){
					map.put("message",rsp);
				}else{
					map.put("message",rsp.getErrorCode());
				}
			}else{
                boolean temp=rsp.getResult();
				map.put("result", temp);
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map; 
	}
	/**
	 * alibaba.aliqin.flow.wallet.sign流量平台用户签约情况查询
	 * @param userNick
	 * @param keyVO
	 * @return
	 */
	public Map<String, Object>  walletSign (String userNick, TaoBaoAccessVo keyVO) {
		Map<String, Object>  map=new HashMap<String, Object>();
		TaobaoClient client = new DefaultTaobaoClient(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		AlibabaAliqinFlowWalletSignRequest  req = new AlibabaAliqinFlowWalletSignRequest ();
		if(userNick!=null && !userNick.equals("")){
			req.setUserNick(userNick);
		}
		AlibabaAliqinFlowWalletSignResponse  rsp;
		try {
			rsp = client.execute(req, keyVO.getAccess_token());
			System.out.println(rsp.getBody());
			if(rsp.getErrorCode()!=null) {
				if(rsp.getMsg()!=null){
					map.put("message",rsp);
				}else{
					map.put("message",rsp.getErrorCode());
				}
			}else{
				String value= rsp.getValue();
				map.put("value", value);
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map; 
	}
	/**
	 * alibaba.aliqin.flow.wallet.send.flow (流量发放)
	 * @param keyVO
	 * @return
	 */
	public Map<String, Object>  sendFlow (SendFlowParamVo param,TaoBaoAccessVo keyVO) {
		Map<String, Object>  map=new HashMap<String, Object>();
		TaobaoClient client = new DefaultTaobaoClient(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		AlibabaAliqinFlowWalletSendFlowRequest req = new AlibabaAliqinFlowWalletSendFlowRequest();
		if(param.getBuyerNick()!=null && !param.getBuyerNick().equals("")){
			req.setBuyerNick(param.getBuyerNick());
		}
		if(param.getRealNick()!=null && !param.getRealNick().equals("")){
			req.setRealNick(param.getRealNick());
		}
		if(param.getSerial()!=null && !param.getSerial().equals("")){
			req.setSerial(param.getSerial());
		}
		if(param.getFlow()!=null && !param.getFlow().equals("")){
			req.setFlow(param.getFlow());
		}
		if(param.getReason()!=null && !param.getReason().equals("")){
			req.setReason(param.getReason());
		}
		if(param.getAlways()!=null && !param.getAlways().equals("")){
			req.setAlways(param.getAlways());
		}
		AlibabaAliqinFlowWalletSendFlowResponse  rsp;
		try {
			rsp = client.execute(req, keyVO.getAccess_token());
			System.out.println(rsp.getBody());
			if(rsp.getErrorCode()!=null) {
				if(rsp.getMsg()!=null){
					map.put("message",rsp);
				}else{
					map.put("message",rsp.getErrorCode());
				}
			}else{
				String value= rsp.getValue();
				map.put("value", value);
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map; 
	}
	/**
	 * alibaba.aliqin.flow.publish (流量发放(用户id))
	 * @param keyVO
	 * @return
	 */
	public Map<String, Object>  FlowPublishByUserId  (SendFlowParamVo param,TaoBaoAccessVo keyVO) {
		Map<String, Object>  map=new HashMap<String, Object>();
		TaobaoClient client = new DefaultTaobaoClient(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		AlibabaAliqinFlowPublishRequest req = new AlibabaAliqinFlowPublishRequest();
		if(param.getUserId()!=null && !param.getUserId().equals("")){
			req.setUserId(param.getUserId());
		}
		if(param.getSerial()!=null && !param.getSerial().equals("")){
			req.setSerial(param.getSerial());
		}
		if(param.getFlow()!=null && !param.getFlow().equals("")){
			req.setFlow(param.getFlow());
		}
		if(param.getReason()!=null && !param.getReason().equals("")){
			req.setReason(param.getReason());
		}
		if(param.getAlways()!=null && !param.getAlways().equals("")){
			req.setAlways(param.getAlways());
		}
		AlibabaAliqinFlowPublishResponse   rsp;
		try {
			rsp = client.execute(req, keyVO.getAccess_token());
			System.out.println(rsp.getBody());
			if(rsp.getErrorCode()!=null) {
				if(rsp.getErrorCode().equals("27") || rsp.getSubCode().equals("session-not-exist") ||  rsp.getSubCode().equals("session-expired")){ //如果session无效退出
					log.error("session无效");
				}
				map.put("message",rsp.getErrorCode());
			}else{
				String value= rsp.getValue();
				map.put("value", value);
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map; 
	}
	/**
	 * 获取店铺信息
	 * @param nick
	 * @return
	 */
	public Shop   shopSearch(String nick){
		TaobaoClient client = new DefaultTaobaoClient(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		ShopGetRequest req = new ShopGetRequest();
		req.setFields("sid,title");
		req.setNick(nick);
		ShopGetResponse rsp=null;
		Shop shop=null;
		try {
			
			rsp = client.execute(req);
			if(rsp.getErrorCode()!=null) {
				log.error(rsp);
			}else{
				shop=  rsp.getShop();
			}
				
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shop;
		
	}
	/**
	 * taobao.user.seller.get (查询卖家用户信息)
	 * @param keyVO
	 */
	public User sellerInfoSearch(TaoBaoAccessVo keyVO){
		
		TaobaoClient client = new DefaultTaobaoClient(Constants4System.API_URL, Constants4System.APPKEY, Constants4System.APPSECRET);
		UserSellerGetRequest req = new UserSellerGetRequest();
		req.setFields("avatar");
		UserSellerGetResponse rsp;
		User user=null;
		try {
			rsp = client.execute(req, keyVO.getAccess_token());
			if(rsp.getErrorCode()!=null) {
				log.error(rsp);
			}else{
				  user=rsp.getUser();
			}
				
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	
	public static void main(String[] args){
		TaoBaoAccessVo keyVO=new TaoBaoAccessVo();
		keyVO.setAccess_token("610002237dbebc0412141c66279a67155ce5c49f9d8f797740700517");
		QrcodeVo param=new QrcodeVo();
		param.setContent("http://puyun.ews.m.jaeapp.com");
		param.setName("小叮当_淘淘的活动");
		System.out.println(param.getSize());
		ItemApi.getInstance().createQrcode(param, keyVO);
		
	}
}
