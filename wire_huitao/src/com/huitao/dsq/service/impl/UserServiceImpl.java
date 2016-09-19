package com.huitao.dsq.service.impl;

import com.huitao.dsq.bean.UserInfo;
import com.huitao.dsq.dao.UserDao;
import com.huitao.dsq.service.UserService;


public class UserServiceImpl  implements UserService {
   private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public UserInfo getByNick(String nick) {
		// TODO Auto-generated method stub
		UserInfo userBean=userDao.selectByNick(nick);
		return userBean;
	}
	public UserInfo addUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		  userDao.addUserInfo(userInfo);
		return userInfo;
	}
	public void updateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		userDao.updateUserInfo(userInfo);
	}

}
