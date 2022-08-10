package com.eren.springboot.demo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eren.springboot.demo.app.entity.Log;

public interface LogRepository extends JpaRepository<Log, Long>{

}
