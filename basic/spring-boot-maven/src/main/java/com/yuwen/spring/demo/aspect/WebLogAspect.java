package com.yuwen.spring.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Restful接口日志切面，用于打印请求日志和响应日志
 * 
 * @author yuwen 90955
 *
 */
@Component
@Aspect
public class WebLogAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(public * com.yuwen.spring.demo.controller.*.*(..))")
	public void webLog() {
	}

	@Around("webLog()")
	public Object around(ProceedingJoinPoint point) throws Throwable {

		String methodName = point.getSignature().toShortString();

		Object[] params = point.getArgs();

		if (logger.isDebugEnabled()) {
			logger.debug("{} start, request={}", methodName, params);
		}

		Object result;
		try {
			result = point.proceed();
		} catch (Exception e) {
			logger.error("{} error, exception={}", methodName, e);
			throw e;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("{} end, result={}", methodName, result);
		}
		return result;

	}
}