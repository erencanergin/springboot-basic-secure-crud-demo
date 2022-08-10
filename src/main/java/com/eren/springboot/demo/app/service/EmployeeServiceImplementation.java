package com.eren.springboot.demo.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eren.springboot.demo.app.dao.EmployeeRepository;
import com.eren.springboot.demo.app.entity.Employee;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	
	EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		
		Optional<Employee> result = employeeRepository.findById(id);
		
		Employee employee = null;
		
		if (result.isPresent()) {
			employee = result.get();
		} else {
			throw new RuntimeException("Didn't find the employee id - " + id);
		}
		
		return employee;
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		
		employeeRepository.save(employee);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		
		employeeRepository.deleteById(id);
	}

}
