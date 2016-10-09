package com.stockbrokerfrommars.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stockbrokerfrommars.web.bean.LoginInfo;
import com.stockbrokerfrommars.web.service.LoginService;

@Controller
public class LoginController {
	private LoginService loginService;

	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String doLogin(Model model,LoginInfo loginInfo) {
		
		if(loginService.Login(loginInfo)){
			return "loginsucess";
		}else{
			return "loginfailed";
		}	
	}

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	
}
