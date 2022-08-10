package com.eren.springboot.demo.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eren.springboot.demo.app.entity.User;
import com.eren.springboot.demo.app.service.UserService;
import com.eren.springboot.demo.app.user.AppUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model model) {
		
		model.addAttribute("newAppUser", new AppUser());
		
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	@Validated
	public String processRegistrationForm(@Valid @ModelAttribute("newAppUser") AppUser appUser, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "registration-form";
		}
		
		User existing = userService.findByUserName(appUser.getUserName());
		if (existing != null) {
			model.addAttribute("newAppUser", new AppUser());
			model.addAttribute("registrationError", "User name already exists.");
			
			return "registration-form";
		}
		
		userService.save(appUser);
		
		return "redirect:/";
	}
}
