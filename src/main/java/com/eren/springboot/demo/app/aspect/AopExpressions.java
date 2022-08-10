package com.eren.springboot.demo.app.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {
	
	@Pointcut(("execution(* com.eren.springboot.demo.app.dao.EmployeeRepository.*(..))"))
	public void forEmployeeRepo() {}
	
	@Pointcut(("forEmployeeRepo() && !(getter() || setter())"))
	public void forEmployeeRepoNoGetterSetter() {}
	
	@Pointcut(("execution(* com.eren.springboot.demo.app.dao.EmployeeRepository.get*(..))"))
	public void getter() {}
	
	@Pointcut(("execution(* com.eren.springboot.demo.app.dao.EmployeeRepository.set*(..))"))
	public void setter() {}
}
