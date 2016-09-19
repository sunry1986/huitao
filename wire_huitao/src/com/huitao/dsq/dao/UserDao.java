package com.huitao.dsq.dao;

import com.huitao.dsq.bean.UserInfo;

public interface UserDao {
	     public void addUserInfo(UserInfo userInfo);
         public UserInfo selectByNick(String nick);
         //public int insertInfo(UserBean userBean);
         public void updateUserInfo(UserInfo userInfo);
}
