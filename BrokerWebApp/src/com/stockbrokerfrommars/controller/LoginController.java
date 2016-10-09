package com.stockbrokerfrommars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String doLogin(Model model) {
		return "loginsucess";
	}
}
