package com.stockbrokerfrommars.web.controller;

import javax.servlet.http.HttpSession;

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
	public String doLogin(Model model,HttpSession session, LoginInfo loginInfo) {

		if (loginService.Login(loginInfo)) {
			session.setAttribute("username", loginInfo.getUsername());
			return "main";
		} else {
			return "home";
		}
	}

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
