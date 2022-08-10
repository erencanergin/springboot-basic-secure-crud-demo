package com.eren.springboot.demo.app.service;

import java.util.List;

import com.eren.springboot.demo.app.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	public Employee findById(int id);
	public void save(Employee employee);
	public void deleteById(int id);
}
