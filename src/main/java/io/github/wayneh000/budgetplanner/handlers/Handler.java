package io.github.wayneh000.budgetplanner.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Handler {

	private static final Logger LOGGER = LogManager.getLogger();

	@AfterReturning(pointcut = "execution(public * io.github.wayneh000.budgetplanner.service.*.*(..))", returning = "obj")
	public void afterReturningAdvice(Object obj) {
		LOGGER.info("Execution successful with details: {}", obj);
	}

	@AfterThrowing(pointcut = "execution(public * io.github.wayneh000.budgetplanner.service.*.*(..))", throwing = "e")
	public void afterThrowingAdvice(Exception e) {
		LOGGER.error("Execution failed with the following reason: {}", e.getMessage());
		LOGGER.error(e);
	}
}
