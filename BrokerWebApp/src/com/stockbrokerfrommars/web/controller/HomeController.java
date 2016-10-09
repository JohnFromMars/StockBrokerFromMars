package com.stockbrokerfrommars.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showHome(Model model){
		model.addAttribute("name", "hello !");
		return "home";
	}
}
