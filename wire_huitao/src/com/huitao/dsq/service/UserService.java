package com.huitao.dsq.service;
import com.huitao.dsq.bean.UserInfo;

public interface UserService {
	/**
	 *
	 * @param nick
	 * @return
	 */
	public UserInfo getByNick(String nick);
	public UserInfo addUserInfo(UserInfo userInfo);
	public void updateUserInfo(UserInfo userInfo);
}
