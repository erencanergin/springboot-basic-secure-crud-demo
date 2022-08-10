package com.eren.springboot.demo.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "log")
@Data
public class Log {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "created_date")
	@CreationTimestamp
	private Date createdDate;
	
	@Column(name = "method_name")
	private String methodName;
	
	@Column(name = "method_duration")
	private Long methodDuration;
	
	@Column(name = "error")
	private String error;
}
