package com.eren.springboot.demo.app.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.eren.springboot.demo.app.entity.User;
import com.eren.springboot.demo.app.service.UserService;

@Component
public class AuthenticationSuccessHandlerImplementation implements AuthenticationSuccessHandler {

	private UserService userService;
	
	@Autowired
	public AuthenticationSuccessHandlerImplementation(UserService userService) {
		this.userService = userService;
	}


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String userName = authentication.getName();
		User user = userService.findByUserName(userName);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		response.sendRedirect(request.getContextPath() + "/");
		
	}

}
