package com.huitao.dsq.action;

import java.util.HashMap;
import java.util.Map;

import com.huitao.baseAction.BaseAction;
import com.huitao.dao.factory.DaoFactory;
import com.huitao.dsq.bean.UserInfo;
import com.huitao.dsq.service.UserService;
import com.huitao.sys.commons.Constants4System;
import com.huitao.sys.utils.TaobaoUtils;
import com.huitao.sys.vo.LoginSystemVO;
import com.huitao.sys.vo.TaoBaoAccessVo;
import com.huitao.taobao.api.ItemApi;
import com.taobao.api.domain.Shop;
import com.taobao.api.domain.User;

public class UserAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3802101001116373906L;
	private UserService userService = (UserService)DaoFactory.getInstance().getService("userService");
	private UserInfo userInfo;
	private  String  result="success";
	private  String  message;
	/**
	 *
	 * @return
	 */
	public void findUserInfo(){
		/*
		 * 便于测试以后放开 String sellerNick=
		 * getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
		 * 
		 */
		 String sellerNick="";
		 String nick=this.getRequest().getParameter("sellerNick");
		 if(nick!=null){
			 sellerNick=nick;
		 }else{
			 sellerNick=getCurrMember().getTaoBaoAccessVo().getTaobao_user_nick();
		 }
		 UserInfo userInfo = userService.getByNick(sellerNick);
		 Map<String, Object> map = new HashMap<String, Object>();
		 if(userInfo!=null){
			 userInfo.setToken("");
		 }
	     map.put("userInfo", userInfo);
		 getPrintWriter(map);
	}
	public String login(){
		System.out.println("11111111111");
		return "admin_index";
	}
	public String execute(){
		String  code=getRequest().getParameter("code"); 
		//code未获取返回错误提示
		if(code==null ||code.equals("") ){
	    	  return "errorPage";
	    }
		TaoBaoAccessVo taoBaoAccessVo=TaobaoUtils.getAccessToken(code);
		if(taoBaoAccessVo==null){
			return "errorPage";
		}
		//子账号不允许登录
		if(taoBaoAccessVo.getSub_taobao_user_id()!=null && taoBaoAccessVo.getSub_taobao_user_id()!=""){
			return "errorPage";
		}
		
		if(this.getSession().getAttribute(Constants4System.LOGIN_SYSTEM_USER_IN_SESSION)!=null){
			LoginSystemVO vo =this.getCurrMember();
			vo.setTaoBaoAccessVo(taoBaoAccessVo);
		}else{
			//登录成功设置session
			LoginSystemVO vo = new LoginSystemVO();
			vo.setTaoBaoAccessVo(taoBaoAccessVo);
			vo.setUserInfo(userInfo);
			getRequest().getSession().setAttribute(Constants4System.LOGIN_SYSTEM_USER_IN_SESSION, vo);
			UserInfo userInfo =saveUserInfo( taoBaoAccessVo);
			vo.setUserInfo(userInfo);
		}
		
		return "admin_index";
	}
	public UserInfo saveUserInfo(TaoBaoAccessVo taoBaoAccessVo){
		//判断用户信息是否存在，存在则跳过，不存在则添加
		 UserInfo userInfo = userService.getByNick(taoBaoAccessVo.getTaobao_user_nick());
		if(userInfo==null){
			 userInfo =new UserInfo();
			 userInfo.setSellerNick(taoBaoAccessVo.getTaobao_user_nick());
			 //获取店铺信息
			 Shop shop=   ItemApi.getInstance().shopSearch(taoBaoAccessVo.getTaobao_user_nick());
			 userInfo.setShopName(shop.getTitle());
			 userInfo.setShopSid(shop.getSid());
			 //获取头像
			 User user=ItemApi.getInstance().sellerInfoSearch(taoBaoAccessVo);
			 userInfo.setAvatar(user.getAvatar());
			 userInfo.setToken(taoBaoAccessVo.getAccess_token());
			 userInfo.setUserId(taoBaoAccessVo.getTaobao_user_id());
			 userService.addUserInfo(userInfo);
		}else{
			//更新
			UserInfo userInfo1 =new UserInfo();
			userInfo1.setSellerNick(userInfo.getSellerNick());
			userInfo1.setToken(taoBaoAccessVo.getAccess_token());
			userInfo1.setUserId(taoBaoAccessVo.getTaobao_user_id());
			userService.updateUserInfo(userInfo1);
		}
		return userInfo;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
