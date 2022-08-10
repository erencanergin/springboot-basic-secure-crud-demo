package com.eren.springboot.demo.app.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.eren.springboot.demo.app.entity.Log;
import com.eren.springboot.demo.app.service.LogService;

@Aspect
@Component
@Order(1)
public class AppLoggingAspect {

	private LogService logService;
	private Logger logger = Logger.getLogger(getClass().getName());
	@Autowired
	public AppLoggingAspect(LogService logService) {
		this.logService = logService;
	}

	@Around("com.eren.springboot.demo.app.aspect.AopExpressions.forEmployeeRepoNoGetterSetter()")
	public Object aroundRepositoryActions(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		Log log = new Log();

		log.setId((long)0);
		String method = proceedingJoinPoint.getSignature().toString();
		logger.info("\n=====>>> Executing @Around on method: " + method);
		log.setMethodName(method);

		Object result = null;

		long begin = System.currentTimeMillis();
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			log.setError(e.getMessage());
			logService.save(log);
			throw e;
		}
		long end = System.currentTimeMillis();
		long duration = end - begin;
		log.setMethodDuration(duration);
		logService.save(log);
		
		return result;
	}
}
