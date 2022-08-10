package com.eren.springboot.demo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eren.springboot.demo.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUserName(String userName);
}
