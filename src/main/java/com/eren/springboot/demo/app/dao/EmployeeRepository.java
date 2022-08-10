package com.eren.springboot.demo.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eren.springboot.demo.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	public List<Employee> findAllByOrderByLastNameAsc();
}
