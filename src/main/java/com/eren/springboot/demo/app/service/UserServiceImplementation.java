package com.eren.springboot.demo.app.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eren.springboot.demo.app.dao.RoleRepository;
import com.eren.springboot.demo.app.dao.UserRepository;
import com.eren.springboot.demo.app.entity.Role;
import com.eren.springboot.demo.app.entity.User;
import com.eren.springboot.demo.app.user.AppUser;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	@Lazy
	private BCryptPasswordEncoder passwordEncoder;
	

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(AppUser appUser) {
		
		User userObj = new User();
		
		userObj.setUserName(appUser.getUserName());
		userObj.setPassword(passwordEncoder.encode(appUser.getPassword()));
		userObj.setFirstName(appUser.getFirstName());
		userObj.setLastName(appUser.getLastName());
		userObj.setEmail(appUser.getEmail());
		
		// set default role as employee
		userObj.setRoles(Arrays.asList(roleRepository.findRoleByName("ROLE_EMPLOYEE")));
		
		userRepository.save(userObj);
	}

}
