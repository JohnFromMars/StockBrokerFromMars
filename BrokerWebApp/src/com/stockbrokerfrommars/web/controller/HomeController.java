package com.stockbrokerfrommars.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showLogin() {
		return "home";
	}
	
	@RequestMapping("/logout")
	public String showLogout() {
		return "home";
	}
	
	@RequestMapping("/main")
	public String showMain(){
		return "main";
	}
}
