package com.eren.springboot.demo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eren.springboot.demo.app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findRoleByName(String roleName);
}
