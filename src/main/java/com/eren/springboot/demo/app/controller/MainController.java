package com.eren.springboot.demo.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String showLoginPage() {
		
		return "login";
		
	}

}
