package com.stockbrokerfrommars.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockbrokerfrommars.web.bean.LoginInfo;
import com.stockbrokerfrommars.web.dao.LoginInfoDao;

@Service("loginService")
public class LoginService {
	private LoginInfoDao loginInfoDao;

	public boolean Login(LoginInfo loginInfo) {
		LoginInfo userInfo = loginInfoDao.getLoginInfo(loginInfo.getUsername());
		
		if(userInfo!=null){
			return userInfo.getPassword().equals(loginInfo.getPassword());
		}else{
			return false;
		}
	}

	@Autowired
	public void setLoginInfoDao(LoginInfoDao loginInfoDao) {
		this.loginInfoDao = loginInfoDao;
	}

}
