package com.eren.springboot.demo.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.eren.springboot.demo.app.entity.User;
import com.eren.springboot.demo.app.user.AppUser;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(AppUser user);
}