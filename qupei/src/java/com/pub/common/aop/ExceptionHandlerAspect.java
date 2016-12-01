package com.pub.common.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面代码
 * 
 * @author jia.chen
 */
public class ExceptionHandlerAspect {
	private final Logger logger = Logger.getLogger(this.getClass());

	public Object exceptionHandler(ProceedingJoinPoint pjp) throws Throwable {

		try {
			return pjp.proceed();
		} catch (Exception e) {
			logger.error("demo ok ok?");
			logger.error(e);

			throw e;
		}
	}
}
