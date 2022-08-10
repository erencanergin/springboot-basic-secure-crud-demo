package com.eren.springboot.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eren.springboot.demo.app.dao.LogRepository;
import com.eren.springboot.demo.app.entity.Log;

@Service
public class LogServiceImplementation implements LogService {

	LogRepository logRepository;
	
	@Autowired
	public LogServiceImplementation(LogRepository logRepository) {
		this.logRepository = logRepository;
	}


	@Override
	@Transactional
	public void save(Log log) {
		logRepository.save(log);
	}

}
