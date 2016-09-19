package com.huitao.sys.vo;

import com.huitao.dsq.bean.UserInfo;


public class LoginSystemVO {
	private TaoBaoAccessVo taoBaoAccessVo;
	private UserInfo  userInfo;

	public TaoBaoAccessVo getTaoBaoAccessVo() {
		return taoBaoAccessVo;
	}

	public void setTaoBaoAccessVo(TaoBaoAccessVo taoBaoAccessVo) {
		this.taoBaoAccessVo = taoBaoAccessVo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
